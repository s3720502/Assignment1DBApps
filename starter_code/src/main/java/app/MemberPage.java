package app;

import java.util.ArrayList;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class MemberPage implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/memberpage.html";

   //THIS PAGE WILL DISPLAY ALL MEMBER DETAILS
   @Override
   public void handle(Context context) throws Exception {
      // Create a simple HTML webpage in a String
      String html = "<html>\n";

      html = html + "<head>" + "<title>Member Details</title>\n";

      html = html + "<link rel='stylesheet' type='text/css' href='common.css' />\n";

      html = html + "<body>\n";
      //imbedded html form created in java for our page
      html = html + "<form action='/memberpage.html' method='post'>\n";
      html = html + "   <h1>Enter Email and Full name:</h1>\n";
      html = html + "   <div class='memberDetails'>\n";
      html = html + "      <label for='email'>Email:</label>\n";
      html = html + "      <input type='text' id='membemail' name='membemail' required><br><br>\n";
      html = html + "   </div>\n";
      html = html + "   <div>\n";
      html = html + "      <label for='fullname'>Full Name:</label>\n";
      html = html + "      <input type'text' id='fullname' name='fullname' required><br><br>\n";
      html = html + "   </div>\n";
      html = html + "   <button type='submit' class='btn btn-primary'>Get Details</button>\n";
      html = html + "</form>\n";


      String email = context.formParam("membemail");
        if (email == null || email == ""){
         html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
         html = html + outputDetails(email);
        }
      
        html = html + "<p>\n";
        html = html + "   <a href='/homepage.html'>Return to Homepage</a>\n";
        html = html + "</p>\n";
      
      html = html + "</body>" + "</html>\n";
      context.html(html);
   }

   //This method displays all a members data when searched
   public String outputDetails(String email){
      String html = "";
      html = html + "<h2>Your Member Details</h2>\n";

      JDBCConnection jdbc = JDBCConnection.getConnection();
      ArrayList<String> memDetails = jdbc.getMemDetails(email);
   
      html = html + "<ul>";
      for (String memDeets : memDetails){
         html = html + "<li>" + memDeets + "</li>\n";
      }
      html = html + "</ul>";

      return html;
   }
}