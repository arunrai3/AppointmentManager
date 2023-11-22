package dataStorage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This class handles, stores, and manipulates data for all Contact objects used through out the program.
 * @author Arun Rai
 */
public class allContacts {
    
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();;

    /**
     * Method is called when program is iterating through each row during a query to the SQL database. Each iteration calls this method and adds the contact to the allContacts Observable List.  
     * @param newContact Contact to be added to "allContacts".
     */
    public static void addContact(Contact newContact){
        allContacts.add(newContact);    
    }
    
    /**
     * When data is updated or program needs to refresh data, this method is called and removes all data from allContacts list, preparing it for a new query.
     */   
    public static void clearContacts() {
        allContacts.clear();
    }

    /**
     * @return allContacts Observable List
     */
    public static ObservableList<Contact> getContacts() {
        return allContacts;
    }
    
    /**
     * Method is called from the initialize method in the "appointment_controller" file. Uses the data from Observable List returned in this method to fill in values for Contact ComboBox so user can select a Contact Name.
     * @return a list of all Contact Names
     */
    public static ObservableList<String> getContactNames(){
        ObservableList<String> thelist = FXCollections.observableArrayList();
        for (Contact contact : allContacts){
            thelist.add(contact.getContactName());            
        }
        return thelist;        
    }  
    
    /**
     * Iterates through all Contacts, finds a Contact with matching Contact ID as the one passed in, and returns Contact Name for that Contact.
     * @param contact_id The Contact, program is finding Contact Name for.
     * @return if Object in allContatcs has same Contact ID as the one passed in, than it returns contact name for that contact. Else it returns "N/A".
     */
    public static String getContactNameWithId(int contact_id){
        for(Contact contact : allContacts){
            if(contact.getContactId() == contact_id){
                return contact.getContactName();
            }
        }
        return "N/A";
    }    
    
    /**
     * Iterates through all Contacts, finds a Contact with matching Contact Name as the one passed in, and returns Contact ID for that Contact.
     * @param contactName The Contact, program is finding Contact ID for.
     * @return if Object in allContatcs has same contact name as the one passed in, than it returns Contact ID for that contact. Else it returns "N/A".
     */
    public static int getContactIdWithName(String contactName){
        for (Contact contact : allContacts){
            if (contact.getContactName().equals(contactName)){
                return contact.getContactId();
            }            
        }
        return -1;        
    }    
}
