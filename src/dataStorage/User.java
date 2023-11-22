package dataStorage;
import java.sql.Timestamp;
import java.util.Date;

/**
 * This class handles, stores, and manipulates data for individual Users.
 * @author Arun Rai
 */
public class User {
    private int userId;
    private String userName;
    private String password;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    
    /**
     * Constructor for User Object. Called from SQL_queries for every row received from users table in database.
     * @param userId ID of User.
     * @param userName Name of User.
     * @param password Password of User.
     * @param createDate Date User was created.
     * @param createdBy Who created User. Auto-Generated.
     * @param lastUpdate Date User was last updated.
     * @param lastUpdatedBy Who created User. Auto-Generated.
     */
    public User(int userId, String userName, String password, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * @return The User ID.
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * @return The User Name. 
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * @return The Password. 
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * @return The Time and Date the User was created as a Timestamp in UTC Time. 
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @return The user who created this user. 
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * @return The Time and Date the User was last updated as a Timestamp in UTC Time. 
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @return The user who last updated this user.  
     */    
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}    
   