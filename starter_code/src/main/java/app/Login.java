package app;

import java.util.ArrayList;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Login implements Handler {
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>\n";

        // Add some Header information
        html = html + "<head>" + "<title>Login</title>\n";

        html = html + "<div>\n";

        html = html + "<div class='rmit'>\n";

        // Add HTML for the logo.png image
        html = html + "<img src='logo.png' height='60px' text-align='left'/>\n";

        html = html + "</div>\n";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='login.css' />\n";

        // Add the body
        html = html + "<body>\n";

        // Add HTML for the list of pages
        html = html + "<h2>FBL-LITE</h2>" + "<ul>\n";

        html = html + "<h3>Login</h3>";

        // Add HTML for form. Includes redirect to home
        // Includes Textfields for Email and Password

        /****** NEED TO ADD PROPER AUTHENTICATION ******/
        html = html + "<form action='/homepage.html'>" ;
        html = html +   "<label for='email'>Email:</label>";
        html = html +   "<input type='text' id='email' name='email'><br><br>";
        html = html +   "<label for='password'>Password:</label>";
        html = html +   "<input type='password' id='password' name='password'><br><br>";
        html = html +   "<button type='submit' class='btn btn-primary'>Login</button>";
        html = html + "</form>";

        // Finish the List HTML
        html = html + "</ul>\n";

        // Add HTML for Linking to Register Page
        html = html + "<a href='/register.html'>Need to Register?</a>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>\n" + "</div>";

        String email = context.formParam("email");
        String password = context.formParam("password");
        loginUser(email, password);

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String loginUser(String email, String password) {
        JDBCConnection jdbc = JDBCConnection.getConnection();
        ArrayList<String> login = jdbc.getLogin(email, password); 
        
        return login.toString();
    }
}
