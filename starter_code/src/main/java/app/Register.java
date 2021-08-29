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
        /****** NEED TO ADD PROPER UPDATING/INSERT TO FBLMember TABLE ******/

        // Add Function for the 'Register' Button
        //JDBCConnection jdbc = JDBCConnection.getConnection();

        //ArrayList<String> register = jdbc.getRegister();

        // Finish the List HTML
        html = html + "</ul>\n";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>\n";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
        context.render("register.html");
    }
}
