package dataStorage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class handles, stores, and manipulates data for all Country objects used through out the program.
 * @author Arun Rai
 */
public class allCountries {
    private static ObservableList<country> allCountries = FXCollections.observableArrayList();
    
    /**
     * Method is called when program is iterating through each row during a query to the SQL database. Each iteration calls this method and adds the country to the allCountries Observable List.  
     * @param newCountry Country to be added to "allCountries".
     */    
    public static void addCountry(country newCountry) {
        allCountries.add(newCountry);
    }
    
    /**
     * @return allCountries Observable list
     */
    public static ObservableList<country> getCountries() {
        return allCountries;
    }
    
    /**
     * When data is updated or program needs to refresh data, this method is called and removes all data from allCountries list, preparing it for a new query.
     */    
    public static void clearAllCountries() {
        allCountries.clear();
    }
    
    /**
     * Iterates through all countries, finds a country with matching country_id as the one passed in, and returns country name for that country.
     * @param country_id  The Country, program is finding Country Name for.
     * @return if Object in allCountries has same country_id as the one passed in, than it returns country name for that country. Else it returns "null".
     */
    public static String getCountryNameWithID (int country_id){
        for (country countryy : allCountries){
            if(countryy.getCountry_ID() == country_id){
                return countryy.getCountry();
            }
        }        
        return "null";    
    }
}
