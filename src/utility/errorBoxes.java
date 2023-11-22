package utility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * The errorBoxes class contains methods for handling and displaying error messages to the user
 * @author Arun Rai
 */
public class errorBoxes {
    
   private static Label errorMain;
   
   /**
    * This method is ran once during the initialize method of "home_controller". It sets the Label of where errors will be pasted on the home page. The variable is used many times so setting it once
    * makes it easier to retrieve.
    * @param label Label from "home_controller".
    */
   public static void setHomeLabels(Label label) {
      errorBoxes.errorMain = label;
   } 

   /**
    * This method is called every time a user clicks "Save" on the Customer and appointment pages. It takes all the errorLabels as a parameter from previous input check and clears all the text, so when the new errors
    * appear they are not combined with the old ones to prevent confusion.
    * @param errorMessage Message that indicates program to reset labels. Such as "reset".
    * @param errorLabels Labels to reset.
    */
   public static void checkForEmptyErrorBox(String errorMessage, Label[] errorLabels) {
        for (Label errorLabel : errorLabels) {
            if (errorMessage.equals("reset")){
               errorLabel.setText("");
               errorLabel.setVisible(false);
            }
            else if (errorLabel.getText().equals("null")) {
                errorLabel.setText(errorMessage);
                errorLabel.setVisible(true);
                break;
            }
        }
    }  

   /**
    * This method is called when program needs to display error message to home screen, such as "Appointment Deleted" or "Appointment not deleted".
    * @param error Error Message to display to Home Page.
    */
    public static void mainErrorboxes(String error){
        errorMain.setText(error);
        errorMain.setVisible(true);
        flashMainError();
    }
    
    /**
     * Create a Flash effect for error label on home page, can be called from any class in the program.
     * <p>
     * LAMBDA EXPRESSION:
     * <p>
     * Lambda expressions are used here to provide a concise and readable way to define the behavior of each KeyFrame in the Timeline, without the need for a separate class or method.
     *
     */
    public static void flashMainError(){
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.1), e -> errorMain.setVisible(false)),
            new KeyFrame(Duration.seconds(0.2), e -> errorMain.setVisible(true)),
            new KeyFrame(Duration.seconds(0.3), e -> errorMain.setVisible(false)),
            new KeyFrame(Duration.seconds(0.4), e -> errorMain.setVisible(true))
        );
        timeline.setCycleCount(3); 
        timeline.play();
    }
 
}
