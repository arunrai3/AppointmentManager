package controllers;



import dataStorage.Appointment;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller class for the notification_error.fxml file. Handles all user input and data manipulation for the notification view.
 * @author Arun Rai
 */
public class notification_error {

    @FXML
    private Label content;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label titleLabel;

    /**
     * Closes the pop up if user clicks "OK" button.
     * @param event The event triggered by clicking the "OK" button.
     */
    @FXML
    void clickedOkbutton(ActionEvent event) {
        home_controller.refresh();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the pop up if user clicks "X" at the top right of screen.
     * @param event Clicking "X" on top right of window.
     * 
     */    
    @FXML
    public void closeRequestHandler(WindowEvent event) {
        home_controller.refresh();
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Method is ran when user selects a customer from home page that has appointments and clicks "Delete Customer". Sets the error message for a customer with associated appointments that must be deleted before the customer can be deleted.
     * Displays the IDs of the associated appointments.
     * @param listOfIntegers List of Appointments belonging to a Customer that must be deleted first.
     */
    public void setDescriptionLabelForDeleteCustomerError (List<Integer> listOfIntegers) {
        titleLabel.setText("Error! Customer has Appointments");
        descriptionLabel.setText("Please delete the following appointments associated with this Customer first:");
        StringBuilder appointmentIds = new StringBuilder();
        appointmentIds.append("\nAppointment ID's: \n");
        for (int i = 0; i < listOfIntegers.size(); i++) {
            appointmentIds.append( listOfIntegers.get(i));
            if (i != listOfIntegers.size() - 1) {
                appointmentIds.append(", ");
            }
        }
        String stringg = appointmentIds.toString();
        content.setText(stringg);                               
    }
    
    /**
     * Method is ran when user picks a time for a customer with an appointment during the same time. Sets the error message for a customer with overlapping appointments.
     * Displays the ID, start time, and end time of the overlapping appointment.
     * @param appointment the Appointment object representing the conflicting appointment
     */        
    public void setDescriptionLabelForOverLappingAppointments (Appointment appointment){
        titleLabel.setText("Error! Customer has an Appointment\nduring this time.");
        descriptionLabel.setText("Following appointments are scheduled during this time:");
        content.setText("Appointment ID: " + appointment.getAppointmentId() + "\nStart: " + appointment.getLocalStart() + "\nEnd: " + appointment.getLocalEnd());
    }
    
    /**
     * Method is ran when user successfully logs in and and their is an appointment within 15 minutes. Sets the message for an appointment that is scheduled within 15 minutes from the current time.
     * Displays the ID, start time, and end time of the appointment.
     *
     * @param appointment the Appointment object representing the appointment scheduled within 15 minutes
     */    
    public void setDescriptionLabelForAppointmentWithin15 (Appointment appointment){
        descriptionLabel.setText("Appointment within 15 minutes:");
        content.setText("Appointment ID: " + appointment.getAppointmentId() + "\nStart: " + appointment.getLocalStart() + "\nEnd: " + appointment.getLocalEnd());
    }
    
    /**
     * Method is ran when user successfully logs in and and their is are no appointments within 15 minutes.
     * Clears the content of the message.
     */    
    public void noUpComingAppointmnets(){
        descriptionLabel.setText("Their are no Appointments within the next 15 minutes.");
        content.setText(" ");
    }
}    
    
    
    


    

