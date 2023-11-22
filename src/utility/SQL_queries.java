package utility;

import Main.JDBC;
import static Main.JDBC.connection;
import dataStorage.Appointment;
import dataStorage.Contact;
import dataStorage.Customer;
import dataStorage.User;
import dataStorage.allAppointments;
import dataStorage.allContacts;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dataStorage.allCountries;
import dataStorage.allCustomers;
import dataStorage.allFirst_level_divisions;
import dataStorage.allUsers;
import dataStorage.country;
import dataStorage.first_level_division;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


/**
 * The SQL_queries class contains methods for running queries and collecting data from tables in the database.
 * @author Arun Rai
 */
public class SQL_queries {

    /**
     *Method is called when program is first loaded or when ever the "refresh" method is ran from "home_controller". Usually to get an updated list after a certain amount of time has passed or changes have been made to the database. Iterates through all countries
     * and adds each one and its columns to an empty list in the allCountries class.
     */
    public static void getCountries(){
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM client_schedule.countries";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Country_ID");
                String name = rs.getString("Country");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                
                country country_query = new country(id, name, createDate, createdBy, lastUpdate, lastUpdatedBy);
                allCountries.addCountry(country_query);
            }
            rs.close();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }    

    /**
     * Method is called when program is first loaded or when ever the "refresh" method is ran from "home_controller". Usually to get an updated list after a certain amount of time has passed or changes have been made to the database. Iterates through all divisions
     * and adds each one and its columns to an empty list in the allFirst_level_divisions class.
     */    
    public static void getDivisions(){
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM client_schedule.first_level_divisions";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int countryId = rs.getInt("COUNTRY_ID");      
            
                first_level_division division = new first_level_division(id, name, createDate, createdBy, lastUpdate, lastUpdatedBy, countryId);
                allFirst_level_divisions.addDivision(division);
            }
            rs.close();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }

    /**
     * Method is called when program is first loaded or when ever the "refresh" method is ran from "home_controller". Usually to get an updated list after a certain amount of time has passed or changes have been made to the database. Iterates through all appointments
     * and adds each one and its columns to an empty list in the allAppointments class.
     */    
    public static void getAppointments() throws ParseException{
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM client_schedule.appointments";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");                
                Timestamp start = new Timestamp(dateFormat.parse(rs.getString("Start")).getTime());
                Timestamp end = new Timestamp(dateFormat.parse(rs.getString("End")).getTime());                
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                
                
                Timestamp localStart = time_zones.convertToTimeStampDataTypeLOCAL(start);
                Timestamp localEnd = time_zones.convertToTimeStampDataTypeLOCAL(end);
                
                
                Appointment appointment = new Appointment(appointmentId, title, description, location, type, start, end, createDate, createdBy, lastUpdate, lastUpdatedBy, customerId, userId, contactId, localStart, localEnd);
                allAppointments.addAppointment(appointment);
            }
            rs.close();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }
    
    /**
     * Method is called when program is first loaded or when ever the "refresh" method is ran from "home_controller". Usually to get an updated list after a certain amount of time has passed or changes have been made to the database. Iterates through all customers
     * and adds each one and its columns to an empty list in the allCustomers class.
     */    
    public static void getCustomers() {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM client_schedule.customers";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String Address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionId = rs.getInt("Division_ID");
            
                Customer customer = new Customer(customerId, customerName, Address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
                allCustomers.addCustomer(customer);
            }
            rs.close();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }
    
    /**
     * Method is called when program is first loaded or when ever the "refresh" method is ran from "home_controller". Usually to get an updated list after a certain amount of time has passed or changes have been made to the database. Iterates through all users
     * and adds each one and its columns to an empty list in the allUsers class.
     */    
    public static void getUsers() {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM client_schedule.users";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Timestamp createDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
            
                User user = new User(userId, userName, password, createDate, createdBy, lastUpdate, lastUpdatedBy);
                allUsers.addUser(user);
            }
            rs.close();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }
    
    /**
     * Method is called when program is first loaded or when ever the "refresh" method is ran from "home_controller". Usually to get an updated list after a certain amount of time has passed or changes have been made to the database. Iterates through all contacts
     * and adds each one its columns to an empty in the allContacts class.
     */    
    public static void getContacts() {
        try {
            JDBC.openConnection();
            String sql = "SELECT * FROM client_schedule.contacts";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactname = rs.getString("Contact_Name");
                String email = rs.getString("Email");
            
                Contact contact = new Contact(contactID, contactname, email);
                allContacts.addContact(contact);
            }
            rs.close();
            stmt.close();
            JDBC.closeConnection();
        } catch (SQLException ex) {
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        } catch (NullPointerException ex){
            errorBoxes.mainErrorboxes("Error while connecting to database. Contact Support.");
        }       
    }
}
