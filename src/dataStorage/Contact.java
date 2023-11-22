package dataStorage;

/**
 * This class handles, stores, and manipulates data for individual Contacts.
 * @author Arun Rai
 */
public class Contact {
    private int contact_id;
    private String contact_name;
    private String email;
    
    /**
     * Constructor for Contact Object. Called from SQL_queries for every row received from contacts table in database.
     * @param contact_id ID of Contact.
     * @param contact_name Name of Contact.
     * @param email Email of Contact.
     */
    public Contact(int contact_id, String contact_name, String email){
        this.contact_id = contact_id;
        this.contact_name = contact_name;
        this.email = email;
    }
    
    /**
     * @return the Contact Id
     */       
    public int getContactId() {
        return contact_id;
    }

    /**
     * @return the Contact Name
     */       
    public String getContactName() {
        return contact_name;
    }

    /**
     * @return the email for Contact
     */       
    public String getEmail() {
        return email;
    }    

}
