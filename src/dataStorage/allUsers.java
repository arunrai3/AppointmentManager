package dataStorage;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class handles, stores, and manipulates data for all User objects used through out the program.
 * @author Arun Rai
 */
public class allUsers {
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * Method is called when program is iterating through each row during a query to the SQL database. Each iteration calls this method and adds the user to the allUsers Observable List.  
     * @param user User to be added to "allUsers".
     */
    public static void addUser(User user) {
        allUsers.add(user);
    }

    /**
     * When data is updated or program needs to refresh data, this method is called and removes all data from allUsers list, preparing it for a new query.
     */
    public static void clearUsers() {
        allUsers.clear();
    }

    /**
     * @return allUsers Observable list
     */
    public static ObservableList<User> getUsers() {
        return allUsers;
    }
    
    /**
     * Method is called from the initialize method in the "appointment_controller" file. Uses the data from Observable List returned in this method to fill in values for User_id ComboBox so user can select a User ID.
     * 
     * @return A list of all User ID's. 
     */
    public static ObservableList<Integer> getUserIds(){
        ObservableList<Integer> thelist = FXCollections.observableArrayList();
        for (User user : allUsers){
            thelist.add(user.getUserId());            
        }
        return thelist;        
    }     
    
    
}
