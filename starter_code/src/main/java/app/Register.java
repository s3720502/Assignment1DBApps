package app;

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

        /****** INSERT WORKS NOW I JUST TO ADD NULL VERIFICATION ******/        
        String email = context.formParam("email");
        if (email == null || email == ""){
            email = null;
        }
        String fullname = context.formParam("fullname");
        if (fullname == null || fullname == ""){
            fullname = null;
        }
        String screenname = context.formParam("screenname");
        if (screenname == null || screenname == ""){
            screenname = null;
        }
        String dob = context.formParam("dob");
        if (dob == null || dob == ""){
            dob = null;
        }
        String gender = context.formParam("gender");
        if (gender == null || gender == ""){
            gender = null;
        }
        String status = context.formParam("status");
        if (status == null || status == ""){
            status = null;
        }
        String location = context.formParam("location");
        if (location == null || location == ""){
            location = null;
        }
        createUser(email, fullname, screenname, dob, gender, status, location);
        
        String password = context.formParam("password");
        if (location == null || location == ""){
            location = null;
        }
        createLogin(password, email);
        
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
        context.render("register.html");
    }

    public String createLogin(String password, String email){
        JDBCConnection jdbc = JDBCConnection.getConnection();
        jdbc.loginInsert(password, email);
        return null;
    }

    public String createUser(String email, String fullname, String screenname, String dob, String gender, String status, String location) {
        JDBCConnection jdbc = JDBCConnection.getConnection();
        jdbc.getRegister(email, fullname, screenname, dob, gender, status, location);
        return null;
    }
}
