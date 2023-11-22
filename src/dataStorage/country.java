package dataStorage;

import java.sql.Timestamp;

/**
 * This class handles, stores, and manipulates data for individual Countries.
 * @author Arun Rai
 */
public class country {
    private int Country_ID;
    private String Country;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    
    /**
     * Constructor for Country Object. Called from SQL_queries for every row received from countries table in database.
     * @param Country_ID ID of Country
     * @param Country Country Name
     * @param Create_Date Date Country was inserted into database.
     * @param Created_By Who created Country. Auto-Generated.
     * @param Last_Update Date Country was last updated.
     * @param Last_Updated_By Who created Country. Auto-Generated. 
     */
    public country(int Country_ID, String Country, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By) {
        this.Country_ID = Country_ID;
        this.Country = Country;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
    }
    
    /**
     * @return the Country ID.
     */    
    public int getCountry_ID() {
        return Country_ID;
    }

    /**
     * @return The Country Name.
     */    
    public String getCountry() {
        return Country;
    }

    /**
     * @return The Time and Date the Country was created as a Timestamp in UTC Time. 
     */    
    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    /**
     * @return The user who created this country.
     */    
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * @return The Time and Date the Country was Last updated as a Timestamp in UTC Time. 
     */    
    public Timestamp getLast_Update() {
        return Last_Update;
    }

    /**
     * @return The user who last updated this country.
     */    
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }
}
