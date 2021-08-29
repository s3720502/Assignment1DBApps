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

        /****** NEED TO ADD PROPER UPDATING/INSERT TO FBLMember TABLE ******/        
        String email = context.formParam("email");
        createUser(email);
        String fullname = context.formParam("fullname");
        createUser(fullname);
        String screenname = context.formParam("screename");
        createUser(screenname);
        String dob = context.formParam("dob");
        createUser(dob);
        String gender = context.formParam("gender");
        createUser(gender);
        String status = context.formParam("status");
        createUser(status);
        String location = context.formParam("location");
        createUser(location);
        
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
        context.render("register.html");
    }

    public String createUser(String type) {
        JDBCConnection jdbc = JDBCConnection.getConnection();
        String register = jdbc.getRegister(type, type, type, type, type, type, type, type); 
        
        return register.toString();
    }
}
