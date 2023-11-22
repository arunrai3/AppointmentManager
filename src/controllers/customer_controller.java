
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Main.Main;
import dataStorage.Customer;
import dataStorage.allCountries;
import dataStorage.allCustomers;
import dataStorage.allFirst_level_divisions;
import dataStorage.country;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import utility.SQL_queries;
import utility.inputcheck;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.SQL_inserts;
import utility.SQL_updates;
import utility.errorBoxes;

/**
 * Controller class for the customer.fxml file. Handles all user input and data manipulation for the Customer view.
 * @author Arun Rai
 */
public class customer_controller implements Initializable {

    @FXML
    private TextField address_box;

    @FXML
    private Label address_error;

    @FXML
    private Button cancelCustomerbutton;

    @FXML
    private ComboBox<String> country_box;

    @FXML
    private Label country_error;

    @FXML
    private TextField customer_id_box;

    @FXML
    private Label customer_id_error;

    @FXML
    private TextField idTextbox;    

    @FXML
    private TextField customer_name_box;

    @FXML
    private Label customer_name_error;

    @FXML
    private Label customer_title;

    @FXML
    private ComboBox<String> first_level_box;

    @FXML
    private Label first_level_error;

    @FXML
    private TextField phone_number_box;

    @FXML
    private Label phone_number_error;

    @FXML
    private TextField postal_code_box;

    @FXML
    private Label postal_code_error;
    
    @FXML
    private Button customerSavebutton;   
    
    @FXML
    private ComboBox<?> selectCountryBox;   
    
    private Integer primary_key;
    
    private Integer primary_key_modify;
    
