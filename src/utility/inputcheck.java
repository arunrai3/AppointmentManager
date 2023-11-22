package utility;

import Main.Main;
import controllers.notification_error;
import dataStorage.Appointment;
import static dataStorage.allAppointments.checkIfNotWithinAnotherAppointment2;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * This is a utility class that provides methods to check user input on certain text fields.
 * @author Arun Rai
 */
public class inputcheck {
         
    /**
     * After a user clicks save on the Customer or Appointment pages it sends certain TextFields that are supposed to contain strings to this method. Checks if a string input from a TextField is valid based on several criteria, such as length and allowed characters.
     * Prints errors to screen using the label parameter indicating invalid data input to the user.
     * @param text String to evaluate.
     * @param outputtext Name of variable that did not pass evaluation so user knows which input did not pass.
     * @param label Label to apply Error Message to on GUI.
     * @return If input is not valid it will return "false_" indicating invalid data, else if the data is valid it will return the string in the TextField.
     */
    public static String checkString (TextField text, String outputtext, Label label){
        String varname = text.getText();
        if (varname.length() > 50) {
            if (varname.matches("[a-zA-Z \\-]*")) {
                if (varname.matches(".*[a-zA-Z].*")) {
                    label.setText(outputtext + " must be less than 50 characters.");
                    label.setVisible(true);     
                    return "false_";
                } else {
                    label.setText(outputtext + " must contain at least one letter. Must be less than 50 characters.");
                    label.setVisible(true);     
                    return "false_";
                }
            } else {
                if (varname.matches(".*[a-zA-Z].*")) {
                    label.setText(outputtext + " can only contain letters, spaces, and dashes. Also must be less than 50 characters.");
                    label.setVisible(true);     
                    return "false_";
                } else {
                    label.setText(outputtext + " must contain at least one letter. Only letters, spaces, and dashes are allowed. Must be less than 50 characters.");
                    label.setVisible(true);     
                    return "false_";
                }
            }
        } else {
            if (varname.matches("[a-zA-Z \\-]*")) {
                if (varname.matches(".*[a-zA-Z].*")) {
                    return varname;
                } else {
                    label.setText(outputtext + " must contain at least one letter.");
                    label.setVisible(true);     
                    return "false_";
                }
            } else {
                if (varname.matches(".*[a-zA-Z].*")) {
                    label.setText(outputtext + " can only contain letters, spaces, and dashes.");
                    label.setVisible(true);     
                    return "false_";
                } else {
                    label.setText(outputtext + " must contain at least one letter. Only letters, spaces, and dashes are allowed.");
                    label.setVisible(true);     
                    return "false_";
                }
            }
        }
    }
    
    /**
     * After a user clicks save on the Customer or Appointment pages it sends certain TextFields that are supposed to contain phone numbers to this method. Checks if a string input from a TextField is valid based on several criteria, such as length and allowed characters.
     * Prints errors to screen using the label parameter indicating invalid data input to the user.
     * @param text Phone Number to evaluate.
     * @param outputtext Name of variable that did not pass evaluation so user knows which input did not pass.
     * @param label Label to apply Error Message to on GUI.
     * @return If data is invalid it returns "invalid number_", else if it is valid it will return the phone number user entered in TextField.
     */
    public static String checkPhonenumber (TextField text, String outputtext, Label label) {
        String phoneNumber = text.getText();
        if (phoneNumber.length() < 50) {        
            if (phoneNumber.matches("^[0-9-]*$")) {
                if (phoneNumber.replaceAll("[^0-9]", "").length() >= 10){
                    return phoneNumber;
                } else {
                    label.setText(outputtext + " must contain at least 10 digits.");
                    label.setVisible(true);
                    return "invalid number_";
                }
            } else {
                if (phoneNumber.replaceAll("[^0-9]", "").length() >= 10){
                    label.setText(outputtext + " can contain only digits and hyphens.");
                    label.setVisible(true);
                    return "invalid number_";
                }else {
                    label.setText(outputtext + " must contain at least 10 digits. Only digits and hyphens are allowed.");
                    label.setVisible(true);
                    return "invalid number_";
                }
            }
        } else {
            if (phoneNumber.matches("^[0-9-]*$")) {
                if (phoneNumber.replaceAll("[^0-9]", "").length() >= 10) {
                    label.setText(outputtext + " must be less than 50 characters.");
                    label.setVisible(true);
                    return "invalid number_";
                } else {
                    label.setText(outputtext + " must contain at least 10 digits. Also must be less than 50 characters.");
                    label.setVisible(true);
                    return "invalid number_";                                        
                }                                                
            } else {
                if (phoneNumber.replaceAll("[^0-9]", "").length() >= 10) {                
                    label.setText(outputtext + " can only contain only digits and hyphens. Also must be less than 50 characters.");
                    label.setVisible(true);
                    return "invalid number_";
                } else {
                    label.setText(outputtext + " must contain at least 10 digits. Only digits and hyphens are allowed. Also must be less than 50 characters.");
                    label.setVisible(true);
                    return "invalid number_";
                }                                        
            }
        }      
    }

