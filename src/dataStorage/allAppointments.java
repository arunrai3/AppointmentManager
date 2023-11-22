package dataStorage;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;
import java.time.Instant;
import utility.time_zones;


/**
 * This class handles, stores, and manipulates data for all Appointment objects used through out the program.
 * @author Arun Rai
 */
public class allAppointments {
    
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    
    /**
     * Method is called when program is iterating through each row during a query to the SQL database. Each iteration calls this method and adds the appointment to the allAppointment Observable List.  
     * @param newAppointment Appointment to be added to "allAppointments".
     */
    public static void addAppointment(Appointment newAppointment){
        allAppointments.add(newAppointment);
    }
    

    /**
     * @return allAppointments Observable List
     */
    public static ObservableList<Appointment> getAppointments(){
        return allAppointments;
    }
    
    /**
     * When data is updated or program needs to refresh data, this method is called and removes all data from allAppointments list, preparing it for a new query.
     */
    public static void clearAllAppointments() {
        allAppointments.clear();
    }
    
    /**
     * Can not delete a Customer that still has Appointments open, this method validates that. Method is called when user selects a customer from home page and clicks "Delete Customer". Passes the Customer ID of the Customer selected, to 
     * this method where the program iterates through each appointment in the database. If any of the appointments have the Customer ID as the one passed in, than it adds the Appointment ID of that Appointment to a List.
     * @param customer_id Customer we are checking to see if has Appointments.
     * @return a list Appointment IDs that belong to the Customer ID passed in (can be null).
     */
    public static List<Integer> checkIfCustomerHasAppointments(int customer_id){
        List<Integer> appointmentsforCustomer = new ArrayList<>();
        for (Appointment appointment : allAppointments){
            if(appointment.getCustomerId() == customer_id){
                appointmentsforCustomer.add(appointment.getAppointmentId());
            }    
        }
        return appointmentsforCustomer;
    }

    /**
     * Iterates through all Appointments, stores the highest # primary key. After done iterating through list it adds 1 to the highest stored primary key, giving a unique primary key.
     * @return The primary key for new Appointment being added in the "Add Appointment" page. 
     */
    public static int getPrimaryKey(){
        int primary_key = 0;
        for (Appointment appointment : allAppointments){
            if(appointment.getAppointmentId() > primary_key){
                primary_key = appointment.getAppointmentId();                
            }
        }
        return primary_key + 1;        
    }
    
    /**
     * Method is called after a user clicks save on the "Add/Modify Appointment" page, Verifies that the appointment times the user inputted are not currently at the same time as another Appointment that Customer has scheduled. Iterates
     * through each appointment, then finds appointments that match the Customer ID passed in. It compares the start and end time of those Appointments with the start and end time that were passed in.
     * @param startTime Start Time of Appointment that is being evaluated.
     * @param endTime End Time of Appointment that is being evaluated.
     * @param customer_id Customer we are checking to see if has any Appointments during "startTime" and "endTime".
     * @param appointmentId Appointment that is being added or modified.
     * @return null if no overlapping Appointments, else it will return the appointment that is overlapping with start and end time passed in.
     */
    public static Appointment checkIfNotWithinAnotherAppointment2(Timestamp startTime,Timestamp endTime, int customer_id, int appointmentId){
        for (Appointment appointment : allAppointments){
            if((appointment.getCustomerId() == customer_id) && (appointmentId != appointment.getAppointmentId())){
                if(startTime.before(appointment.getEnd()) && endTime.after(appointment.getStart())){
                    return appointment;
                }                
            }                        
        }
        return null;
    }
    
    /**
     * Method is called from "Reports page" when user wants to view appointment for a certain Contact. Contact Name is passed in then program iterates through all Appointments finding the Appointments that have the same Contact Name
     * and adds them to an Observable List.
     * @param contactName Contact that we are finding Appointments for.
     * @return Observable List of Appointments that belong to the Contact Name passed in.
     */
    public static ObservableList<Appointment> getContactAppointments(String contactName){
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        for(Appointment appointment : allAppointments){
            if(appointment.getContactName().equals(contactName)){
                list.add(appointment);
            }
        }
        return list;
    }
    
    /**
     * Method is called from "Reports page" when user wants to view appointments for a certain Customer. Customer Name is passed in then program iterates through all Appointments finding the Appointments that have the same Customer Name
     * and adds them to an Observable List.
     * @param customerName Customer that we are finding Appointments for.
     * @return Observable List of Appointments that belong to the Customer Name passed in.
     */
    public static ObservableList<Appointment> getCustomerAppointments(String customerName){
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        for(Appointment appointment : allAppointments){
            if(appointment.getCustomerName().equals(customerName)){
                list.add(appointment);
            }
        }
        return list;
    }

    /**
     * This method is called from the "Reports page". Used to populate the ComboBox so the user can select what type of Appointments they want to view. Iterates through each Appointment, and for each Appointment type the program checks
     * if it already has that type in the list. If it does then it won't add it to prevent duplicates. Else it will add it to the list.
     * @return A list of all types of Appointments.
     */
    public static ObservableList<String> getTypes(){
        ObservableList<String> list = FXCollections.observableArrayList();
        for(Appointment appointment : allAppointments){
            if(!list.contains(appointment.getType())){
                list.add(appointment.getType());
            }
        }
        return list;
    }
    
