package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples. This page currently: - Provides
 * a link back to the index page - Displays the list of movies from the Movies
 * Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2021. email: halil.ali@rmit.edu.au
 */
public class MemberPage implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/memberpage.html";

   //THIS PAGE WILL DISPLAY ALL MEMBER DETAILS

   //IT WILL ALSO UPDATE A USERS DETAILS
   @Override
   public void handle(Context context) throws Exception {
      // Create a simple HTML webpage in a String
      String html = "<html>\n";
      // Look up some information from JDBC
      // First we need to use your JDBCConnection class

      String email = context.formParam("membemail");
        if (email == null || email == ""){
            email = null;
        }
      JDBCConnection jdbc = JDBCConnection.getConnection();

      // Next we will ask this *class* for the movies
      ArrayList<String> memDetails = jdbc.getMemDetails(email);

      for (String memDeets : memDetails){
         html = html + "<li>" + memDeets + "</li>\n";
      }

      context.html(html);
      context.render("memberpage.html");
   }

}
