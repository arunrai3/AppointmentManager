package Main;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * The JDBC class provides methods for opening and closing a database connection.
 * @author Arun Rai
 */
public abstract class JDBC {
   
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = LOCAL"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "root"; // Username
    private static String password = "tigers123"; // Password
    public static Connection connection;  // Connection Interface
    
    
    /**
     * The openConnection method opens a connection to the database.
     */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * The closeConnection method closes the database connection.
     */    
    public static void closeConnection() {
        try {
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println(" ");
        }
    }
    
}