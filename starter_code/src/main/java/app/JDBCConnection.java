package app;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database. Allows SQL
 * queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies
 * Database This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Halil Ali, 2021. email halil.ali@rmit.edu.au
 */
public class JDBCConnection {

   // the default oracle account uses the the read only MOVIES database
   // once you create a set of tables in your own account, update this to your RMIT
   // Oracle account details
   private static final String DATABASE_USERNAME = "s3720502";
   private static final String DATABASE_PASSWORD = "Monkeynuts15!";

   private static final String DATABASE_URL = "jdbc:oracle:thin:@//localhost:9922/CSAMPR1.its.rmit.edu.au";
   private static JDBCConnection jdbc = null;
   private static Connection connection;

   /**
    * Singleton function to return single copy of this class to other classes
    **/
   public static JDBCConnection getConnection() {

      // check that ssh session is still open (if not reopen)
      SSHTunnel.getSession();

      // check that JDBCconnection is available (if not establish)
      if (jdbc == null) {
         jdbc = new JDBCConnection();
      }
      return jdbc;
   }

   /**
    * Hidden constructor to establish Database connection (once)
    **/
   private JDBCConnection() {
      System.out.println("Created JDBC Connection Object");

      try {
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
      } catch (SQLException e) {
         // If there is an error, lets just print the error
         System.err.println(e.getMessage());
      }
   }

   /**
    * Closes the database connection - called only when server shutdown
    **/
   public static void closeConnection() {
      try {
         if (connection != null) {
            connection.close();
            System.out.println("Database Connection closed");
         }
      } catch (SQLException e) {
         // connection close failed.
         System.err.println(e.getMessage());
      }
   }

   // This Will Register a User, from the Register.java page
   // Takes All Params except visibility as we thought that should be changeable
   public String getRegister(String email, String fullname, String screenname, String dob, String gender, String status, String location) {

      try {
         PreparedStatement ps = connection.prepareStatement("INSERT INTO FBLMembers (EMAIL, FULLNAME, SCREENNAME, DOB, GENDER, STATUS, LOCATION) VALUES (?,?,?,TO_DATE(?, 'YYYY-MM-DD'),?,?,?)");
         ps.setQueryTimeout(30);

         ps.setString(1, email);
         ps.setString(2, fullname);
         ps.setString(3, screenname);
         ps.setString(4, dob);
         ps.setString(5, gender);
         ps.setString(6, status);
         ps.setString(7, location);

         int x = ps.executeUpdate();
            
         if(x > 0){
            System.out.println("Registration Successful!");
         } else{
            System.out.println("Registration Unsuccessful");
         }

      } catch (SQLException e) {
         System.err.println(e.getMessage());
      }
   return null;
   }

   //This is paired with the Register method, in the Register.java
   //This will take their email and the password and store it in the passwords table
   public String loginInsert(String enpassword, String email){
      
      try{
         PreparedStatement ps = connection.prepareStatement("INSERT INTO PASSWORDS (PASSWORD, EMAIL) VALUES (?,?)");
         ps.setQueryTimeout(30);

         ps.setString(1, enpassword);
         ps.setString(2, email);

         int x = ps.executeUpdate();

         if(x > 0){
            System.out.println("Registration Successful!");
         } else{
            System.out.println("Registration Unsuccessful");
         }
      }catch (SQLException e) {
         System.err.println(e.getMessage());
      }
      return null;
   }

   //This acts like a Member Search Function
   //Gets the email that the user looks up
   //Retrieves all data of that email from the Members table
   public ArrayList<String> getMemDetails(String email){
      ArrayList<String> memberDetails = new ArrayList<String>();

      try{
         PreparedStatement ps = connection.prepareStatement("SELECT * FROM FBLMembers WHERE email = ?");
         ps.setQueryTimeout(30);

         ps.setString(1, email);

         ResultSet results = ps.executeQuery();

         while (results.next()){
            String memEmail = results.getString("email");
            String memFullName = results.getString("fullname");
            String memScreenName = results.getString("screenname");
            String memDOB = results.getString("dob");
            String memGender = results.getString("gender");
            String memStatus = results.getString("status");
            String memLocation = results.getString("location");
            String memVis = results.getString("visiibility");

            memberDetails.add(memEmail);
            memberDetails.add(memFullName);
            memberDetails.add(memScreenName);
            memberDetails.add(memDOB);
            memberDetails.add(memGender);
            memberDetails.add(memStatus);
            memberDetails.add(memLocation);
            memberDetails.add(memVis);
         }

         ps.close();
         
      }catch (SQLException e) {
         System.err.println(e.getMessage());
      }
      return memberDetails;
   }

   //This Methood will update a users details, screenname, status and visibility
   //Based on their email
   public String updateDetails(String screenname, String status, String visi, String email){
      
      try{
         PreparedStatement ps = connection.prepareStatement("UPDATE FBLMembers SET screenname = ?, status =  ?, visiibility = ? WHERE email = ?");
         ps.setQueryTimeout(30);

         ps.setString(1, screenname);
         ps.setString(2, status);
         ps.setString(3, visi);
         ps.setString(4, email);

         int x =  ps.executeUpdate();

         if(x>0){
            System.out.println("Details Updated Successfully!");
         }else{
            System.out.println("Details Not Updated...");
         }

      }catch (SQLException e) {
         System.err.println(e.getMessage());
      }
      return null;
   }


