package dataStorage;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * This class handles, stores, and manipulates data for individual Appointments.
 * @author Arun Rai
 */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private Timestamp localStart;
    private Timestamp localEnd;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;
    private String customerName;
    
    private Timestamp startDate;
    private Timestamp endDate;
    private Timestamp startTime;
    private Timestamp endTime;
    
    private String tableStartDate;
    private String tableEndDate;
    private String tableStartTime;
    private String tableEndTime;

    

    
    
    /**
     * Constructor for Appointment Object. Called from SQL_queries for every row received from appointments table in database.
     * @param appointmentId ID of Appointment.
     * @param title Title of Appointment.
     * @param description Description of Appointment.
     * @param location Location of Appointment.
     * @param type Type of Appointment
     * @param start Start time of Appointment in UTC Time.
     * @param end End time of Appointment in UTC Time.
     * @param createDate Date Appointment was created.
     * @param createdBy User that created Appointment.
     * @param lastUpdate Date Appointment was last updated.
     * @param lastUpdatedBy User that last Updated Appointment.
     * @param customerId Customer ID of Appointment.
     * @param userId User ID of Appointment.
     * @param contactId Contact ID of Appointment.
     * @param localStart Start time of Appointment in local time set on computer. 
     * @param localEnd End Time of Appointment in local time set on computer.
     */
    public Appointment(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int customerId, int userId, int contactId, Timestamp localStart, Timestamp localEnd) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.localStart = localStart;
        this.localEnd = localEnd;
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(localStart);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        this.startDate = new Timestamp(cal.getTimeInMillis());

        cal = Calendar.getInstance();
        cal.setTime(localEnd);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        this.endDate = new Timestamp(cal.getTimeInMillis());

        cal = Calendar.getInstance();
        cal.setTime(localStart);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 1970);
        this.startTime = new Timestamp(cal.getTimeInMillis());

        cal = Calendar.getInstance();
        cal.setTime(localEnd);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 1970);
        this.endTime = new Timestamp(cal.getTimeInMillis());                                  
    }
    
    /**
     * @return the Appointment Id
     */    
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * @return the Title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @return the Description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @return the Location
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * @return the Type
     */
    public String getType() {
        return type;
    }
    
    /**
     * 
     * @return the Start Time and Date of Appointment as a Timestamp in UTC Time
     */
    public Timestamp getStart() {
        return start;
    }
    
    /**
     * @return the End Time and Date of Appointment as a Timestamp in UTC Time
     */
    public Timestamp getEnd() {
        return end;
    }
    
    /**
     * @return the Start Time and Date of Appointment as a Timestamp in Local Time
     */    
    public Timestamp getLocalStart() {
        return localStart;
    }
    
    /**
     * @return the End Time and Date of Appointment as a Timestamp in Local Time
     */
    public Timestamp getLocalEnd() {
        return localEnd;
    }
    
    /**
     * @return the Time and Date of when the Appointment was created as a Timestamp in UTC Time
     */
    public Timestamp getCreateDate() {
        return createDate;
    }
    
    /**
     * @return the user who created the appointment
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * @return the Time and Date of when the Appointment was last updated as a Timestamp in UTC Time
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    
    /**
     * @return the user who last updated the appointment
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    /**
     * @return the Customer Id
     */
    public int getCustomerId() {
        return customerId;
    }
    
    /**
     * @return the User Id
     */
    public int getUserId() {
        return userId;
    }
    
    /**
     * @return the Contact Id
     */
    public int getContactId() {
        return contactId;
    }
    
    /**
     * Iterates through all Contacts and finds one with matching Contact Id than returns name associated with that row.
     * @return the Contact Name
     */    
    public String getContactName(){
        contactName = allContacts.getContactNameWithId(contactId);
        return contactName;
    }
    
    /**
     * Iterates through all Customers and finds one with matching Customer Id than returns name associated with that row.
     * @return the Contact Name
     */
    public String getCustomerName(){
        customerName = allCustomers.getCustomerNameWithId(customerId);
        return customerName;
    }    
    
    /**
     * @return The Start Date of the appointment without the time in Local Time.
     */
    public Timestamp getStartDate() {
        return startDate;
    }
    
    /**
     * @return the End Date of the appointment without the time in Local Time.
     */
    public Timestamp getEndDate() {
        return endDate;
    }
    
    /**
     * @return the Start Time of the appointment without the date in Local Time.
     */
    public Timestamp getStartTime() {
        return startTime;
    }
    
    /**
     * @return the End Time of the appointment without the date in Local Time.
     */
    public Timestamp getEndTime() {
        return endTime;
    }  
    
    /**
     * @return The Start Date (Local time) in String form so it can be displayed on the table view.
     */
    public String getTableStartDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tableStartDate = sdf.format(startDate);
        return tableStartDate;        
    }    
    
    /**
     * @return The End Date (Local time) in String form so it can be displayed on the table view.
     */
    public String getTableEndDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tableEndDate = sdf.format(endDate);
        return tableEndDate;        
    }       
    
    /**
     * @return The Start Time (Local time) in String form so it can be displayed on the table view.
     */
    public String getTableStartTime(){
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        tableStartTime = sdfTime.format(startTime);
        return tableStartTime;        
    }
    
    /**
     * @return The End Time (Local time) in String form so it can be displayed on the table view.
     */
    public String getTableEndTime(){
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        tableEndTime = sdfTime.format(endTime);
        return tableEndTime;        
    }    
}

