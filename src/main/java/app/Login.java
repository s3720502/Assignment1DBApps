package app;

//import java.util.ArrayList;

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

        // Add some CSS (external file)
<<<<<<< HEAD
        html = html + "<link rel='stylesheet' type='text/css' href='login.css' />\n";
=======
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />\n";
>>>>>>> eef0a0271dc19d0246e4c1f0960e8f1b18041fa0

        // Add the body
        html = html + "<body>\n";

        // Add HTML for the logo.png image
<<<<<<< HEAD
        html = html + "<img src='logo.png' height='200px' class='center' />\n"; 

        // Add HTML for the list of pages
        html = html + "<h2>Login</h2>" + "<ul>\n";
=======
        html = html + "<img src='logo.png' height='200px'/>\n";

        // Add HTML for the list of pages
        html = html + "<h1>Login</h1>" + "<ul>\n";
>>>>>>> eef0a0271dc19d0246e4c1f0960e8f1b18041fa0

        // Add HTML for form. Includes redirect to home
        // Includes Textfields for Email and Password

        /****** NEED TO ADD PROPER AUTHENTICATION ******/
        html = html + "<form action='/homepage.html'>" + "<label for='email'>Email:</label>"
                + "<input type='text' id='email' name='email'><br><br>" + "<label for='password'>Password:</label>"
                + "<input type='password' id='password' name='password'><br><br>"
                + "<input type='submit' value='Login'>" + "</form>";

        // Finish the List HTML
        html = html + "</ul>\n";

        // Add HTML for Linking to Register Page
<<<<<<< HEAD
        html = html + "<a href='/register.html'>Register an Account</a>";
=======
        html = html + "<a href='/register.html'>Need to Register?</a>";
>>>>>>> eef0a0271dc19d0246e4c1f0960e8f1b18041fa0

        // Finish the HTML webpage
        html = html + "</body>" + "</html>\n";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }
}