package controllers;

import static Main.JDBC.connection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import Main.Main;
import dataStorage.Appointment;
import dataStorage.Customer;
import dataStorage.allAppointments;
import dataStorage.allContacts;
import dataStorage.allCountries;
import dataStorage.allCustomers;
import dataStorage.allFirst_level_divisions;
import dataStorage.allUsers;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.SQL_queries;
import utility.errorBoxes;


/**
 * Controller class for the home_controller.fxml file. Handles all user input and data manipulation for the Home view.
 * @author Arun Rai
 */
public class home_controller implements Initializable {
    
    @FXML
    private Button Exit_home_page;

    @FXML
    private Button add_home_page;

    @FXML
    private TableColumn<Appointment, Integer> allA_c_id;

    @FXML
    private TableColumn<Appointment, Integer> allA_contact;

    @FXML
    private TableColumn<Appointment, String> allA_description;

    @FXML
    private TableColumn<Appointment, Timestamp> allA_endTime;

    @FXML
    private TableColumn<Appointment, Timestamp> allA_enddate;

    @FXML
    private TableColumn<Appointment, Integer> allA_id;

    @FXML
    private TableColumn<Appointment, String> allA_location;

    @FXML
    private TableColumn<Appointment, Timestamp> allA_startdate;

    @FXML
    private TableColumn<Appointment, Timestamp> allA_starttime;

    @FXML
    private TableColumn<Appointment, String> allA_title;

    @FXML
    private TableColumn<Appointment, String> allA_type;

    @FXML
    private TableColumn<Appointment, Integer> allA_u_id;

    @FXML
    private Tab allAppointmentsTab;

    @FXML
    private TableView<Appointment> allAppointmentsTable;

    @FXML
    private TableColumn<Customer, String> allC_address;

    @FXML
    private TableColumn<Customer, String> allC_country;

    @FXML
    private TableColumn<Customer, Integer> allC_id;

    @FXML
    private TableColumn<Customer, String> allC_name;

    @FXML
    private TableColumn<Customer, String> allC_phonenumber;

    @FXML
    private TableColumn<Customer, Integer> allC_postalcode;

    @FXML
    private TableColumn<Customer, String> allC_state_p;

    @FXML
    private Tab allCustomersTab;

    @FXML
    private TableView<Customer> allCustomersTable;

    @FXML
    private TableView<Appointment> appointmentsByweekTable;

    @FXML
    private Tab appointmentsMonthTab;

    @FXML
    private TableView<Appointment> appointmentsMonthTable;

    @FXML
    private Tab appointmentsWeekTab;

    @FXML
    private Button delete_home_page;

    @FXML
    private Button modify_home_page;

    @FXML
    private TableColumn<Appointment, Integer> monthA_c_id;

    @FXML
    private TableColumn<Appointment, String> monthA_contact;

    @FXML
    private TableColumn<Appointment, String> monthA_description;

    @FXML
    private TableColumn<Appointment, String> monthA_endTime;

    @FXML
    private TableColumn<Appointment, String> monthA_enddate;

    @FXML
    private TableColumn<Appointment, Integer> monthA_id;

    @FXML
    private TableColumn<Appointment, String> monthA_location;

    @FXML
    private TableColumn<Appointment, String> monthA_startdate;

    @FXML
    private TableColumn<Appointment, String> monthA_starttime;

    @FXML
    private TableColumn<Appointment, String> monthA_title;

    @FXML
    private TableColumn<Appointment, String> monthA_type;

    @FXML
    private TableColumn<Appointment, Integer> monthA_u_id;

    @FXML
    private Tab reportsTab;

    @FXML
    private TableView<Appointment> reportsTable;

    @FXML
    private TableColumn<Appointment, Integer> weekA_c_id;

    @FXML
    private TableColumn<Appointment, String> weekA_contact;

    @FXML
    private TableColumn<Appointment, String> weekA_description;

    @FXML
    private TableColumn<Appointment, String> weekA_enddate;

    @FXML
    private TableColumn<Appointment, String> weekA_endtime;

    @FXML
    private TableColumn<Appointment, Integer> weekA_id;

    @FXML
    private TableColumn<Appointment, String> weekA_location;

    @FXML
    private TableColumn<Appointment, String> weekA_startdate;

    @FXML
    private TableColumn<Appointment, String> weekA_starttime;

