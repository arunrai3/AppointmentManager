package dataStorage;
import java.sql.Timestamp;


/**
 * This class handles, stores, and manipulates data for individual Customers.
 * @author Arun Rai
 */
public class Customer {
    private int customer_id;
    private String customer_name;
    private String address;
    private String postal_code;
    private String phone_number;
    private Timestamp create_date;
    private String created_by;
    private Timestamp last_update;
    private String last_updated_by;
    private int division_id;
    private String country;
    private String division;
    
    /**
     * Constructor for Customer Object. Called from SQL_queries for every row received from customers table in database.
     * @param customer_id ID of Customer.
     * @param customer_name Name of Customer.
     * @param address Address of Customer.
     * @param postal_code Postal Code of Customer.
     * @param phone_number Phone Number of Customer.
     * @param create_date Date Customer was created.
     * @param created_by User who created Customer.
     * @param last_update Date Customer was last updated.
     * @param last_updated_by User who last updated Customer.
     * @param division_id Division ID of Customer.
     */
    public Customer(int customer_id, String customer_name, String address, String postal_code, String phone_number, Timestamp create_date, String created_by, Timestamp last_update, String last_updated_by, int division_id){
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.address = address;
        this.postal_code = postal_code;
        this.phone_number = phone_number;
        this.create_date = create_date;
        this.created_by = created_by;
        this.last_update = last_update;
        this.last_updated_by = last_updated_by;
        this.division_id = division_id;
    }

    /**
     * @return The customer Id. 
     */
    public int getCustomerId() {
        return customer_id;
    }

    /**
     * @return The Customer Name. 
     */
    public String getCustomerName() {
        return customer_name;
    }

    /**
     * @return The Address for customer. 
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return The postal code for customer. 
     */
    public String getPostalCode() {
        return postal_code;
    }

    /**
     * @return The phone number for customer. 
     */
    public String getPhoneNumber() {
        return phone_number;
    }

    /**
     * @return The Time and Date the Customer was created as a Timestamp in UTC Time. 
     */
    public Timestamp getCreateDate() {
        return create_date;
    }

    /**
     * @return Which user created the customer. 
     */
    public String getCreatedBy() {
        return created_by;
    }

    /**
     * @return The Time and Date the Customer was last updated as a Timestamp in UTC Time. 
     */
    public Timestamp getLastUpdate() {
        return last_update;
    }
    
    /**
     * @return The Time and Date the Customer was last updated as a Timestamp in UTC Time. 
     */
    public String getLastUpdatedBy() {
        return last_updated_by;
    }
    /**
     * @return The Division ID for customer. 
     */
    public int getDivisionId() {
        return division_id;
    }

    /**
     * Iterates through all divisions and finds the row with matching division ID and then returns country_ID for that row. Then takes that country_ID and iterates through all countries and finds a matching ID. Then returns country name for that row.
     * @return The country that is associated with the division Id. 
     */    
    public String getCountry(){
        int country_id = allFirst_level_divisions.getCountryIdWithDivisionId(division_id);
        country = allCountries.getCountryNameWithID(country_id);
        return country;
    }
    
    /**
     * Iterates through all divisions and finds the row with matching division ID and then returns the name for that row.
     * @return The division name. 
     */
    public String getDivision(){
        division = allFirst_level_divisions.getDivisionNameWithDivisionID(division_id);
        return division;
    }

    
}
