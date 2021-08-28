package app;

//import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Register implements Handler {
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/register.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>\n";

        // Add some Header information
        html = html + "<head>" + "<title>Register for FBL</title>\n";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />\n";

        // Add the body
        html = html + "<body>\n";

        // Add HTML for the logo.png image
        html = html + "<img src='logo.png' height='200px'/>\n";

        // Add HTML for the list of pages
        html = html + "<h1>Register</h1>" + "<ul>\n";

        // Add HTML for form. Includes redirect to home
        // Includes Textfields for Email and Password
        /****** NEED TO ADD PROPER UPDATING/INSERT TO FBLMember TABLE ******/
        html = html + "<form action='/homepage.html'>" + "<label for='email'>Email:</label>"
                + "<input type='text' id='email' name='email'><br><br>" + "<label for='password'>Password:</label>"
                + "<input type='password' id='password' name='password'><br><br>"
                + "<label for='fullname'>Full Name:</label>" + "<input type'text' id='fullname' name='fullname'<br><br>"
                + "<label for='screenname'>Screen Name:</label>"
                + "<input type'text' id='screenname' name='screenname'<br><br>"
                + "<label for='dob'>Date of Birth:</label>" + "<input type'text' id='dob' name='dob'<br><br>"
                + "<label for='gender'>Gender:</label>" + "<input type'text' id='gender' name='gender'<br><br>"
                + "<label for='status'>Status:</label>" + "<input type'text' id='status' name='status'<br><br>"
                + "<label for='location'>Location:</label>" + "<input type'text' id='location' name='location'<br><br>"
                + "<input type='submit' value='Register'>" + "</form>";

        // Add Function for the 'Register' Button
        // JDBCConnection jdbc = JDBCConnection.getConnection();

        // ArrayList<String> register = jdbc.getRegister();

        // Finish the List HTML
        html = html + "</ul>\n";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>\n";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
}