    @FXML
    private TableColumn<Appointment, String> weekA_title;

    @FXML
    private TableColumn<Appointment, String> weekA_type;

    @FXML
    private TableColumn<Appointment, Integer> weekA_u_id;
    
    @FXML
    private Label errorLabelHome;  
    
    @FXML
    private Label select_report_label;
    
    @FXML
    private Label select_box_2_label_reports;
    
    @FXML
    private Label select_box_3_label_reports;
    
    @FXML
    private Label total_appointments_label;
    
    @FXML
    private Label number_of_appointments_label;
    
    
    @FXML
    private ComboBox<String> combo_box_1_reports;

    @FXML
    private ComboBox<String> combo_box_2_reports;
    
    @FXML
    private ComboBox<String> combo_box_3_reports;
    
    @FXML
    private TableColumn<Appointment, Integer> reports_table_appointment_id;

    @FXML
    private TableColumn<Appointment, String> reports_table_title;

    @FXML
    private TableColumn<Appointment, String> reports_description_table;

    @FXML
    private TableColumn<Appointment, String> reports_type_table;

    @FXML
    private TableColumn<Appointment, Timestamp> reports_start_date_table;

    @FXML
    private TableColumn<Appointment, Timestamp> reports_start_time_table;

    @FXML
    private TableColumn<Appointment, Timestamp> reports_end_date_table;
    
    @FXML
    private TableColumn<Appointment, Timestamp> reports_end_time_table;
    
    @FXML
    private TableColumn<Appointment, Integer> reports_customer_id_table;
    
    @FXML
    private ComboBox<String> appointment_month_box;

    @FXML
    private ComboBox<String> appointment_week_box;
    
    private static ObservableList<Appointment> reportslist = FXCollections.observableArrayList();
    
    private static ObservableList<Appointment> appointmentsByWeekList = FXCollections.observableArrayList();
    
    private static ObservableList<Appointment> appointmentsByMonthList = FXCollections.observableArrayList();    
    
    private boolean isErrorLabelsSet = false;
    
    
    private static EventHandler<ActionEvent> lastActionWeek;
    
    private static EventHandler<ActionEvent> lastActionMonth;
    
    private static EventHandler<ActionEvent> lastActionReports;
    
    

