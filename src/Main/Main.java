package Main;

import controllers.appointment_controller;
import controllers.customer_controller;
import controllers.delete_confirmation_controller;
import controllers.notification_error;
import dataStorage.Appointment;
import dataStorage.Customer;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Modality;


/**
 * The Main class is responsible for starting the application and handling scene changes.
 * @author Arun Rai
 */

public class Main extends Application {

    /**
     * The main method starts the application and opens the database connection.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    } 
    
    /**
     * The start method loads the login scene and displays it.
     * @param stage the primary stage for this application
     * @throws IOException if an I/O error occurs
     */    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    /**
     * The handleSceneChange method loads the specified FXML file and displays it in a new scene.
     * @param fxmlFile The FXML file to be loaded.
     * @param text Title for FXML being loaded. Because different pages share same FXML file.
     * @param labelId Label to apply Title text to in FXML file
     * @param button Button from scene that called the method
     * @param popUp "yes" if loading a pop up or "no" if not loading pop up.
     * @param customer If loading Modify Customer page, you can pass the Customer you want to modify with this parameter.
     * @param appointment If loading Modify Appointment page, you can pass the Appointment you want to modify with this parameter.
     * @param listofappointments When deleting a customer, this list gets passed to a pop up notifying user that Appointments for this Customer must be deleted first. 
     * @param hidebackground When loading a pop up, you can choose to hide background by passing "hide".
     */
    public static void handleSceneChange(String fxmlFile, String text, String labelId, Button button, String popUp, Customer customer, Appointment appointment, List<Integer> listofappointments, String hidebackground) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/" + fxmlFile));
            Parent root = loader.load();
            Object controller = loader.getController();
            if (labelId != "null" && text != "null"){
            Label labelVariable = (Label)root.lookup("#" + labelId);
            labelVariable.setText(text);
            }
            if(text.equals("No Upcoming Appointments!")){
                ((notification_error)controller).noUpComingAppointmnets();                
            }
            if (listofappointments != null){
                ((notification_error)controller).setDescriptionLabelForDeleteCustomerError(listofappointments);
            }
            if (customer != null){
                if (text.equals("Modify Customer")){
                 ((customer_controller)controller).loadCustomerData(customer);
                }else {
                 ((delete_confirmation_controller)controller).setCustomerinPopUp(customer);        
                }
            }
            if (appointment != null){
                if (text.equals("Modify Appointment")){
                 ((appointment_controller)controller).loadAppointmentData(appointment);
                }else if(fxmlFile.equals("delete_error.fxml")) {
                 ((delete_confirmation_controller)controller).setAppointmentinPopUp(appointment);        
                }else if (fxmlFile.equals("notification_error.fxml")){
                    if(text.equals("Upcoming Appointment!")){
                        ((notification_error)controller).setDescriptionLabelForAppointmentWithin15(appointment);
                    } else {
                        ((notification_error)controller).setDescriptionLabelForOverLappingAppointments(appointment);
                    }
                }                
            }
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            if (popUp.equals("yes")) {
                if (fxmlFile.equals("delete_error.fxml")){
                    stage.setOnCloseRequest(e -> ((delete_confirmation_controller)controller).closeRequestHandler(e));
                } else if(fxmlFile.equals("notification_error.fxml")) {
                    stage.setOnCloseRequest(e -> ((notification_error)controller).closeRequestHandler(e));
                } else if(fxmlFile.equals("customer.fxml")){
                    stage.setOnCloseRequest(e -> ((customer_controller)controller).closeRequestHandler(e));
                } else if(fxmlFile.equals("appointment.fxml")){
                    stage.setOnCloseRequest(e -> ((appointment_controller)controller).closeRequestHandler(e));
                }
                
                Stage originalStage = (Stage) button.getScene().getWindow();
                if(hidebackground.equals("hide")){
                    originalStage.setOpacity(0);                
                }
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(button.getScene().getWindow());
                stage.setScene(scene);
                stage.showAndWait();
                if(hidebackground.equals("hide")){
                    originalStage.setOpacity(1);
                }
            } else {
                Stage originalStage = (Stage) button.getScene().getWindow();
                originalStage.setScene(scene);
                originalStage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }    
        
}
