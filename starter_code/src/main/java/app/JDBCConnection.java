package app;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.sql.PreparedStatement;
//import java.time.LocalDateTime;

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

   /**** REGISTRATION/CREATE NEW USER ****/
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

   /**** USER PASSWORD INSERT ****/
   public String loginInsert(String password, String email){
      
      try{
         PreparedStatement ps = connection.prepareStatement("INSERT INTO PASSWORDS (PASSWORD, EMAIL) VALUES (?,?)");
         ps.setQueryTimeout(30);

         ps.setString(1, password);
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


   //DISPLAYS ALL MEMBERS (This one was a test function)
   public ArrayList<String> getMembers() {
      ArrayList<String> members = new ArrayList<String>();

      try {
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);

         String query = "SELECT fullname" + "\n" + "FROM FBLMembers";

         ResultSet results = statement.executeQuery(query);

         // Process all of the results
         // The "results" variable is similar to an array
         // We can iterate through all of the database query results
         while (results.next()) {
            // We can lookup a column of the a single record in the
            // result using the column name
            // BUT, we must be careful of the column type!
            // int id = results.getInt("mvnumb");
            String memberName = results.getString("fullname");
            // int year = results.getInt("yrmde");
            // String type = results.getString("mvtype");

            // For now we will just store the movieName and ignore the id
            members.add(memberName);
         }

         // Close the statement because we are done with it
         statement.close();
      } catch (SQLException e) {
         // If there is an error, lets just print the error
         System.err.println(e.getMessage());
      }

      // Finally we return all of the movies
      return members;
   }

   /**** LOGIN ATTEMPT 1****/
   public ArrayList<String> getLogin(String email, String password) {
      ArrayList<String> login = new ArrayList<String>();

      try {
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);

         String query = "SELECT *" + "\n" + "FROM passwords"
         + "\n" + "WHERE email = '" + email + "' AND password = '" + password + "';";

         /*ResultSet results = */statement.executeQuery(query);

         // Close the statement because we are done with it
         statement.close();
      } catch (SQLException e) {
         // If there is an error, lets just print the error
         System.err.println(e.getMessage());
      }

      // Finally we return all of the movies
      return login;
   }

   /**
    * Get all of the Movies in the database
    */
   // public ArrayList<String> getMovies() {
   // ArrayList<String> movies = new ArrayList<String>();

   // try {
   // // Prepare a new SQL Query & Set a timeout
   // Statement statement = connection.createStatement();
   // statement.setQueryTimeout(30);

   // // The Query
   // String query = "SELECT *" + "\n" + "FROM movie";

   // // Get Result
   // ResultSet results = statement.executeQuery(query);

   // // Process all of the results
   // // The "results" variable is similar to an array
   // // We can iterate through all of the database query results
   // while (results.next()) {
   // // We can lookup a column of the a single record in the
   // // result using the column name
   // // BUT, we must be careful of the column type!
   // // int id = results.getInt("mvnumb");
   // String movieName = results.getString("mvtitle");
   // // int year = results.getInt("yrmde");
   // // String type = results.getString("mvtype");

   // // For now we will just store the movieName and ignore the id
   // movies.add(movieName);
   // }

   // // Close the statement because we are done with it
   // statement.close();
   // } catch (SQLException e) {
   // // If there is an error, lets just print the error
   // System.err.println(e.getMessage());
   // }

   // // Finally we return all of the movies
   // return movies;
   // }

   /**
    * Get all the movies in the database by a given type. Note this takes a string
    * of the type as an argument! This has been implemented for you as an example.
    * HINT: you can use this to find all of the horror movies!
    */
   public ArrayList<String> getMoviesByType(String movieType) {
      ArrayList<String> movies = new ArrayList<String>();

      // Setup the variable for the JDBC connection
      // Connection connection = null;

      try {
         // Prepare a new SQL Query & Set a timeout
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);

         // The Query
         String query = "SELECT *" + "\n" + "FROM movie" + "\n" + "WHERE LOWER(mvtype) = LOWER('" + movieType + "')";
         System.out.println(query);

         // Get Result
         ResultSet results = statement.executeQuery(query);

         // Process all of the results
         while (results.next()) {
            String movieName = results.getString("mvtitle");
            movies.add(movieName);
         }

         // Close the statement because we are done with it
         statement.close();
      } catch (SQLException e) {
         // If there is an error, lets just pring the error
         System.err.println(e.getMessage());
      }

      // Finally we return all of the movies
      return movies;
   }

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
}