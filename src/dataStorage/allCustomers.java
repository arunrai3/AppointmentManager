package dataStorage;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This class handles, stores, and manipulates data for all Customer objects used through out the program.
 * @author Arun Rai
 */
public class allCustomers {
    
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    
    /**
     * Method is called when program is iterating through each row during a query to the SQL database. Each iteration calls this method and adds the customer to the allCustomers Observable List.  
     * @param newCustomer Customer to be added to "allCustomers".
     */
    public static void addCustomer(Customer newCustomer){
        allCustomers.add(newCustomer);    
    }
    

    /**
     * @return allCustomers Observable List
     */
    public static ObservableList<Customer> getCustomers(){
        return allCustomers;
    }
    
    /**
     * When data is updated or program needs to refresh data, this method is called and removes all data from allCustomers list, preparing it for a new query.
     */
    public static void clearAllCustomers() {
        allCustomers.clear();
    }        
    
    /**
     * Iterates through all customers, stores the highest # primary key. After done iterating through list it adds 1 to the highest stored primary key, giving a unique primary key.
     * @return The primary key for new customer being added in the "Add Customer" page. 
     */
    public static int getPrimaryKey(){
        int primary_key = 0;
        for (Customer customer : allCustomers){
            if(customer.getCustomerId() > primary_key){
                primary_key = customer.getCustomerId();
                
            }
        }
        return primary_key + 1;        
    }

    /**
     * Method is called from the initialize method in the "appointment_controller" file. Uses the data from Observable List returned in this method to fill in values for CustomerID ComboBox so user can select a Customer ID.
     * @return A list of all Customer ID's.
     */
    public static ObservableList<Integer> getCustomerIds(){
        ObservableList<Integer> thelist = FXCollections.observableArrayList();
        for (Customer customer : allCustomers){
            thelist.add(customer.getCustomerId());            
        }
        return thelist;        
    }

    /**
     * Method is called from reports page when user is selecting what customer they want to view appointments for. Uses the data from Observable List returned in this method to fill in values for Customer ComboBox so user can select a Customer Name to view appointments for.
     * @return a list of all Customer Names
     */
    public static ObservableList<String> getCustomerNames(){
        ObservableList<String> thelist = FXCollections.observableArrayList();
        for (Customer customer : allCustomers){
            thelist.add(customer.getCustomerName());            
        }
        return thelist;        
    }      
    
    /**
     * Iterates through all Customers, finds a Customer with matching Customer ID as the one passed in, and returns Customer Name for that Customer.
     * @param customer_id The Customer, program is searching Customer Name for.
     * @return if Object in allCustomers has same Customer ID as the one passed in, than it returns Customer Name for that Customer. Else it returns "N/A".
     */
    public static String getCustomerNameWithId(int customer_id){
        for(Customer customer : allCustomers){
            if(customer.getCustomerId() == customer_id){
                return customer.getCustomerName();
            }
        }
        return "N/A";
    }
}
