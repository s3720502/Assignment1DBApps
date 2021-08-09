package app;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
   private static final String DATABASE_USERNAME = "romovies";
   private static final String DATABASE_PASSWORD = "movies";

   private static final String DATABASE_URL = "jdbc:oracle:thin:@//localhost:9922/CSAMPR1.its.rmit.edu.au";
   private static JDBCConnection jdbc = null;
   private static Connection connection;

   /**
   * Singleton function to return single copy of this class to other classes
   **/
   public static JDBCConnection getConnection(){

      //check that ssh session is still open (if not reopen)
      SSHTunnel.getSession();

      //check that JDBCconnection is available (if not establish)
      if(jdbc==null){
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
   public static void closeConnection(){
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

   /**
    * Get all of the Movies in the database
    */
   public ArrayList<String> getMovies() {
      ArrayList<String> movies = new ArrayList<String>();

      try {
         // Prepare a new SQL Query & Set a timeout
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);

         // The Query
         String query = "SELECT *"   + "\n" +
                        "FROM movie" ;

         // Get Result
         ResultSet results = statement.executeQuery(query);

         // Process all of the results
         // The "results" variable is similar to an array
         // We can iterate through all of the database query results
         while (results.next()) {
            // We can lookup a column of the a single record in the
            // result using the column name
            // BUT, we must be careful of the column type!
            // int id = results.getInt("mvnumb");
            String movieName = results.getString("mvtitle");
            // int year = results.getInt("yrmde");
            // String type = results.getString("mvtype");

            // For now we will just store the movieName and ignore the id
            movies.add(movieName);
         }

         // Close the statement because we are done with it
         statement.close();
      } catch (SQLException e) {
         // If there is an error, lets just print the error
         System.err.println(e.getMessage());
      }

      // Finally we return all of the movies
      return movies;
   }

   /**
    * Get all the movies in the database by a given type. Note this takes a string
    * of the type as an argument! This has been implemented for you as an example.
    * HINT: you can use this to find all of the horror movies!
    */
   public ArrayList<String> getMoviesByType(String movieType) {
      ArrayList<String> movies = new ArrayList<String>();

      // Setup the variable for the JDBC connection
      //Connection connection = null;

      try {
         // Prepare a new SQL Query & Set a timeout
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);

         // The Query
         String query = "SELECT *"                                        + "\n" +
                        "FROM movie"                                      + "\n" +
                        "WHERE LOWER(mvtype) = LOWER('" + movieType + "')";
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
}
