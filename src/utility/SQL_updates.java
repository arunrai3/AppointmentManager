package utility;

import Main.JDBC;
import static Main.JDBC.connection;
import controllers.login_controller;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * The SQL_updates class contains methods for updating a Customer or an Appointment in the database.
 * @author Arun Rai
 */
public class SQL_updates {

    /**
     * Updates an existing customer in the database, knows which row to update by matching primary key with one passed in (customer_id). This method is called from the Modify Customer screen after a user clicks the "Save" button 
     * and the data the user inputted has been validated.
     * @param customer_id ID of Customer.
     * @param customername Name of Customer.
     * @param address Address of Customer.
     * @param postalcode Postal Code of Customer.
     * @param phonenumber Phone Number of Customer.
     * @param division_id Division ID of Customer.
     */
    public static void updateCustomer(int customer_id, String customername, String address, String postalcode, String phonenumber, int division_id) {
        try{
            Instant currentInstant = Instant.now();
            Timestamp currentTimestamp = Timestamp.from(currentInstant);
            Timestamp utcTimestamp = Timestamp.valueOf(currentInstant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
            JDBC.openConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE client_schedule.customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?");
            stmt.setString(1, customername);
            stmt.setString(2, address);
            stmt.setString(3, postalcode);
            stmt.setString(4, phonenumber);
            stmt.setTimestamp(5, utcTimestamp);
            stmt.setString(6, login_controller.getCurrentUser());
            stmt.setInt(7, division_id);
            stmt.setInt(8, customer_id);
            stmt.executeUpdate();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException e){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }

    /**
     * Updates an existing appointment in the database, knows which row to update by matching primary key with one passed in (appointment_id). This method is called from the Modify Appointment screen after a user clicks the "Save" button 
     * and the data the user inputted has been validated.
     * @param appointment_id ID of Appointment.
     * @param title Title of Appointment.
     * @param description Description of Appointment.
     * @param location Location of Appointment.
     * @param type Type of Appointment.
     * @param startTime Start Time of Appointment.
     * @param endTime End Time of Appointment.
     * @param customer_id Customer ID of Appointment.
     * @param user_id User ID of Appointment.
     * @param contact_id Contact ID of Appointment.
     */
    public static void updateAppointment (int appointment_id, String title, String description, String location, String type, Timestamp startTime, Timestamp endTime, int customer_id, int user_id, int contact_id){
        try{
            Instant currentInstant = Instant.now();
            Timestamp currentTimestamp = Timestamp.from(currentInstant);
            Timestamp utcTimestamp = Timestamp.valueOf(currentInstant.atOffset(java.time.ZoneOffset.UTC).toLocalDateTime());
            JDBC.openConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE client_schedule.appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?");
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, location);
            stmt.setString(4, type);
            stmt.setTimestamp(5, startTime);
            stmt.setTimestamp(6, endTime);
            stmt.setTimestamp(7, utcTimestamp);
            stmt.setString(8, login_controller.getCurrentUser());
            stmt.setInt(9, customer_id);
            stmt.setInt(10, user_id);
            stmt.setInt(11, contact_id);
            stmt.setInt(12, appointment_id);
            stmt.executeUpdate();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException e){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }
}
