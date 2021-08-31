package app;

//import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class Register implements Handler {
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/register.html";
    JDBCConnection jdbc = JDBCConnection.getConnection();

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>\n";

        //TESTING BRANCH
        /****** NEED TO ADD PROPER UPDATING/INSERT TO FBLMember TABLE ******/        
        String email = context.formParam("email");
        if (email == null)
        {
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
            createUser(email);
        }
        String fullname = context.formParam("fullname");
        if (fullname == null)
        {
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
            createUser(fullname);
        }
        String screenname = context.formParam("screename");
        if (screenname == null)
        {
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
            createUser(screenname);
        }
        String dob = context.formParam("dob");
        if (dob == null)
        {
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
            createUser(dob);
        }
        String gender = context.formParam("gender");
        if (gender == null)
        {
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
            createUser(gender);
        }
        String status = context.formParam("status");
        if (status == null)
        {
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
            createUser(status);
        }
        String location = context.formParam("location");
        if (location == null || location == "")
        {
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        else{
            createUser(location);
        }
        
        
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
        context.render("register.html");
    }

    public String createUser(String type) {
        JDBCConnection jdbc = JDBCConnection.getConnection();
        jdbc.getRegister(type, type, type, type, type, type, type);
        return null;
    }
}