    /**
     * The initialize method of the home_controller is the code that is executed after a user successfully logs in. it will clear all ObservableLists to remove any outdated data. Then it will query the database for all data required to run
     * the program such as Appointment and Customer data. It will also populate tables with the data it just retrieved.
     * <p>
     * LAMBDA EXPRESSIONS(Applies to all of lambda expressions in this method):
     * <p>
     * When dealing with a GUI that handles actions and clicks by a user, using lambda expressions can simplify the code by allowing developers to define the behavior of the GUI elements in a more concise and functional way. 
     * When an action occurs, using a lambda expression to handle it can be more efficient than using traditional approaches like anonymous inner classes, because the overhead associated with creating an anonymous class instance is 
     * avoided, and the code can be more easily optimized by the compiler. Additionally, lambda expressions can enable developers to write code that is more focused on the behavior that needs to be performed when an action occurs, 
     * rather than on the boilerplate code needed to define an anonymous inner class. This can make the code more readable, easier to maintain, and less error-prone, particularly when dealing with complex GUIs that require many event handlers.     * <p>
     * 
     * @param url a URL representing the location of the FXML file.
     * @param rb a ResourceBundle representing the localized resources for the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!isErrorLabelsSet) {
            errorBoxes.setHomeLabels(errorLabelHome);
            isErrorLabelsSet = true;
        }   
        allCountries.clearAllCountries();
        allFirst_level_divisions.clearAllDivisions();
        SQL_queries.getCountries();
        SQL_queries.getDivisions();        
        allCustomers.clearAllCustomers();
        allAppointments.clearAllAppointments();
        allAppointmentsTable.getItems().clear();
        allCustomersTable.getItems().clear();
        appointmentsMonthTable.getItems().clear();
        appointmentsByweekTable.getItems().clear();
        try {
            SQL_queries.getAppointments();
        } catch (ParseException ex) {
            Logger.getLogger(home_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        SQL_queries.getCustomers();
        allContacts.clearContacts();
        SQL_queries.getContacts();
        
        ObservableList<Customer> customers = allCustomers.getCustomers();
        ObservableList<Appointment> appointments = allAppointments.getAppointments();

        new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
                 Platform.runLater(() -> checkWithin15minutesPopUp());
             }
        }, 
        1000 
        );
                        
        setAllAppointmentsTable(appointments);
        setAllCustomersTable(customers);
                
        
        addTabListener(allCustomersTab, this::customerButtons);
        addTabListener(appointmentsMonthTab, this::appointmentButtons);
        addTabListener(appointmentsWeekTab, this::appointmentButtons);
        addTabListener(allAppointmentsTab, this::appointmentButtons);
        addTabListener(reportsTab, this::appointmentButtons);

        
        appointment_week_box.getItems().addAll(" ", "Current Week", "Upcoming Week", "Previous Week");
        appointment_week_box.setOnAction (event ->{
            appointmentsByweekTable.getItems().clear();
            String whichweek = appointment_week_box.getValue();
            if(whichweek.equals("Current Week")){
                appointmentsByWeekList = allAppointments.getDurationAppointments("week","Current");
                appointmentsByweekTable.setItems(appointmentsByWeekList);
                setAppointmentByWeekTable();
            } else if(whichweek.equals("Upcoming Week")){
                appointmentsByWeekList = allAppointments.getDurationAppointments("week", "Upcoming");
                appointmentsByweekTable.setItems(appointmentsByWeekList);
                setAppointmentByWeekTable();
            } else if(whichweek.equals("Previous Week")){
                appointmentsByWeekList = allAppointments.getDurationAppointments("week", "Previous");
                appointmentsByweekTable.setItems(appointmentsByWeekList);
                setAppointmentByWeekTable();
            } else if (whichweek.equals(" ")){
                appointmentsByweekTable.getItems().clear();
            }
            lastActionWeek = appointment_week_box.getOnAction();
        });

        appointment_month_box.getItems().addAll(" ", "Current Month", "Upcoming Month", "Previous Month");
        appointment_month_box.setOnAction (event ->{
            appointmentsMonthTable.getItems().clear();
            String whichmonth = appointment_month_box.getValue();
            if(whichmonth.equals("Current Month")){
                appointmentsByMonthList = allAppointments.getDurationAppointments("month","Current");
                setAppointmentByMonthTable();
            } else if(whichmonth.equals("Upcoming Month")){
                appointmentsByMonthList = allAppointments.getDurationAppointments("month", "Upcoming");
                setAppointmentByMonthTable();
            } else if(whichmonth.equals("Previous Month")){
                appointmentsByMonthList = allAppointments.getDurationAppointments("month", "Previous");
                setAppointmentByMonthTable();
            } else if (whichmonth.equals(" ")){
                appointmentsMonthTable.getItems().clear();
            }
            lastActionMonth = appointment_month_box.getOnAction();
        });

        combo_box_1_reports.getItems().addAll(" ", "Customer Appointments by Type and Month", "Contact Schedule", "Customer Schedule");
        
        combo_box_1_reports.setOnAction(event -> {
            reportsTable.getItems().clear();
            number_of_appointments_label.setVisible(false);
            total_appointments_label.setVisible(false);
            String report = combo_box_1_reports.getValue();
            if(report.equals(" ")){
                combo_box_3_reports.setVisible(false);
                combo_box_2_reports.setVisible(false);
                select_box_2_label_reports.setVisible(false);
                select_box_3_label_reports.setVisible(false);
            } else if(report.equals("Customer Appointments by Type and Month")) {
                reports_customer_id_table.setText("Customer ID");
                combo_box_3_reports.setVisible(true);
                combo_box_2_reports.setVisible(true);
                select_box_2_label_reports.setText("Select Type:");
                select_box_2_label_reports.setVisible(true);
                select_box_3_label_reports.setText("Select Month:");
                select_box_3_label_reports.setVisible(true);
                combo_box_2_reports.getItems().clear();
                combo_box_3_reports.getItems().clear();                
                ObservableList<String> TypeList = FXCollections.observableArrayList();
                TypeList.add(" ");
                TypeList.addAll(allAppointments.getTypes());
                combo_box_2_reports.getItems().addAll(TypeList);
                combo_box_3_reports.getItems().addAll(" ", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
                                
            } else if(report.equals("Contact Schedule")){
                reports_customer_id_table.setText("Customer ID");
                combo_box_3_reports.setVisible(false);
                combo_box_2_reports.setVisible(true);
                select_box_2_label_reports.setText("Select Contact:");
                select_box_2_label_reports.setVisible(true);
                select_box_3_label_reports.setVisible(false);
                combo_box_2_reports.getItems().clear();
                ObservableList<String> contactlist = FXCollections.observableArrayList();
                contactlist.add(" ");
                contactlist.addAll(allContacts.getContactNames());
                combo_box_2_reports.getItems().addAll(contactlist);
            } else if(report.equals("Customer Schedule")){
                reports_customer_id_table.setText("Contact");
                combo_box_3_reports.setVisible(false);
                combo_box_2_reports.setVisible(true);
                select_box_2_label_reports.setText("Select Customer:");
                select_box_2_label_reports.setVisible(true);
                select_box_3_label_reports.setVisible(false);
                combo_box_2_reports.getItems().clear();
                ObservableList<String> customerlist = FXCollections.observableArrayList();
                customerlist.add(" ");
                customerlist.addAll(allCustomers.getCustomerNames());
                combo_box_2_reports.getItems().addAll(customerlist);

            }
            lastActionReports = combo_box_1_reports.getOnAction();
        });           

        combo_box_2_reports.setOnAction(event -> {
            number_of_appointments_label.setVisible(false);
            total_appointments_label.setVisible(false);
            reportsTable.getItems().clear();
            String report = combo_box_1_reports.getValue();
            if(report.equals("Customer Appointments by Type and Month")) {
                String type = combo_box_2_reports.getValue();
                String month = combo_box_3_reports.getValue();                
                reportslist = allAppointments.getTypeMonthAppointments(type, month);

                setReportsTable("customerId");

                if (reportslist.size() > 0){
                    number_of_appointments_label.setText(Integer.toString(reportslist.size()));
                    number_of_appointments_label.setVisible(true);
                    total_appointments_label.setVisible(true);
                }
                
            } else if(report.equals("Contact Schedule")){
                String contact = combo_box_2_reports.getValue();
                
                reportslist = allAppointments.getContactAppointments(contact);
                setReportsTable("customerId");

                
                
            } else if(report.equals("Customer Schedule")){
                String customer = combo_box_2_reports.getValue();
                
                reportslist = allAppointments.getCustomerAppointments(customer);
                setReportsTable("contactName");

                if (reportslist.size() > 0){
                    number_of_appointments_label.setText(Integer.toString(reportslist.size()));
                    number_of_appointments_label.setVisible(true);
                    total_appointments_label.setVisible(true);
                }
            }
            lastActionReports = combo_box_2_reports.getOnAction();
        });
        
        combo_box_3_reports.setOnAction(event -> {
            reportsTable.getItems().clear();
            number_of_appointments_label.setVisible(false);
            total_appointments_label.setVisible(false);

            String type = combo_box_2_reports.getValue();
            String month = combo_box_3_reports.getValue();
            reportslist = allAppointments.getTypeMonthAppointments(type, month);
            setReportsTable("customerId");

            if (reportslist.size() > 0){
                number_of_appointments_label.setText(Integer.toString(reportslist.size()));
                number_of_appointments_label.setVisible(true);
                total_appointments_label.setVisible(true);
            }
            lastActionReports = combo_box_3_reports.getOnAction();
        });
    }
    
    /**
     * This method is called every time data is updated on the database, for example when new customers or appointments are added. The method will clear all old data and redo all the queries to update all the tables so
     * the user is always viewing the most up to date data.
     */
    public static void refresh(){
        allCountries.clearAllCountries();
        allFirst_level_divisions.clearAllDivisions();
        allCustomers.clearAllCustomers();
        allAppointments.clearAllAppointments();
        allContacts.clearContacts();
        allUsers.clearUsers();
        
        SQL_queries.getUsers();
        SQL_queries.getCountries();
        SQL_queries.getDivisions();        
        SQL_queries.getContacts();
        SQL_queries.getCustomers();
        try {
            SQL_queries.getAppointments();
        } catch (ParseException ex) {
            Logger.getLogger(home_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (lastActionWeek != null) {
            lastActionWeek.handle(new ActionEvent());
        }
        if (lastActionMonth != null) {
            lastActionMonth.handle(new ActionEvent());
        }
        if (lastActionReports != null) {
            lastActionReports.handle(new ActionEvent());
        }        
    }
    
    
    /**
     * Handles the logic for user switching tabs.
     * <p>
     * LAMBDA EXPRESSION:
     * <p>
     * This method uses a lambda expression to add a change listener to the selected property of the given tab. When the selected property changes, the lambda expression check whether the new value is true and, if so, executes the 
     * given action. Using a lambda expression in this case allowed for a concise and functional approach to specify the behavior to execute when the selected property of a Tab object changes.
     * @param tab The tab selected.
     * @param action switching page to that tab.
     */
    private void addTabListener(Tab tab, Runnable action) {
        tab.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                action.run();
                errorLabelHome.setVisible(false);
            }
        });
    }    
    
    /**
     * Method is called when tab is switched to a page where user is viewing Customers such as "All Customers". Changes the buttons to include Customer instead of Appointment to keep consistency.
     */
    @FXML
    void customerButtons(){
        add_home_page.setText("Add Customer");
        modify_home_page.setText("Modify Customer");
        delete_home_page.setText("Delete Customer");        
    }
    
    /**
     * Method is called when tab is switched to a page where user is viewing Appointments such as "All Appointments" or "Appointments by Week". Changes the buttons to include Appointment instead of Customer to keep consistency.
     */
    @FXML
    void appointmentButtons(){
        add_home_page.setText("Add Appointment");
        modify_home_page.setText("Modify Appointment");
        delete_home_page.setText("Delete Appointment");        
    }
    
    /**
     * Method is called from initialize method of "home_controller". Checks if any Appointments are within 15 minutes of logging in. If true, than a pop up is loaded with the Appointment Information, else a pop up is
     * loaded notifying user their are no Appointments within 15 minutes.
     */
    private void checkWithin15minutesPopUp(){
        Appointment appointment = allAppointments.checkWithin15Minutes();
        if(appointment != null){            
            errorLabelHome.setVisible(false);
            Main.handleSceneChange("notification_error.fxml", "Upcoming Appointment!", "titleLabel", add_home_page,"yes",null,appointment,null, "dont hide");
        }else{
            errorLabelHome.setVisible(false);
            Main.handleSceneChange("notification_error.fxml", "No Upcoming Appointments!", "titleLabel", add_home_page,"yes",null,null,null, "dont hide");
        }
    }
    

    /**
     * Method is ran when user clicks "Add Appointment" or "Add Customer" from Home page. If button is "Add Appointment" than it will load empty Appointment page, else it will load empty Customer page.
     * @param event Clicking "Add Customer" button or "Add Appointment" button.
     */
    @FXML
    void addButtonHomeAction(ActionEvent event) {
        if(add_home_page.getText().equals("Add Appointment")){
            errorLabelHome.setVisible(false);
            Main.handleSceneChange("appointment.fxml", "Add Appointment", "appointment_title", add_home_page,"yes",null,null,null, "hide");
        } else {
            errorLabelHome.setVisible(false);
            Main.handleSceneChange("customer.fxml", "Add Customer", "customer_title", add_home_page,"yes",null,null,null, "hide");
        }
    }
    
    /**
     * When a user clicks the delete button, the program needs to know which table the user wants to delete from. That is the purpose of this method.
     * @param event  Clicking "Delete Customer" button or "Delete Appointment" button.
     */
    @FXML
    void deleteButtonHomeAction(ActionEvent event) {
        if (allCustomersTab.isSelected()) {
            deleteCustomerFromTable(allCustomersTable);
        } else if (allAppointmentsTab.isSelected()) {
            deleteAppointmentFromTable(allAppointmentsTable);        
        } else if (appointmentsMonthTab.isSelected()) {
            deleteAppointmentFromTable(appointmentsMonthTable);        
        } else if (appointmentsWeekTab.isSelected()) {
            deleteAppointmentFromTable(appointmentsByweekTable);        
        } else if (reportsTab.isSelected()) {
            deleteAppointmentFromTable(reportsTable);       
        }
    }
    
    /**
     * This method accepts a table as a parameter, which is the table the user wants to delete from. Then checks if an item from that table was selected, if so it will first check if that Customer has Appointments. If Customer has Appointments
     * than a pop up will load notifying user that appointments must be deleted first. If Customer has no Appointments it will load a pop up confirming if the user wants to delete Customer. If no item was selected from table 
     * it will print an error to the home screen.
     * @param table Table that was visible when user clicked "Delete Customer".
     */
    private void deleteCustomerFromTable(TableView<Customer> table) {
        Customer selectedCustomer = table.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            int customerID = selectedCustomer.getCustomerId();
            List<Integer> appointmentlist = allAppointments.checkIfCustomerHasAppointments(customerID);
            if(appointmentlist.size() > 0) {
                errorLabelHome.setVisible(false);
                Main.handleSceneChange("notification_error.fxml", "null", "null", delete_home_page,"yes", null, null,appointmentlist,"dont hide");
            } else {
                errorLabelHome.setVisible(false);
                Main.handleSceneChange("delete_error.fxml", "Delete Customer", "titleLabel", delete_home_page,"yes", selectedCustomer, null,null,"dont hide");
            }                         
        } else {
            errorLabelHome.setText("Please select a Customer to delete.");
            errorLabelHome.setVisible(true);
            errorBoxes.flashMainError();
        }
    }
    
    /**
     * This method accepts a table as a parameter, which is the table the user wants to delete from. Then checks if an item from that table was selected, if so it will load a pop up confirming if the user wants to delete the
     * Appointment. Else if no item was selected it will print an error to the home screen.
     * @param table Table that was visible when user clicked "Delete Appointment".
     */
    private void deleteAppointmentFromTable(TableView<Appointment> table) {
        Appointment selectedAppointment = table.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            errorLabelHome.setVisible(false);
            Main.handleSceneChange("delete_error.fxml", "Delete Appointment", "titleLabel", delete_home_page,"yes", null, selectedAppointment,null,"dont hide");
        } else {
            errorLabelHome.setText("Please select an appointment to delete.");
            errorLabelHome.setVisible(true);
            errorBoxes.flashMainError();
        }        
    }
    
    /**
     * Returns the user to the Login Screen.
     * @param event Clicked "Log out" button.
     */
    @FXML
    void exitHomeButtonAction(ActionEvent event) {
        Main.handleSceneChange("login.fxml", "null", "null", delete_home_page,"no", null, null,null,"dont hide");
    }
    
    /**
     * When a user clicks the modify button, the program needs to know which table the user wants to modify items from. That is the purpose of this method.
     * @param event Clicked "Modify Appointment" button or "Modify Customer" button.
     */
    @FXML
    void modifyButtonHomeAction(ActionEvent event) {
        if (allCustomersTab.isSelected()) {
            modifyCustomerFromTable(allCustomersTable);
        } else if (allAppointmentsTab.isSelected()) {
            modifyAppointmentFromTable(allAppointmentsTable);
        } else if (appointmentsMonthTab.isSelected()) {
            modifyAppointmentFromTable(appointmentsMonthTable);
        } else if (appointmentsWeekTab.isSelected()) {
            modifyAppointmentFromTable(appointmentsByweekTable);
        } else if (reportsTab.isSelected()) {
            modifyAppointmentFromTable(reportsTable);
        }
    }
    
    /**
     * This method accepts a table as a parameter, which is the table the user wants to modify items from. Then checks if an item from that table was selected, if so it will load the Customer data selected 
     * from table into "Modify Customer" Page. Else if no item was selected it will print an error to the home screen.
     * @param table Table that was visible when user clicked "Modify Customer".
     */
    private void modifyCustomerFromTable(TableView<Customer> table){
        if (table.getSelectionModel().getSelectedItem() != null){
            Customer selectedCustomer = table.getSelectionModel().getSelectedItem();
            errorLabelHome.setVisible(false);
            Main.handleSceneChange("customer.fxml", "Modify Customer", "customer_title", add_home_page,"yes",selectedCustomer,null,null, "hide");
        } else {
            errorLabelHome.setText("Please select a Customer to modify.");
            errorLabelHome.setVisible(true);
            errorBoxes.flashMainError();
        }
    }
    
    /**
     * This method accepts a table as a parameter, which is the table the user wants to modify items from. Then checks if an item from that table was selected, if so it will load the Appointment data selected 
     * from table into "Modify Appointment" Page. Else if no item was selected it will print an error to the home screen.
     * @param table Table that was visible when user clicked "Modify Appointment".
     */
    private void modifyAppointmentFromTable(TableView<Appointment> table){
        if (table.getSelectionModel().getSelectedItem() != null){
            Appointment selectedAppointment = table.getSelectionModel().getSelectedItem();
            errorLabelHome.setVisible(false);
            Main.handleSceneChange("appointment.fxml", "Modify Appointment", "appointment_title", add_home_page,"yes",null,selectedAppointment,null, "hide");
        } else {
            errorLabelHome.setText("Please select an Appointment to modify.");
            errorLabelHome.setVisible(true);         
            errorBoxes.flashMainError();
        }                        
    }
    
    /**
     * Populates the All Appointments Table, with list of Appointments passed in from initialize method of "home_controller".
     * @param list List of All Appointments.
     */
    private void setAllAppointmentsTable(ObservableList<Appointment> list){
        allAppointmentsTable.setItems(list);
        allA_id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        allA_contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        allA_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        allA_endTime.setCellValueFactory(new PropertyValueFactory<>("tableEndTime"));
        allA_enddate.setCellValueFactory(new PropertyValueFactory<>("tableEndDate"));
        allA_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        allA_startdate.setCellValueFactory(new PropertyValueFactory<>("tableStartDate"));
        allA_starttime.setCellValueFactory(new PropertyValueFactory<>("tableStartTime"));
        allA_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        allA_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        allA_u_id.setCellValueFactory(new PropertyValueFactory<>("userId"));
        allA_c_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));        
    }
    
    /**
     * Populates the All Customer Table, with list of Customers passed in from initialize method of "home_controller".
     * @param list List of all Customers.
     */
    private void setAllCustomersTable(ObservableList<Customer> list){
        allCustomersTable.setItems(list);
        allC_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        allC_name.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        allC_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        allC_postalcode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        allC_phonenumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        allC_country.setCellValueFactory(new PropertyValueFactory<>("country"));
        allC_state_p.setCellValueFactory(new PropertyValueFactory<>("division"));                
    }
    
    /**
     * Populates the Appointments by Week Table.
     */        
    private void setAppointmentByWeekTable(){
        appointmentsByweekTable.setItems(appointmentsByWeekList);
        weekA_id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weekA_contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        weekA_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekA_endtime.setCellValueFactory(new PropertyValueFactory<>("tableEndTime"));
        weekA_enddate.setCellValueFactory(new PropertyValueFactory<>("tableEndDate"));
        weekA_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekA_startdate.setCellValueFactory(new PropertyValueFactory<>("tableStartDate"));
        weekA_starttime.setCellValueFactory(new PropertyValueFactory<>("tableStartTime"));
        weekA_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekA_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        weekA_u_id.setCellValueFactory(new PropertyValueFactory<>("userId"));
        weekA_c_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));                        
    }
    
    /**
     * Populates the Appointments by Month Table.
     */        
    private void setAppointmentByMonthTable(){
        appointmentsMonthTable.setItems(appointmentsByMonthList);
        monthA_id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthA_contact.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        monthA_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthA_endTime.setCellValueFactory(new PropertyValueFactory<>("tableEndTime"));
        monthA_enddate.setCellValueFactory(new PropertyValueFactory<>("tableEndDate"));
        monthA_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthA_startdate.setCellValueFactory(new PropertyValueFactory<>("tableStartDate"));
        monthA_starttime.setCellValueFactory(new PropertyValueFactory<>("tableStartTime"));
        monthA_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthA_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthA_u_id.setCellValueFactory(new PropertyValueFactory<>("userId"));
        monthA_c_id.setCellValueFactory(new PropertyValueFactory<>("customerId"));                
    }
    
    /**
     * Populates the Reports Table Page.
     * @param customerOrContact Used to identify what data should go in "reports_customer_id_table" column. "customerId" or "contactId" depending on the report selected.
     */
    private void setReportsTable( String customerOrContact){
        reportsTable.setItems(reportslist);
        reports_table_appointment_id.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        reports_description_table.setCellValueFactory(new PropertyValueFactory<>("description"));
        reports_end_time_table.setCellValueFactory(new PropertyValueFactory<>("tableEndTime"));
        reports_end_date_table.setCellValueFactory(new PropertyValueFactory<>("tableEndDate"));
        reports_start_date_table.setCellValueFactory(new PropertyValueFactory<>("tableStartDate"));
        reports_start_time_table.setCellValueFactory(new PropertyValueFactory<>("tableStartTime"));
        reports_table_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        reports_type_table.setCellValueFactory(new PropertyValueFactory<>("type"));
        reports_customer_id_table.setCellValueFactory(new PropertyValueFactory<>(customerOrContact));                    
    }
}    