    /**
     * After a user clicks save on the Customer or Appointment pages it sends certain TextFields that are supposed to contain Postal code or addresses to this method. Checks if a string input from a TextField is valid based on several criteria, such as length and allowed characters.
     * Prints errors to screen using the label parameter indicating invalid data input to the user.
     * @param text Postal Code or Address to evaluate.
     * @param outputtext Name of variable that did not pass evaluation so user knows which input did not pass.
     * @param label Label to apply Error Message to on GUI.
     * @param type Character that are allowed in Postal Code or Address.
     * @return f data is invalid it returns "invalid postal code_", else if it is valid it will return the postal code or address user entered in TextField.
     */
    public static String checkPostalcodeOrAddress(TextField text, String outputtext, Label label, String type) {
        String postalCodeOrAddress = text.getText();
        String comma_hyphen;
        int length;
        String lengthString;
        if (outputtext.equals("Postal Code")){
            comma_hyphen = "-";
            length = 50;
            lengthString = "50";
        }else {
            comma_hyphen = ", ";
            length = 100;
            lengthString = "100";
        }
        if (postalCodeOrAddress.length() < length) {        
            if (postalCodeOrAddress.matches("[a-zA-Z0-9" + comma_hyphen +  "]*")) {
                if (postalCodeOrAddress.matches(".*[a-zA-Z0-9].*")) {
                    return postalCodeOrAddress;
                } else {
                    label.setText(outputtext + " must contain at least one letter or number.");
                    label.setVisible(true);     
                    return "invalid postal code_";
                }
            } else {
                if (postalCodeOrAddress.matches(".*[a-zA-Z0-9].*")) {
                    label.setText(outputtext + "  can only contain " + type + ",letters and/or numbers.");
                    label.setVisible(true);     
                    return "invalid postal code_";
                } else {
                    label.setText(outputtext + "  must contain at least one letter or number. Only " + type + ",letters and/or numbers are allowed");
                    label.setVisible(true);     
                    return "invalid postal code_";
                }
            }
        } else {
            if (postalCodeOrAddress.matches("[a-zA-Z0-9" + comma_hyphen +  "]*")) {
                 if (postalCodeOrAddress.matches(".*[a-zA-Z0-9].*")) {
                    label.setText(outputtext + " must be less than " + lengthString + " characters.");
                    label.setVisible(true);     
                    return "invalid postal code_";
                } else {
                    label.setText(outputtext + " must contain at least one letter or number. Also must be less than " + lengthString + " characters.");
                    label.setVisible(true);     
                    return "invalid postal code_";
                }
           } else {
                if (postalCodeOrAddress.matches(".*[a-zA-Z0-9].*")) {
                    label.setText(outputtext + " can only contain " + type + ",letters and/or numbers. Also must be less than " + lengthString + " characters.");
                    label.setVisible(true);     
                    return "invalid postal code_";
                } else {
                    label.setText(outputtext + " can only contain " + type + ",letters and/or numbers. Must contain at least one letter or number. Also must be less than " + lengthString + " characters.");
                    label.setVisible(true);     
                    return "invalid postal code_";
                }
            }
        }                   
    }
    
    /**
     * After user clicks save on the Customer Page, this method will check if the Country/First Division Combo boxes are empty. If it is empty it will print errors to the screen using the label parameter indicating that the combo boxes can not be empty.
     * @param boxvalue Country or Division to evaluate.
     * @param outputtext Name of variable that did not pass evaluation so user knows which input did not pass.
     * @param label Label to apply Error Message to on GUI.
     * @return If Combo Box is empty it will return "not valid country_division", else it will return the value in the ComboBox.
     */
    public static String checkCountryOrDivision(String boxvalue, String outputtext, Label label){
        if (boxvalue == null || boxvalue.equals(" ")) {
            label.setText("Please select a " + outputtext);
            label.setVisible(true);     
            return "not valid country_division";
        }
        return boxvalue;    
    }
    
    /**
     * After user clicks save on Appointment Page, The start and end times the user entered for that appointment are passed to this method. They are then evaluated against the Eastern Time business hours. If appointment
     * falls outside the business hours than it print an error to screen using the label parameter.
     * @param startTime Start Time of Appointment being evaluated.
     * @param endTime End Time of Appointment being evaluated.
     * @param label Label to apply Error Message to on GUI.
     * @return If Appointment is within business hours it will return "Within business hours", else it will return "Not within business hours".
     */
    public static String checkIfWithinBuisnessHours(Timestamp startTime, Timestamp endTime, Label label){
        ZoneId utcZoneId = ZoneOffset.UTC;
        
        ZoneId easternZoneId = ZoneId.of("America/New_York");
        
        LocalDateTime startlocalDateTime = startTime.toLocalDateTime().atZone(utcZoneId).withZoneSameInstant(easternZoneId).toLocalDateTime();
        LocalDateTime endlocalDateTime = endTime.toLocalDateTime().atZone(utcZoneId).withZoneSameInstant(easternZoneId).toLocalDateTime();


        int startHour = startlocalDateTime.getHour();
        int endHour = endlocalDateTime.getHour();
        
        LocalDate startDate = startlocalDateTime.toLocalDate();
        LocalDate endDate = endlocalDateTime.toLocalDate();    
        
        
        if(startDate.equals(endDate)){
            if(startHour >= 8 && startHour < 22 && endHour >= 8 && endHour < 22){
                return "Within buisness hours";
            }
        }        
        label.setText("The Appointment time block has to be within buiness hours. 8:00 a.m. to 10:00 p.m. EST, including weekends.");
        label.setVisible(true);

        return "Not within buisness hours";                    
    }
    
