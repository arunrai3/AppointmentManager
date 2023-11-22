package dataStorage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * This class handles, stores, and manipulates data for all First Level Division objects used through out the program.
 * @author Arun Rai
 */
public class allFirst_level_divisions {
    private static ObservableList<first_level_division> allDivisions = FXCollections.observableArrayList();
    
    /**
     * Method is called when program is iterating through each row during a query to the SQL database. Each iteration calls this method and adds the division to the allDivisions Observable List.  
     * @param newDivision Division to be added to "allDivisions".
     */
    public static void addDivision(first_level_division newDivision) {
        allDivisions.add(newDivision);
    }
   
    /**
     * 
     * @return allDivisions Observable List.
     */
    public static ObservableList<first_level_division> getDivisions() {
        return allDivisions;
    }

    /**
     * When data is updated or program needs to refresh data, this method is called and removes all data from allDivisions list, preparing it for a new query.
     */
    public static void clearAllDivisions() {
        allDivisions.clear();
    }
    
    /**
     * This method is used when populating the ComboBoxes on the Add/Modify Customer Page. When a Country is selected, the division box has to contain only Divisions for that Country.
     * Iterates through all divisions and divisions that have the same Country ID as the one passed in get added to a list.
     * @param country_id The Country the program is finding Division Names for.
     * @return The list of division names with same Country ID as the one passed in.
     */
    public static ObservableList<String> getDivisionNames(int country_id){
        ObservableList<String> division_names = FXCollections.observableArrayList();
        for (first_level_division division : allDivisions){
            if (division.getCountry_ID() == country_id) {
                division_names.add(division.getDivision());
            }            
        }
        return division_names;                
    }
    
    /**
     * Iterates through all Divisions, finds a Division with matching Division Name as the one passed in, and Division ID for that Division.
     * @param divisionName The Division, program is finding Division ID for.
     * @return if Object in allDivisons has same Division Name as the one passed in, than it returns Division ID for that Division. Else it returns "N/A".
     */
    public static int getDivisionId (String divisionName) {
        for(first_level_division division : allDivisions){
            if(division.getDivision().equals(divisionName)){
                return division.getDivision_ID();
            }            
        }
        return -1;
    }

    /**
     * Finds the Country ID for a specific Division. Iterates through all divisions and first finds the division with matching ID. Than it retrieves the Country ID for that Division.
     * @param division_id The Division, program is finding Country ID for.
     * @return country ID for division with ID that matches the one passed in. If no division exists that it returns -1. 
     */
    public static int getCountryIdWithDivisionId (int division_id) {
        for(first_level_division division : allDivisions){
            if(division.getDivision_ID() == division_id){
                return division.getCountry_ID();
            }            
        }
        return -1;
    }

    /**
     * Iterates through all Divisions, finds a Division with matching Division ID as the one passed in, and returns Division Name for that Division.
     * @param division_id The Division, program is finding Division Name for.
     * @return if Object in allDivisions has same Division ID as the one passed in, than it returns Division Name for that Division. Else it returns "null".
     */
    public static String getDivisionNameWithDivisionID (int division_id){
        for(first_level_division division : allDivisions){
            if(division.getDivision_ID() == division_id){
                return division.getDivision();
            }            
        }
        return "null";        
    }
    
}