    /**
     * This method initializes the Add Customer/Modify page with country and first-level division data from the database. It sets the primary key text box to the next available primary key for Customers. It adds the country names 
     * to the country_box and sets the prompt text to "Select country first". When a country is selected from the country_box, it clears the first_level_box and sets it to visible. It creates an ObservableList of first-level divisions 
     * for the selected country and adds them to the first_level_box. If a blank space is selected, it clears the first_level_box and hides it, and sets the selectCountryBox to visible.
     * @param url a URL representing the location of the FXML file.
     * @param rb a ResourceBundle representing the localized resources for the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        primary_key = allCustomers.getPrimaryKey();
        idTextbox.setPromptText(Integer.toString(primary_key));
        selectCountryBox.setPromptText("Select country first");
        allCountries.clearAllCountries();
        allFirst_level_divisions.clearAllDivisions();
        SQL_queries.getCountries();
        SQL_queries.getDivisions();
        
        ObservableList<country> countries = allCountries.getCountries();
        ObservableList<String> countrynames = FXCollections.observableArrayList(); 
        HashMap<String, Integer> country_and_id = new HashMap<String, Integer>();
        countrynames.add(" ");
        for (country loop_country : countries) {            
            countrynames.add(loop_country.getCountry());
            country_and_id.put(loop_country.getCountry(), loop_country.getCountry_ID());   
        }
        
        country_box.setItems(countrynames);
        
        country_box.setOnAction(event -> {
            String selectedCountry = country_box.getValue();
            if (!selectedCountry.equals(" ")) {
                first_level_box.setValue(" ");
                first_level_box.getItems().clear();
                selectCountryBox.setVisible(false);
                first_level_box.setVisible(true);
                int country_id = country_and_id.get(selectedCountry);
                ObservableList<String> divisions = FXCollections.observableArrayList(); 
                divisions.add(" ");  
                divisions.addAll(allFirst_level_divisions.getDivisionNames(country_id));            
                first_level_box.setItems(divisions);                                    
            } else {
                first_level_box.getItems().clear();
                first_level_box.setValue(" ");
                first_level_box.setVisible(false);
                selectCountryBox.setVisible(true);                
            }
        });                
    }

    /**
     * Close the customer fxml file and return user to home page without saving any data.
     * @param event the event triggered by clicking the "Cancel" button
     */    
    @FXML
    void clickedCancelCustomerButton(ActionEvent event) {
        home_controller.refresh();
        Stage stage = (Stage) customerSavebutton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * If page is closed by clicking "X" on top right it will execute this code, which will close the window and return the user to home page without saving any data.
     * @param event The event triggered by clicking "X" on top right of window.
     */
    @FXML
    public void closeRequestHandler(WindowEvent event) {
        home_controller.refresh();
        Stage stage = (Stage) customerSavebutton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * When modify button is clicked from home page, it loads the selected customer data through this method
     * @param customer the customer object to be loaded
     */       
    public void loadCustomerData(Customer customer){
        idTextbox.setPromptText(Integer.toString(customer.getCustomerId()));
        primary_key_modify = customer.getCustomerId();
        customer_name_box.setText(customer.getCustomerName());
        phone_number_box.setText(customer.getPhoneNumber());
        address_box.setText(customer.getAddress());
        postal_code_box.setText(customer.getPostalCode());
        country_box.setValue(customer.getCountry());
        first_level_box.setValue(customer.getDivision());
        selectCountryBox.setVisible(false);
        first_level_box.setVisible(true);
        
        int country_id = allFirst_level_divisions.getCountryIdWithDivisionId(customer.getDivisionId());
        ObservableList<String> divisions = FXCollections.observableArrayList(); 
        divisions.add(" ");  
        divisions.addAll(allFirst_level_divisions.getDivisionNames(country_id));            
        first_level_box.setItems(divisions);                                    

    }
    
    
    
    /**
     * When the user clicks the save button, program will collect all data in text fields and combo boxes. It will then pass the data to methods in the class "inputcheck" and verify
     * if the data is valid. If all the data is valid and the page is has title "Add Customer" it will insert a new row into the customers table in the client_schedule database, else if the title
     * is "Modify Customer" it will update the row with the matching Customer_ID and return the user to home page.If the data is not valid than it will remain on the customer page, not insert or update any data and notify the user with 
     * error messages of how they can correct their data inputs.
     * @param event The event triggered by the user clicking the "Save" button.
     */
    @FXML
    void clickedSaveButton(ActionEvent event) {
        
        errorBoxes.checkForEmptyErrorBox("reset", new Label[]{customer_name_error, phone_number_error, postal_code_error,address_error, first_level_error, country_error});
        
        String customername = inputcheck.checkString(customer_name_box, "Customer Name",customer_name_error);
        String phonenumber = inputcheck.checkPhonenumber(phone_number_box, "Phone Number", phone_number_error);
        String postalcode = inputcheck.checkPostalcodeOrAddress(postal_code_box, "Postal Code", postal_code_error, "hyphens");
        String address = inputcheck.checkPostalcodeOrAddress(address_box, "Address", address_error,"commas, spaces");
        String country = inputcheck.checkCountryOrDivision(country_box.getValue(),"Country", country_error);
        String first_level = inputcheck.checkCountryOrDivision(first_level_box.getValue(), "First-Level Division", first_level_error);
        
        if (!customername.equals("false_") && !phonenumber.equals("invalid number_") && !postalcode.equals("invalid postal code_") && !address.equals("invalid postal code_") && !country.equals("not valid country_division") && !first_level.equals("not valid country_division")){
            int division_id = allFirst_level_divisions.getDivisionId(first_level);
            if (division_id != -1) {  
                if (customer_title.getText().equals("Add Customer")) {
                    SQL_inserts.insertCustomer(primary_key,customername,address,postalcode,phonenumber,division_id);
                } else {
                    SQL_updates.updateCustomer(primary_key_modify, customername, address, postalcode, phonenumber, division_id);
                }
                home_controller.refresh();
                Stage stage = (Stage) customerSavebutton.getScene().getWindow();
                stage.close();
            } else {
                home_controller.refresh();
                errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
                Stage stage = (Stage) customerSavebutton.getScene().getWindow();
                stage.close();
            }
        }        
    }
}

