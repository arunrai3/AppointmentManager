package controllers;

import Main.JDBC;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static Main.JDBC.connection;
import Main.Main;
import dataStorage.Appointment;
import dataStorage.allAppointments;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


/**
 * Controller class for the login.fxml file. Handles all user input and data manipulation for the Login view.
 * @author Arun Rai
 */
public class login_controller implements Initializable  {
    
    @FXML
    private Button clearbutton;

    @FXML
    private Label errorLogin;

    @FXML
    private Button exitButton;

    @FXML
    private Label languageText;

    @FXML
    private Label locationText;

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordClear;

    @FXML
    private PasswordField passwordText;

    @FXML
    private ToggleButton showPassword;

    @FXML
    private TextField usernameText;
    
    @FXML
    private AnchorPane AnchorPane;
    
    @FXML
    private Label passwordLabel;
    
    @FXML
    private Label usernameLabel;
    
    @FXML
    private Label logintitleLabel;
    
    @FXML 
    private Label locationText1;
    
    @FXML
    private Label languageText1;
    
    @FXML
    private Label connectionError;
    
    private static String currentUser;
    
    /**
     * When user is interacting with the login page they can click the tab button it will move to the next button in the most logical way.
     * <p>
     * LAMBDA EXPRESSION:
     * <p>
     * The lambda expression is useful in this case because instead of creating an inner class with methods for what we want to execute within the lambda expression we can simply paste the action
     * into the expression. Making the code easier to modify and more clear for future developers to work with.
     * @param node Currently selected Item on GUI.
     * @param nextNode The next Item in cycle of options to be selected in GUI.
     */    
    private void setNextFocusOnTabPress(Node node, Node nextNode) {
        node.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                if (nextNode == showPassword && node == usernameText){
                    if (showPassword.isSelected()) {
                        passwordClear.requestFocus();
                    } else {
                        passwordText.requestFocus();                        
                    }
                } else {
                    nextNode.requestFocus();
                }
            }
        });
    }

    
    /**
     * The initialize method of the login controller, will identify the current time zone set on the PC the program is running on and display it to screen. It will
     * also identify whether the current language on PC is French or English and set all the text to the appropiate language. Also assign which buttons and Text fields
     * will be added to the group of options to cycle through when the user clicks tab.
     * @param url a URL representing the location of the FXML file.
     * @param rb a ResourceBundle representing the localized resources for the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ZoneId zoneId = ZoneId.systemDefault();
        Locale locale = Locale.getDefault();

        if (locale.getLanguage().equals("fr")) {
            String displayName = zoneId.getDisplayName(TextStyle.FULL, locale);
            locationText1.setText(displayName);
        } else {
            locationText1.setText(zoneId.toString());
        }
        
        try{
            ResourceBundle bundle = ResourceBundle.getBundle("utility.login", locale);
            usernameLabel.setText(bundle.getString("usernameLabel"));
            passwordLabel.setText(bundle.getString("passwordLabel"));
            logintitleLabel.setText(bundle.getString("loginButton"));
            languageText.setText(bundle.getString("languageText"));
            locationText.setText(bundle.getString("locationText"));
            errorLogin.setText(bundle.getString("errorLogin"));
            clearbutton.setText(bundle.getString("clearbutton"));
            showPassword.setText(bundle.getString("showPassword"));
            exitButton.setText(bundle.getString("exitButton"));
            loginButton.setText(bundle.getString("loginButton"));
            languageText1.setText(bundle.getString("languageText1"));
            connectionError.setText(bundle.getString("connectionError"));
        } catch(Exception e){
    
        }

        Tooltip tooltip = new Tooltip();
        tooltip.textProperty().bind(locationText.textProperty());
        Tooltip.install(locationText, tooltip); 
        AnchorPane.setOnMouseClicked(event -> {
            Node target = (Node) event.getTarget();
            if (!(target instanceof Button || target instanceof TextField)) {
                AnchorPane.requestFocus();
            }
        });
        setNextFocusOnTabPress(usernameText, showPassword);
        setNextFocusOnTabPress(passwordText, loginButton);
        setNextFocusOnTabPress(passwordClear, loginButton);
        setNextFocusOnTabPress(loginButton, exitButton);
        setNextFocusOnTabPress(exitButton, clearbutton);
        setNextFocusOnTabPress(clearbutton, showPassword);
        setNextFocusOnTabPress(showPassword, usernameText);
    }
    
    /**
     * Closes the entire program.
     * @param event The event triggered by clicking the "Exit" button.
     */
    @FXML
    void clickedExit(ActionEvent event) {
       ((Stage) exitButton.getScene().getWindow()).close();
    }
    
    /**
     * Runs the method validate credentials when user clicks "Login".
     * @param event The event triggered by clicking the "Login" button.
     */
    @FXML
    void clickedLogin(ActionEvent event) {
        validateCredentials();
    }    
    
    /**
     * If the user enters an incorrect username or password text will display to the user notifying them of the error. This method sets the text to invisible then visible repeatedly creating a flash effect to capture the
     * users attention. It is especially useful when the user triggers the same error twice and is not sure whether the error text is from previous error or current one. The flashing happens every time a new error occurs
     * to prevent confusion with previous errors.
     * <p>
     * LAMBDA EXPRESSION:
     * <p>
     * Lambda expressions are used here to provide a concise and readable way to define the behavior of each KeyFrame in the Timeline, without the need for a separate class or method.
     * @param label Label to apply flash effect to.
     */
    private void flashError(Label label) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.1), e -> label.setVisible(false)),
            new KeyFrame(Duration.seconds(0.2), e -> label.setVisible(true)),
            new KeyFrame(Duration.seconds(0.3), e -> label.setVisible(false)),
            new KeyFrame(Duration.seconds(0.4), e -> label.setVisible(true))
        );
        timeline.setCycleCount(3); 
        timeline.play();
    }

    /**
     * Method is ran when user clicks the "Clear" button, it will clear all data inputs, remove all errors displayed to screen, and reset "Show Password" setting to default where password is not invisible.
     * @param event The event triggered by clicking the "Clear" button.
     */
    @FXML
    void clickedClear(ActionEvent event) {
        errorLogin.setVisible(false);
        usernameText.setText("");
        passwordText.setText("");
        passwordClear.setText("");
        showPassword.setSelected(false);
        passwordText.setVisible(true);
        passwordClear.setVisible(false);
        connectionError.setVisible(false);

    }

    /**
     * Clicking Login is not the only way to notify the program that the user has entered all data and is ready to verify credentials. Clicking the "Enter" button on your keyboard when the password box is highlighted
     * also works.
     * @param event The even triggered by clicking "Enter" on keyboard while hidden password text box is selected.
     */
    @FXML
    void enteredPassword(ActionEvent event) {
        validateCredentials();
    }
    
    /**
     * Clicking Login is not the only way to notify the program that the user has entered all data and is ready to verify credentials. Clicking the "Enter" button on your keyboard when the password box (and show password is not selected) is highlighted
     * also works. 
     * @param event The even triggered by clicking "Enter" on keyboard while visible password text box is selected.
     */    
    @FXML
    void passwordClearEnter(ActionEvent event) {
        validateCredentials();
    }    
    
    /**
     * Clicking Login is not the only way to notify the program that the user has entered all data and is ready to verify credentials. Clicking the "Enter" button on your keyboard when the username box is highlighted
     * also works.
     * @param event The even triggered by clicking "Enter" on keyboard while username text box is selected.
     */
    @FXML
    void enteredUsername(ActionEvent event) {
        validateCredentials();
    }
    
    /**
     * Method is called from four places. User clicks "Login" button, or user clicked "Enter" on keyboard when either username box, or one of the password boxes is highlighted(depending on if Show Password is selected or not).
     * Program then gathers data from appropiate Text boxes and runs a SELECT statement on database trying to find a matching username and password. If match is found it will load the home page, and write to "login_activity.txt" that
     * a successful attempt was made with time and date included. If no username was found or a username was found but the password did not match, it will print an error to screen notifying user of incorrect credentials and write
     * to "login_activity.txt" that a failed attempt was made with time and date included.
     */
    private void validateCredentials(){
        errorLogin.setVisible(false);
        String username = usernameText.getText();
        String password = "";
        if (showPassword.isSelected()) {
            password = passwordClear.getText();
        } else {
            password = passwordText.getText();        
        }
        try {
            JDBC.openConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT Password FROM users WHERE User_Name = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String passwordFromDb = rs.getString("Password");
                if (password.equals(passwordFromDb)) {
                    currentUser = username;
                    loginActivity("Succesful");
                    Main.handleSceneChange("home.fxml", "null", "null", loginButton, "no",null,null,null,"hide");                    
                } else {
                    loginActivity("Failed");
                    connectionError.setVisible(false);
                    errorLogin.setVisible(true);
                    flashError(errorLogin);
                }
            } else {
                loginActivity("Failed");
                connectionError.setVisible(false);
                errorLogin.setVisible(true);
                flashError(errorLogin);
            }

            rs.close();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException ex) {
            errorLogin.setVisible(false);
            connectionError.setVisible(true);
            flashError(connectionError);
        } catch (NullPointerException ex) {
            errorLogin.setVisible(false);
            connectionError.setVisible(true);
            flashError(connectionError);
        }        
    }
    
    /**
     * Method is ran when "Show Password" button is clicked. If the button was not selected before the time of button being clicked, it will make the password visible so user can see, else if it was selected
     * before than it will make the password invisible.
     * @param event The even triggered by clicking the "Show Password" button.
     */
    @FXML
    void togglePasswordVisibility(ActionEvent event) {
        if (showPassword.isSelected()) {
            passwordText.setVisible(false);
            passwordClear.setVisible(true);
            passwordClear.setText(passwordText.getText());
        } else {
            passwordText.setVisible(true);
            passwordClear.setVisible(false);
            passwordText.setText(passwordClear.getText());
        }
    }
    
    /**
     * This method is ran every time the "validateCredentials" is ran. If the SELECT statement ran in "validateCredentials" was successful than this method will be ran with the String status having the value "Successful" else it will have the
     * "Failed". Then the program will look in the root folder of the project and see if the file "login_activity.txt" exists. If it does it will append the file with a new line containing the status variable, the date, and time. If the file does
     * not exist than it will create a new one and add the first line with status variable, date, and time.
     * @param status "Successful" or "Failed" depending on if the login attempt was successful or not.
     */
    private void loginActivity(String status){
        String rootFolder = System.getProperty("user.dir");
        String filePath = rootFolder + "\\login_activity.txt";

        Instant currentInstant = Instant.now();
        Timestamp currentTimestamp = java.sql.Timestamp.from(currentInstant);
        Timestamp utcTimestamp = java.sql.Timestamp.valueOf(currentInstant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
        
        
        try {
            Path file = Paths.get(filePath);
            if (Files.exists(file)) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                writer.append(status + " login attempt at UTC time: " + utcTimestamp + "\n");
                writer.close();
            } else {
                Files.createFile(file);
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                writer.write(status + " login attempt at UTC time: " + utcTimestamp + "\n");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    /**
     * Called from "SQL_updates" and "SQL_inserts", fills in the columns "Created_By" and "Last_Updated_By". currentUser assigned to the username that was used the last time the program successfully logged in.
     * @return Current User.
     */
    public static String getCurrentUser (){
        return currentUser;
    }
    
}