    /**
     * This method is called from the "Reports page". User will select what type of Appointments they want to view and for what month. It will display all the matching Appointments and include the number of Appointments
     * that met the parameters the user entered. Also accepts "null" values so if the user just wants to view the type but for a specific month and vice versa that will work as well.
     * @param type Type of Appointment that program is finding appointments for.
     * @param month Month that program is finding appointments for.
     * @return An Observable List of Appointments that match the type and month user entered.
     */
    public static ObservableList<Appointment> getTypeMonthAppointments(String type, String month){
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        for(Appointment appointment : allAppointments){
            String appointmentMonthNumber = appointment.getTableStartDate().substring(5, 7);
            String[] months = {"January", "February", "March", "April", "May", "June",
                                "July", "August", "September", "October", "November", "December"};
            String appointmentMonth = months[Integer.parseInt(appointmentMonthNumber) - 1];            

            if((type == null || type.equals(" ")) && (month == null || month.equals(" "))){
                return list;
            } else if((type != null && !type.equals(" ")) && (month == null || month.equals(" "))){
                if(appointment.getType().equals(type)){
                    list.add(appointment);
                }
            } else if((type == null || type.equals(" ")) && (month != null && !month.equals(" "))){
                if(appointmentMonth.equals(month)){
                    list.add(appointment);
                }            
            } else if(type != null && month != null && !type.equals(" ") && !month.equals(" ")){
                if(appointmentMonth.equals(month) && appointment.getType().equals(type)){
                    list.add(appointment);
                }
            }
        }
        return list;        
    }
   
    /**
     * This method is called from the "Appointment By Week" and "Appointment by Month" page. It Accepts the duration that signals which page it was called from, so "week" or "month". Then Accepts the type which signals for if you want to view
     * past, present, or future for your duration. Then collects the Timestamp of the current UTC time, and uses that Timestamp to find the rest of the variables required to evaluate the conditional statement. Such as, Start of next week,
     * Start of this week, etc. Then iterates through all Appointments and adds all Appointments that fall within desired duration and type.
     * @param duration Duration that program is finding appointments form, Examples "week" or "month".
     * @param type Type that program is finding appointments for, Examples "Current", "Previous", "Upcoming".
     * @return Observable List of Appointments that are within the duration and type passed in the method.
     */
    public static ObservableList<Appointment> getDurationAppointments (String duration, String type){
        ObservableList<Appointment> list = FXCollections.observableArrayList();
        Instant currentInstant = Instant.now();
        Timestamp currentTimestamp = java.sql.Timestamp.from(currentInstant);
        Timestamp utcTimestamp = java.sql.Timestamp.valueOf(currentInstant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
        Timestamp startOfDurationTimestamp = null;
        Timestamp startOfDurationAfterTimestamp = null;
        
        
        if(type.equals("Current")){
            if(duration.equals("week")){
                startOfDurationTimestamp = time_zones.getStartOfWeekTimestamp(currentTimestamp);
                startOfDurationAfterTimestamp = time_zones.getStartOfNextWeekTimestamp(currentTimestamp);
            } else {
                startOfDurationTimestamp = time_zones.getStartOfMonthTimestamp(currentTimestamp);
                startOfDurationAfterTimestamp = time_zones.getStartOfNextMonthTimestamp(currentTimestamp);
            }
        } else if(type.equals("Previous")){
            if(duration.equals("week")){
                startOfDurationTimestamp = time_zones.getStartOfPreviousWeekTimestamp(currentTimestamp);
                startOfDurationAfterTimestamp = time_zones.getStartOfWeekTimestamp(currentTimestamp);
            } else {
                startOfDurationTimestamp = time_zones.getStartOfPreviousMonthTimestamp(currentTimestamp);
                startOfDurationAfterTimestamp = time_zones.getStartOfMonthTimestamp(currentTimestamp);                
            }
        } else if(type.equals("Upcoming")){
            if(duration.equals("week")){
                startOfDurationTimestamp = time_zones.getStartOfNextWeekTimestamp(currentTimestamp);
                startOfDurationAfterTimestamp = time_zones.getStartOfNextWeekTimestamp(startOfDurationTimestamp);
            } else {
                startOfDurationTimestamp = time_zones.getStartOfNextMonthTimestamp(currentTimestamp);
                startOfDurationAfterTimestamp = time_zones.getStartOfNextMonthTimestamp(startOfDurationTimestamp);
            }
        }
        for(Appointment appointment : allAppointments){
            Timestamp appointmentTimestamp = appointment.getLocalStart();
            if ((appointmentTimestamp.after(startOfDurationTimestamp) || appointmentTimestamp.equals(startOfDurationTimestamp)) && appointmentTimestamp.before(startOfDurationAfterTimestamp)) {
                list.add(appointment);
            }
        }
        return list;
    }

    /**
     * This method is ran every time a user successfully logs in. Used to notify user if their are any upcoming Appointments. First collects the current UTC time, then iterates through all Appointments 
     * looking for Appointments that are within 15 minutes of that current UTC time.
     * @return Observable List of Appointments.
     */
    public static Appointment checkWithin15Minutes(){
        Instant currentInstant = Instant.now();
        Timestamp currentTimestamp = java.sql.Timestamp.from(currentInstant);
        Timestamp utcTimestamp = java.sql.Timestamp.valueOf(currentInstant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
        for(Appointment appointment : allAppointments){
            long timeDifferenceSeconds = (appointment.getStart().getTime() - utcTimestamp.getTime()) / 1000;
            if (timeDifferenceSeconds > 0 && timeDifferenceSeconds <= 900) {
                return appointment;
            }            
        }        
        return null;
    }
}
