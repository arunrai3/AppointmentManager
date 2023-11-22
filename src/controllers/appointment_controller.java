package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Main.Main;
import dataStorage.Appointment;
import dataStorage.allAppointments;
import dataStorage.allContacts;
import dataStorage.allCustomers;
import dataStorage.allUsers;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import utility.SQL_queries;
import utility.errorBoxes;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.SQL_inserts;
import utility.SQL_updates;
import utility.inputcheck;

import utility.time_zones;



/**
 * Controller class for the appointment.fxml file. Handles all user input and data manipulation for the Appointment view.
 * @author Arun Rai
 */
public class appointment_controller implements Initializable {

    @FXML
    private TextField appointment_id_box;

    @FXML
    private Label appointment_id_error;

    @FXML
    private Label appointment_title;

    @FXML
    private Button cancel_appointment_button;

    @FXML
    private ComboBox<String> contact_box;

    @FXML
    private Label contact_error;

    @FXML
    private ComboBox<Integer> customer_id_drop_box;

    @FXML
    private Label customer_id_error_a;

    @FXML
    private TextField description_box;

    @FXML
    private Label description_error;

    @FXML
    private DatePicker end_date_box;

    @FXML
    private Label end_date_error;

    @FXML
    private ComboBox<String> end_hour_box;

    @FXML
    private ComboBox<String> end_minute_box;

    @FXML
    private Label end_time_error;

    @FXML
    private TextField location_box;

    @FXML
    private Label location_error;

    @FXML
    private Button save_appointment_button;

    @FXML
    private DatePicker start_date_box;

    @FXML
    private Label start_date_error;

    @FXML
    private ComboBox<String> start_hour_box;

    @FXML
    private ComboBox<String> start_min_box;

    @FXML
    private Label start_time_error;

    @FXML
    private Label title_error;

    @FXML
    private TextField title_id_box;

    @FXML
    private TextField type_box;

    @FXML
    private Label type_error;

    @FXML
    private ComboBox<Integer> user_id_box;

    @FXML
    private Label user_id_error;
        
    @FXML
    private TextField idTextbox;

    private Integer primary_key;

    private Integer primary_key_modify;

