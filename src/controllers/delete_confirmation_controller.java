
package controllers;

import dataStorage.Appointment;
import dataStorage.Customer;
import dataStorage.allAppointments;
import dataStorage.allCustomers;
import java.text.ParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utility.SQL_deletes;
import utility.SQL_queries;
import utility.errorBoxes;

/**
 * Controller class for the delete_error.fxml file. Handles  data manipulation for the delete confirmation view.
 * @author Arun Rai
 */
public class delete_confirmation_controller {

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private Label questionLabel;

    @FXML
    private Label titleLabel;
    
    private Customer selectedCustomerFromHome;
    
    private Appointment selectedAppointmentFromHome;

    /**
     * Close the "delete_error.fxml" file and return user to home page without deleting or removing and data.
     * @param event The event triggered by clicking the "Cancel" button.
     */
    @FXML
    void clickedCancelbutton(ActionEvent event) {
        closeWindow();
    }

    /**
     * If user clicks "OK" to confirm deleting a customer or appointment, program will read the title of current FXML file to verify which object to delete. If
     * title contains customer than it will delete private variable "selectedCustomerFromHome" from SQL database which was set with selected customer from home page when program was first loaded. Else, it will
     * delete "selectedAppointmentFromHome" from SQL database which was also set with selected appointment from home page when program was loaded.
     * 
     * @param event The event triggered by clicking the "OK" button.
     * @throws ParseException 
     */    
    @FXML
    void clickedOkbutton(ActionEvent event) throws ParseException {
        if(questionLabel.getText().contains("Customer")){
            SQL_deletes.deleteCustomer(selectedCustomerFromHome);
            allCustomers.clearAllCustomers();
            SQL_queries.getCustomers();
        } else {
            SQL_deletes.deleteAppointment(selectedAppointmentFromHome);
            allAppointments.clearAllAppointments();
            SQL_queries.getAppointments();
        }
        home_controller.refresh();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();        
    }
    /**
     * If page is closed by clicking "X" on top right it will execute this code, which will close the window and return the user to home page without deleting any data.
     * @param event Clicking "X" on top right of window.
     */
    @FXML
    public void closeRequestHandler(WindowEvent event) {
        closeWindow();
    }
    
    /**
     *When program needs to close page and return to home page without deleting any data it will execute this method which prints messages to the home page notifying the user that no data was deleted. 
     */
    private void closeWindow(){
        if(questionLabel.getText().contains("Customer")){
            errorBoxes.mainErrorboxes("Customer not deleted. (Customer ID: " + selectedCustomerFromHome.getCustomerId() + ")"); 
        } else {
            errorBoxes.mainErrorboxes("Appointment not deleted. (Appointment ID: " + selectedAppointmentFromHome.getAppointmentId() + ", Type: " + selectedAppointmentFromHome.getType() + ")");                        
        }
        home_controller.refresh();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();        
    }
    
    
    /**
     * When user clicks "Delete Customer" from home page and a customer is selected. This method is ran, it will set the private variable "selectedCustomerFromHome" to the customer selected so
     * when the user confirms the delete in the pop the program can retrieve the customer that was selected from home page and delete it from SQL database.
     * @param customer Customer that is being deleted.
     */
    public void setCustomerinPopUp(Customer customer){
        questionLabel.setText("Are you sure want to delete this Customer?\nCustomer ID: " + customer.getCustomerId());
        selectedCustomerFromHome = customer;       
    }    

    /**
     * When user clicks "Delete Appointment" from home page and a appointment is selected. This method is ran, it will set the private variable "selectedAppointmentFromHome" to the appointment selected so
     * when the user confirms the delete in the pop the program can retrieve the appointment that was selected from home page and delete it from SQL database.
     * @param appointment Appointment that is being deleted.
     */
    public void setAppointmentinPopUp(Appointment appointment){
        questionLabel.setText("Are you sure want to delete Appointment?\nAppointment ID: " + appointment.getAppointmentId() + "\nType: " + appointment.getType());
        selectedAppointmentFromHome = appointment;       
    }    
}

