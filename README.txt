(INDEX.HTML FILE FOR JAVADOC COMMENTS IS LOCATED IN "dist/javadoc/" folder)

Title: Appointment Scheduler

Purpose: To help individuals or organizations manage appointments with professional relationships such as customers, investors, or associates. This program helps
by allowing users to add, modify, and delete appointmnets/customers in an easy to use graphical user interface. It also helps by automatically updating a SQL database
when changes are made so when the next user logs in, the changes made by previous user will be present. Bonus features include adjusting time zones and languages
to the settings of the computer the program is being run on.

Author: Arun Singh Rai

Contact Information: arai12@wgu.edu

Application Version: Version 1

Date: 3/19/2023

IDE Version: Apache NetBeans IDE 16

JDK Version: Java SE 17.0.5

Java FX Version: JavaFX-SDK-19.0.2.1

Directions:
Step 1: Visit Netbeans official website, and download the installer IDE for your operating system. Open the installer and follow the steps.

Step 2: Download JDK 17, go to the Oracle website and search for "Java SE Development Kit 17.0.5". Download the installer for your operating system from the Oracle website.

Step 3: Install JDK 17, double-click the downloaded file and follow the installation to install JDK 17. Choose the "Custom" installation option and during the installation,
choose the "C:\Program Files\Java" directory as the installation directory.

Step 4: Download JavaFX 19.0.0.2, go to the Gluon website and search for "JavaFX SDK 19.0.0.2". Download the JavaFX SDK for your operating system from the Gluon website.

Step 5: Extract JavaFX, double-click the downloaded file to extract the JavaFX SDK. In the extraction wizard, choose the "C:\Program Files" directory as the extraction directory.
 
Step 6: Install the MySQL server in the "Downloads" folder on your "C:" drive on your machine. You can download it from the MySQL website and follow the installation instructions for your operating system. When configuring the
setup for MySQL after you download it, make sure you include MySQL workbench in the installation as well.

Step 7: Open MySQL Workbench and create a connection. When you create a connection, it will also ask you to create a user as well, for username use "sqlUser", and for password use
"Passw0rd!". For Hostname use "127.0.0.1" or "localhost" and for port use "3306". Fill in any connection name than click "OK".

Step 8: Open your connection and on the top tab of options click "Server" -> "Users and Privileges", then on the left hand side select "sqlUser". After this click on "Administrative roles"
on the top tab and check "DBA" and click "Apply".

Step 9: Close that tab from Step 3 and open a new query tab, by cliking "File" -> "New Query Tab". Now go to the WGU Website and enter the "Software 2 Code Repository" and access the file
"create_C195_database_with_data_9-17-2021.sql". Copy and paste this code into the query tab you opened and click the yellow lightning bolt button on the top bar. Then confirm your actions.

Step 10: Next step is to click on the dropdown button next to "client_schedule" under "Schemas" on the left panel. Then click the dropdown button next to "Tables" and click the table icon
next to "users" table after highlighting it with your mouse. Remember the "User_Name" and "Password" for at least one of the rows as this will be used in the next step.

Step 11: Open the Java program in your IDE (Netbeans), click File -> Open Project -> locate and click open where you downloaded this project -> and click run(green arrow), when the 
Login page loads enter the information from previous step. If Main menu loads, than your connection is setup and your program is running great. If not, you can contact the email 
next to "Contact Information" for further assistance. 

Step 12: The following steps are how use the program once you have the enviornment setup correctly. 

Step 13: In the home page you will notice 5 tabs on the top of the screen that you can click through, these will be where you can view information in the database.

Step 14: At the bottom you will notice three buttons that contains "Add", "Modify", and "Delete". These buttons will work when any tab is selected, but the "Modify" and "Delete" buttons
will only work when youy have selected an item from a table. Their will be more details on these buttons in the next step.

Step 15: Their are two additional pages beside the home page and login page that we have gone over in the previous steps. The "Appointment Page" and "Customer Page", when you click "Add Customer" or
"Modify Customer" the Customer Page will open. And if you click "Add Appointment" or "Modify Appointment" the Appointment Page will open. On these pages you can insert or update data into the
database, the program will give you errors if you enter invalid data, such as outside of business hours.

Step 16: Bonus features of this program include that when you log in, the program will give you a notification if you have any appointments within 15 minutes (Local Time). The
login page for this program supports French, And all of the Times displayed in the program will adjust to the local time set on your PC. 

Step 17: At the home page on the bottom right you can click Log out which will return you to the Log in page.



Description of additional report: The additional report I added was checking all appointments for a specfic customer by name, instead of trying to manually search for appointments with the corresponding
Customer ID in the "All Appointments" list. This is a helpful report because it will be frequently used since indivdual customers will often call in to check when their
next upcoming appointment is and this will make that easier. In addition the "All Appointments" page does not show customer name, only the Customer ID which can make it hard to retrieve information
in a timely manner since you will have to go to the "All Customers" page first to retrieve the customer name that matches with the Customer ID first.

MY SQL Connector Driver Version: mysql-connector-java-8.0.32
