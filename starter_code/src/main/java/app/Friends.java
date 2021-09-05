package app;

import java.util.ArrayList;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.sql.Date;

public class Friends implements Handler {
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/friends.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>\n";

        // Add some Header information
        html = html + "<head>" + "<title>Friends</title>\n";
        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />\n";

        // Add the body
        html = html + "<body>\n";

        // Add HTML for the list of pages
        html = html + "<h2>Add Friends</h2>\n";

        // Add HTML for form. Includes redirect to home
        // Includes Textfields for Email and Password

        /****** Create Form for Friend Request ******/
        html = html + "<form action='/friends.html' method='post'>\n"; 
        html = html +   "<div class=recemail>\n";
        html = html +       "<label for='email'>Enter Recipient Email:</label>\n";
        html = html +       "<input type='text' id='recemail' name='recemail'><br><br>\n" ;
        html = html +   "</div>\n";
        html = html +   "<div class=reqemail>\n";
        html = html +       "<label for='password'>Enter Your Email</label>\n";
        html = html +       "<input type='text' id='reqemail' name='reqemail'><br><br>\n";
        html = html +   "<div>\n";
        html = html +   "<button type='submit' class='btn btn-primary'>Send Request</button>\n";
        html = html + "</form>\n";

        // Add HTML for Linking to Register Page
        html = html + "<a href='/homepage.html'>Return to Homepage</a>\n";

        // Finish the HTML webpage
        html = html + "</body>\n";
        html = html + "</html>\n";

        String recemail = context.formParam("recemail");
        String reqemail = context.formParam("reqemail");
        String status = "Sent";
        String date = new Date(System.currentTimeMillis()).toString();
        System.out.println(date);
        
        friendRequests(recemail, reqemail, status, date);

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
        //context.sessionAttribute(key, value);
    }

    public ArrayList<String> friendRequests(String recemail, String reqemail, String status, String date){
        JDBCConnection jdbc = JDBCConnection.getConnection();
        jdbc.friendRequest(reqemail, recemail, status, date);
        return null;
    }

    // public String updateFriends(String status, String email){
    //     JDBCConnection jdbc = JDBCConnection.getConnection();
    //     jdbc.friendUpdate(status, email);
    //     return null;
    // }
}