    /**
     * Initializes the controller class and sets the initial values of the various input fields and drop down menus. Also clears all customer, contact, appointment, and user data,
     * and then calls methods to retrieve appointment, customer, and user data from the SQL database. 
     * @param url the URL used to create an instance of this class. 
     * @param rb the ResourceBundle used to create an instance of this class.
     */    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        allCustomers.clearAllCustomers();
        allContacts.clearContacts();
        allAppointments.clearAllAppointments();
        allUsers.clearUsers();
        try {
            SQL_queries.getAppointments();
        } catch (ParseException ex) {
            Logger.getLogger(appointment_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQL_queries.getCustomers();
        SQL_queries.getContacts(); 
        SQL_queries.getUsers();

        primary_key = allAppointments.getPrimaryKey();
        idTextbox.setPromptText(Integer.toString(primary_key));        

        ObservableList<Integer> customerIds = FXCollections.observableArrayList();
        customerIds.add(null);
        customerIds.addAll(allCustomers.getCustomerIds());
        customer_id_drop_box.setItems(customerIds);
        
        ObservableList<Integer> userIds = FXCollections.observableArrayList();
        userIds.add(null);
        userIds.addAll(allUsers.getUserIds());
        user_id_box.setItems(userIds);        
        
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        contactNames.add(" ");
        contactNames.addAll(allContacts.getContactNames());
        contact_box.setItems(contactNames);        
        
        ObservableList<String> hours = FXCollections.observableArrayList();
        hours.add(" ");
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24");
        
        ObservableList<String> minutes = FXCollections.observableArrayList();
        minutes.add(" ");
        minutes.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59");        
        
        start_hour_box.setItems(hours);
        end_hour_box.setItems(hours);
        
        start_min_box.setItems(minutes);                
        end_minute_box.setItems(minutes);
        
    }
    
    
    /**
     * Close the appointment fxml file and return user to home page without saving any data.
     * @param event The event triggered by clicking the "Cancel" button.
     */    
    @FXML
    void clickedCancelAppointment(ActionEvent event) {
        home_controller.refresh();
        Stage stage = (Stage) type_error.getScene().getWindow();
        stage.close();
    }
    
    /**
     * If page is closed by clicking "X" on top right it will execute this code, which will close the window and return the user to home page without saving any data.
     * @param event The event triggered by clicking "X" on top right of window.
     */
    @FXML
    public void closeRequestHandler(WindowEvent event) {
        home_controller.refresh();
        Stage stage = (Stage) type_error.getScene().getWindow();
        stage.close();
    }    
    

    
    /**
     * When modify button is clicked from home page, it loads the selected appointment data through this method
     * @param appointment the Appointment object to be loaded
     */    
    public void loadAppointmentData (Appointment appointment){
        idTextbox.setPromptText(Integer.toString(appointment.getAppointmentId()));
        primary_key_modify = appointment.getAppointmentId();
        title_id_box.setText(appointment.getTitle());
        description_box.setText(appointment.getDescription());
        location_box.setText(appointment.getLocation());
        type_box.setText(appointment.getType());
        contact_box.setValue(appointment.getContactName());
        customer_id_drop_box.setValue(appointment.getCustomerId());
        user_id_box.setValue(appointment.getUserId());
        
        Timestamp start = appointment.getLocalStart();
        Timestamp end = appointment.getLocalEnd();
        
        LocalDate startDate = start.toLocalDateTime().toLocalDate();
        start_date_box.setValue(startDate);

        String startHour = String.format("%02d", start.toLocalDateTime().getHour());
        start_hour_box.setValue(startHour);

        String startMinute = String.format("%02d", start.toLocalDateTime().getMinute());
        start_min_box.setValue(startMinute);

        LocalDate endDate = end.toLocalDateTime().toLocalDate();
        end_date_box.setValue(endDate);

        String endHour = String.format("%02d", end.toLocalDateTime().getHour());
        end_hour_box.setValue(endHour);

        String endMinute = String.format("%02d", end.toLocalDateTime().getMinute());
        end_minute_box.setValue(endMinute);                
        
    }
    
    
    /**
     *When user clicks the "Save" button in the "Add Appointment" or "Modify Appointment" screen, this method checks that all input fields are valid, that the start time is earlier than the end time, that the appointment time
     *is within business hours and that there are no overlapping appointments. If all checks pass, the appointment data is either added to the database (if adding a new appointment) or modified in the database (if modifying 
     *an existing appointment). The home page is then refreshed to show the updated appointment data, and the Add/Modify Appointment screen is closed. If any checks fail, the user is alerted via error messages displayed on the screen.
     *@param event The event triggered by the user clicking the "Save" button.
     */    

    @FXML
    void clickedSaveButton(ActionEvent event) {
        Timestamp startTime = null;
        Timestamp endTime = null;
        String EndGreaterThanStart = "not good";
        String TimeInBuisnessHours = "Not within buisness hours";
        String NotOverlapping = "Overlapping";        

        errorBoxes.checkForEmptyErrorBox("reset", new Label[]{user_id_error, type_error, title_error,start_time_error, start_date_error, location_error, end_time_error,end_date_error, description_error,customer_id_error_a, contact_error,appointment_id_error});
        
        String title = inputcheck.checkString(title_id_box, "Title",title_error);
        String description = inputcheck.checkString(description_box, "Description", description_error);
        String location = inputcheck.checkString(location_box, "Location", location_error);
        String type = inputcheck.checkString(type_box, "Type", type_error);
        String contact = inputcheck.checkCountryOrDivision(contact_box.getValue(),"Contact", contact_error);
        String customerIdcheck = inputcheck.checkNullCustomerOrUser(customer_id_drop_box.getValue(),customer_id_error_a , "Please select a Customer ID.");
        String userIdcheck = inputcheck.checkNullCustomerOrUser(user_id_box.getValue(),user_id_error , "Please select a User ID.");
        String startDateCheck = inputcheck.checkNullDate(start_date_box.getValue(), start_date_error, "Please select a Start Date");
        String endDateCheck = inputcheck.checkNullDate(end_date_box.getValue(), end_date_error, "Please select an End Date");
        String time_input = inputcheck.checkHourAndMinBoxes(start_hour_box.getValue(), start_min_box.getValue(),end_hour_box.getValue(),end_minute_box.getValue(), start_time_error, end_time_error);


        if (time_input.equals("valid") && startDateCheck.equals("valid date") && endDateCheck.equals("valid date")){
            startTime = time_zones.convertToTimeStampDataTypeUTC(start_min_box.getValue(),start_hour_box.getValue(), start_date_box.getValue());
            endTime = time_zones.convertToTimeStampDataTypeUTC(end_minute_box.getValue(),end_hour_box.getValue(), end_date_box.getValue());        
            EndGreaterThanStart = inputcheck.checkIfStratGreaterThanEnd(startTime,endTime, start_time_error);
            if (EndGreaterThanStart.equals("good")){
                TimeInBuisnessHours = inputcheck.checkIfWithinBuisnessHours(startTime, endTime, start_time_error);
                if (customerIdcheck.equals("valid ID")){
                    if(appointment_title.getText().equals("Add Appointment")){
                        NotOverlapping = inputcheck.checkIfNotWithinAnotherAppointment(startTime,endTime,customer_id_drop_box.getValue(),save_appointment_button, primary_key);
                    } else {
                        NotOverlapping = inputcheck.checkIfNotWithinAnotherAppointment(startTime,endTime,customer_id_drop_box.getValue(),save_appointment_button,primary_key_modify);
                    }
                } else {
                    end_time_error.setText("Please select a Customer ID before checking Time slots.");
                    end_time_error.setVisible(true);
                }
            }
        }
        

        if (!title.equals("false_") && !description.equals("false_") && !location.equals("false_") && !type.equals("false_") &&
            !contact.equals("not valid country_division") && !customerIdcheck.equals("invalid ID") && !userIdcheck.equals("invalid ID") && !startDateCheck.equals("invalid date") && !endDateCheck.equals("invalid date") &&
            !time_input.equals("not valid") && !EndGreaterThanStart.equals("not good") && !TimeInBuisnessHours.equals("Not within buisness hours") && !NotOverlapping.equals("Overlapping")){

            int contact_id = allContacts.getContactIdWithName(contact_box.getValue());
            if (contact_id != -1){
                if (appointment_title.getText().equals("Add Appointment")){
                    SQL_inserts.insertAppointment(primary_key , title_id_box.getText() , description_box.getText(), location_box.getText(), type_box.getText(), startTime, endTime, customer_id_drop_box.getValue(), user_id_box.getValue(), contact_id);
                } else {
                    SQL_updates.updateAppointment(primary_key_modify , title_id_box.getText() , description_box.getText(), location_box.getText(), type_box.getText(), startTime, endTime,  customer_id_drop_box.getValue(), user_id_box.getValue(), contact_id);
                }
                home_controller.refresh();
                Stage stage = (Stage) type_error.getScene().getWindow();
                stage.close();
            } else {
                home_controller.refresh();
                errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
                Stage stage = (Stage) type_error.getScene().getWindow();
                stage.close();
            }
        }
    }
}