   //This just prints all users names that are in the system
   public ArrayList<String> getMembers() {
      ArrayList<String> members = new ArrayList<String>();

      try {
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);

         String query = "SELECT fullname" + "\n" + "FROM FBLMembers";

         ResultSet results = statement.executeQuery(query);

         
         while (results.next()) {
            
            String memberName = results.getString("fullname");
            
            members.add(memberName);
         }

         statement.close();
      } catch (SQLException e) {
         System.err.println(e.getMessage());
      }

      return members;
   }

   //This is the Login Method, it works partially
   //Validation doesnt work completely but it does check the table properly
   public ArrayList<String> getLogin(String password, String email) {
      ArrayList<String> login = new ArrayList<String>();

      // Connect to database 
      try {
         PreparedStatement ps = connection.prepareStatement("SELECT * FROM PASSWORDS WHERE password = ? AND email = ?");
         ps.setString(1, password);
         ps.setString(2, email);
         ResultSet results = ps.executeQuery();

         //While statement that allows or denies the user to go to Homepage
         if (results.next())
         {
            if(password.equals(results.getString(1)) && email.equals(results.getString(2)))
               System.out.println("Logged in");
               //code to allow access to Homepage
            }
         else
            {
               System.out.println("Bad username or password!");
               //code to redirect to Registration page
            }
         
         ps.close();
      
      } catch (SQLException e) {
         // If there is an error, lets just print the error
         System.err.println(e.getMessage());
         System.out.println("LOGIN UNSUCCESSFUL TRY AGAIN");
      }

      // Finally we return all of the movies
      return login;
   }

   //This Method is for creating posts
   //Gets the content they entered, gets their email
   //Couldnt automate the postIDs
   //Timestamp is automatic
   public String insertPosts(String postID, String content, String posttime, String parpostID, String postemail){

      try{
         PreparedStatement ps = connection.prepareStatement("INSERT INTO FBLPosts (POSTID, CONTENT, POSTTIMESTAMP, PARENTPOSTID, POSTEREMAIL) VALUES (?,?,TO_TIMESTAMP(?, 'yyyy-MM-dd HH24:mi:ss'),?,?)");
         ps.setQueryTimeout(30);

         ps.setString(1, postID);
         ps.setString(2, content);
         ps.setString(3, posttime);
         ps.setString(4, parpostID);
         ps.setString(5, postemail);

         int x = ps.executeUpdate();
            
         if(x > 0){
            System.out.println("Post Insertion Successful!");
         } else{
            System.out.println("Post Insertion Unsuccessful...");
         }

      }catch (SQLException e) {
         System.err.println(e.getMessage());
      }

      return null;
   }

   //This will display all the posts created
   public ArrayList<String> displayPosts(){
      ArrayList<String> disPosts = new ArrayList<String>();

      try{
         PreparedStatement ps = connection.prepareStatement("SELECT CONTENT, POSTEREMAIL, POSTTIMESTAMP FROM FBLPosts");
         ps.setQueryTimeout(30);

         ResultSet results = ps.executeQuery();

         while (results.next()){
            String postContent = results.getString("content");
            String postEmail = results.getString("posteremail");
            String postTime = results.getString("posttimestamp");

            disPosts.add(postContent);
            disPosts.add(postEmail);
            disPosts.add(postTime);
         }

         ps.close();
         
      }catch (SQLException e) {
         System.err.println(e.getMessage());
      }
      return disPosts;
   }

   //This allows a user to send a friend request to a user
   //Status is set a 'Sent'
   public ArrayList<String> friendRequest(String reqemail, String recemail, String status, String date){
      ArrayList<String> friendReq = new ArrayList<String>();

      try{
         PreparedStatement ps = connection.prepareStatement("INSERT INTO Friends (requesteremail, receiveremail, status, dateadded) VALUES (?,?,?,TO_DATE(?, 'YYYY-MM-DD'))");
         ps.setQueryTimeout(30);

         ps.setString(1,reqemail);
         ps.setString(2, recemail);
         ps.setString(3, status);
         ps.setString(4, date);

         int x = ps.executeUpdate();
            
         if(x > 0){
            System.out.println("Friend Request Sent Successfully!");
         } else{
            System.out.println("Friend Request Unsuccessful...");
         }
      }catch (SQLException e) {
         System.err.println(e.getMessage());
      }

      return friendReq;
   }

   //This is where the user accepts or rejects the request and updates the status column of our friends table
   public ArrayList<String> friendUpdate(String status, String email){
      ArrayList<String> friendUp = new ArrayList<String>();
      
      try{
         PreparedStatement ps = connection.prepareStatement("UPDATE Friends SET status = ? WHERE receiveremail = ?");
         ps.setQueryTimeout(30);

         ps.setString(1, status);
         ps.setString(2, email);

         int x = ps.executeUpdate();
            
         if(x > 0){
            System.out.println("Friendship Updated Successfully!");
         } else{
            System.out.println("Friendship Update Unsuccessful...");
         }
      }catch (SQLException e) {
         System.err.println(e.getMessage());
      }

      return friendUp;
   }
}