    /**
     * After user clicks save on Appointment Page, The start and end times the user entered for that appointment are passed to this method. Method will evaluate both times and verify the start time is before the end time. If appointment
     * start time is after end time, that error is printed to screen using the label parameter.
     * @param startTime Start Time of Appointment being evaluated.
     * @param endTime End Time of Appointment being evaluated.
     * @param label Label to apply Error Message to on GUI.
     * @return If Start Time is before End Time returns "good", else returns "not good".
     */
    public static String checkIfStratGreaterThanEnd(Timestamp startTime, Timestamp endTime, Label label){
        if (startTime.before(endTime)) {
            return "good";
        } else {
            label.setText("Start Time has to be before End Time.");
            label.setVisible(true);
            return "not good";
        }        
    }
    
    /**
     * After user clicks save on Appointment Page, The start and end times the user entered for that appointment are passed to this method. These times are then passed to another method in the "allAppointments" class to verify their
     * are no appointments currently within the same time for that customer. If their are appointments within that time, a pop up is loaded indicating the error to user.
     * @param startTime Start Time of Appointment being evaluated.
     * @param endTime End Time of Appointment being evaluated.
     * @param customerId The Customer, program is checking Appointments for.
     * @param button Date required to load pop up if their are overlapping Appointments.
     * @param appointmentId Appointment that is being added or modified.
     * @return If their are appointments within the same time method returns "Overlapping", or else returns "Not overlapping".
     */
    public static String checkIfNotWithinAnotherAppointment(Timestamp startTime, Timestamp endTime, int customerId, Button button, int appointmentId){
        
        Appointment OverlappingAppointment = checkIfNotWithinAnotherAppointment2(startTime,endTime,customerId, appointmentId);
        
        if (OverlappingAppointment != null){
            Main.handleSceneChange("notification_error.fxml", "null", "null", button,"yes", null, OverlappingAppointment,null,"dont hide");            
            return "Overlapping";
        } else {
            return "Not overlapping";
        }
    }
    
    /**
     * Checks whether the input fields for start hour, start minute, end hour and end minute are not null. If they one or more are null, errors are printed to screen.
     * @param starthour Start Hour
     * @param startmin Start Minute
     * @param endhour End Hour
     * @param endmin End Minute
     * @param startlabel Label to apply Error Message for "starthour" and "startmin".
     * @param endlabel Label to apply Error Message for "endhour" and "endmin".
     * @return if no values are null "valid" is returned. If one more values are null than "not valid" is returned.
     */
    public static String checkHourAndMinBoxes(String starthour, String startmin, String endhour, String endmin, Label startlabel, Label endlabel){
        String time_input = "valid";
        if (starthour == null  || starthour.equals(" ") || startmin == null || startmin.equals(" ")){
            startlabel.setText("Both Start Hour and Minute cannot be null");
            startlabel.setVisible(true);
            time_input = "not valid";
        }
        if (endhour == null || endhour.equals(" ") || endmin == null || endmin.equals(" ")){
            endlabel.setText("Both End Hour and Minute cannot be null");
            endlabel.setVisible(true);
            time_input = "not valid";
        }
        return time_input;
    }
    
    /**
     * Checks whether the start or end DatePicker boxes are null or not. If null, errors are printed to screen.
     * @param date Date being evaluated.
     * @param label Label to apply Error Message to on GUI.
     * @param outputtext Error message to print to GUI.
     * @return If DatePicker box is null, "invalid date" is returned, else "valid date" is returned.
     */
    public static String checkNullDate(LocalDate date, Label label, String outputtext){
        if(date == null){
            label.setText(outputtext);
            label.setVisible(true);
            return "invalid date";
        } else {
            return "valid date";
        }
    }
    
    /**
     * After user clicks save on the Appointment Page, this method will check if the Customer/User Combo boxes are empty. If it is empty it will print errors to the screen using the label parameter indicating that the combo boxes can not be empty.
     * @param id Customer or User ID being evaluated.
     * @param label Label to apply Error Message to on GUI.
     * @param outputtext Error message to print to GUI.
     * @return If Combo Box is empty it will return "invalid ID", else it will return "valid ID".
     */
    public static String checkNullCustomerOrUser(Integer id, Label label, String outputtext){
        if(id == null){
            label.setText(outputtext);
            label.setVisible(true);
            return "invalid ID";
        } else {
            return "valid ID";
        }
    }            
}
