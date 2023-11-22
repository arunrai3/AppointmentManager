package utility;

import Main.JDBC;
import dataStorage.Appointment;
import dataStorage.Customer;
import java.sql.SQLException;
import static Main.JDBC.connection;
import java.sql.PreparedStatement;

/**
 * The SQL_deletes class contains methods for deleting a Customer or an Appointment from the database.
 * @author Arun Rai
 */
public class SQL_deletes {

    /**
     * Deletes a customer from the database. Method is called from delete confirmation pop up when user confirms the item they want to delete.
     * @param customer Customer to be deleted. 
     */
    public static void deleteCustomer(Customer customer){
        int customer_id = customer.getCustomerId();
        try {
            JDBC.openConnection();
            String query = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, customer_id);
            stmt.executeUpdate();
            stmt.close();
            JDBC.closeConnection();
            errorBoxes.mainErrorboxes("Customer deleted. (Customer ID: " + customer.getCustomerId() + ")");
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }

    /**
     * Deletes an appointment from the database. Method is called from delete confirmation pop up when user confirms the item they want to delete.
     * @param appointment Appointment to be deleted.
     */
    public static void deleteAppointment(Appointment appointment){
        int appointment_id = appointment.getAppointmentId();
        try {
            JDBC.openConnection();
            String query = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, appointment_id);
            stmt.executeUpdate();
            stmt.close();
            JDBC.closeConnection();
            errorBoxes.mainErrorboxes("Appointment deleted. (Appointment ID: " + appointment_id + ", Type: " + appointment.getType() + ")");
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }
}
