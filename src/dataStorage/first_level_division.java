package dataStorage;

import java.util.Date;
import java.sql.Timestamp;

/**
 * This class handles, stores, and manipulates data for individual First Level Divisions.
 * @author Arun Rai
 */
public class first_level_division {
    private int Division_ID;
    private String Division;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;
    private String Last_Updated_By;
    private int Country_ID;
    
    /**
     * Constructor for First Level Division Object. Called from SQL_queries for every row received from first_level_divisions table in database.
     * @param Division_ID ID of Division.
     * @param Division Division Name.
     * @param Create_Date Date Division was added to database.
     * @param Created_By Who created Division. Auto-Generated.
     * @param Last_Update Date Division was last updated.
     * @param Last_Updated_By Who created Division. Auto-Generated.
     * @param Country_ID ID of Country that this Division is a part of.
     */
    public first_level_division(int Division_ID, String Division, Timestamp Create_Date, String Created_By, Timestamp Last_Update, String Last_Updated_By, int Country_ID) {
        this.Division_ID = Division_ID;
        this.Division = Division;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Country_ID = Country_ID;
    }
        
    /**
     * @return the Division ID.
     */      
    public int getDivision_ID() {
        return Division_ID;
    }
    
    /**
     * @return the Division Name.
     */      
    public String getDivision() {
        return Division;
    }
    
    /**
     * @return The Time and Date the Division was created as a Timestamp in UTC Time. 
     */      
    public Timestamp getCreate_Date() {
        return Create_Date;
    }

    /**
     * @return The user who created this Division.
     */      
    public String getCreated_By() {
        return Created_By;
    }
    
    /**
     * @return The Time and Date the Division was last updated as a Timestamp in UTC Time. 
     */       
    public Timestamp getLast_Update() {
        return Last_Update;
    }
    
    /**
     * @return The user who last updated this Division.
     */      
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * @return The Country ID associated with this Division
     */    
    public int getCountry_ID() {
        return Country_ID;
    }
}
