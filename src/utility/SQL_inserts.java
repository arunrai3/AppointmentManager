package utility;

import Main.JDBC;
import java.sql.SQLException;
import static Main.JDBC.connection;
import controllers.login_controller;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.sql.Timestamp;

/**
 * The SQL_inserts class contains methods for inserting a Customer or an Appointment into the database.
 * @author Arun Rai
 */
public class SQL_inserts {

    /**
     * Inserts a new customer into the database. Called from "Add Customer" page. After the user clicks save, if all the data inputted passes the validation tests it is passed into this method.
     * @param customer_id ID of Customer.
     * @param customername Name of Customer.
     * @param address Address of Customer.
     * @param postalcode Postal Code of Customer.
     * @param phonenumber Phone Number of Customer.
     * @param division_id Division ID of Customer.
     */
    public static void insertCustomer (int customer_id, String customername, String address, String postalcode, String phonenumber, int division_id) {
        try {
            
            Instant currentInstant = Instant.now();
            Timestamp currentTimestamp = java.sql.Timestamp.from(currentInstant);
            Timestamp utcTimestamp = java.sql.Timestamp.valueOf(currentInstant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
            JDBC.openConnection();    
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO client_schedule.customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, customer_id);
            stmt.setString(2, customername);
            stmt.setString(3, address);
            stmt.setString(4, postalcode);
            stmt.setString(5, phonenumber);
            stmt.setTimestamp(6,utcTimestamp);
            stmt.setString(7,login_controller.getCurrentUser());
            stmt.setTimestamp(8, utcTimestamp);
            stmt.setString(9,login_controller.getCurrentUser());            
            stmt.setInt(10, division_id);
            stmt.executeUpdate();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException e) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }

    /**
     * Inserts a new appointment into the database. Called from "Add Appointment" page. After the user clicks save, if all the data inputted passes the validation tests it is passed into this method.
     * @param appointment_id ID of Appointment.
     * @param title Title of Appointment.
     * @param description Description of Appointment.
     * @param location Location of Appointment.
     * @param type Type of Appointment.
     * @param startTime Start Time of Appointment.
     * @param endTime End Time of Appointment.
     * @param customer_id Customer ID of Appointment.
     * @param user_id User ID of Appointment.
     * @param contact_id  Contact ID of Appointment.
     */
    public static void insertAppointment (int appointment_id, String title, String description, String location, String type, Timestamp startTime, Timestamp endTime, int customer_id, int user_id, int contact_id) {
        try {
            Instant currentInstant = Instant.now();
            Timestamp currentTimestamp = Timestamp.from(currentInstant);
            Timestamp utcTimestamp = Timestamp.valueOf(currentInstant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
            JDBC.openConnection();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO client_schedule.appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, appointment_id);
            stmt.setString(2, title);
            stmt.setString(3, description);
            stmt.setString(4, location);
            stmt.setString(5, type);
            stmt.setTimestamp(6,startTime);
            stmt.setTimestamp(7,endTime);
            stmt.setTimestamp(8, utcTimestamp);
            stmt.setString(9,login_controller.getCurrentUser());            
            stmt.setTimestamp(10, utcTimestamp);
            stmt.setString(11,login_controller.getCurrentUser());            
            stmt.setInt(12, customer_id);
            stmt.setInt(13, user_id);
            stmt.setInt(14, contact_id);
            stmt.executeUpdate();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException e) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }
}
