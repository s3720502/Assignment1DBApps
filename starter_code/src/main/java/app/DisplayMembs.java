package app;

import java.util.ArrayList;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class DisplayMembs implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/displaymembs.html";

   @Override
   public void handle(Context context) throws Exception {
      // Create a simple HTML webpage in a String
      String html = "<html>\n";

      // Add some Header information
      html = html + "<head>" + "<title>All Members</title>\n";

      // Add some CSS (external file)
      html = html + "<link rel='stylesheet' type='text/css' href='Page1.css' />\n";

      // Add the body
      html = html + "<body>\n";

      // Add HTML for link back to the homepage
      html = html + "<p>\n";
      html = html + "   <a href='/homepage.html'>Return to Homepage</a>\n";
      html = html + "</p>\n";

      JDBCConnection jdbc = JDBCConnection.getConnection();

      // Next we will ask this *class* for the movies
      ArrayList<String> members = jdbc.getMembers();

      html = html + "<h1>Members</h1>" + "<ul>\n";

      //Prints all members
      for (String member : members) {
         html = html + "<li>" + member + "</li>\n";
      }

      // Finish the List HTML
      html = html + "</ul>\n";

      // Finish the HTML webpage
      html = html + "</body>" + "</html>\n";

      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.html(html);
   }

}
