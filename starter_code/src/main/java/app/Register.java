package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Base64;

public class Register implements Handler {
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/register.html";
    JDBCConnection jdbc = JDBCConnection.getConnection();

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>\n";

        //THIS PAGE ALLOWS FOR REGISTRATION
        //These Lines of Code Below get the Details from our register.html form 
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

        createUser(email, fullname, screenname, dob, gender, status, location);// sends the information above to this specific method
        
        String password = context.formParam("password");
        if (password == null || password == ""){
            password = "dummy";
        }
        String enpassword = passEncrypt(password);
        createLogin(enpassword, email);// sends the password and email to this method only
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
        context.render("register.html");
    }

    //This method encrypts the password and spits out as an enpassword as above
    public String passEncrypt(String password) throws Exception{
        byte[] X= Base64.getEncoder().encode(password.getBytes());
        String return_string = new String(X);

        return return_string;
    }
    
    public String createLogin(String enpassword, String email){
        JDBCConnection jdbc = JDBCConnection.getConnection();
        jdbc.loginInsert(enpassword, email);//Calls the loginInsert method in JDBC
        return null;
    }

    public String createUser(String email, String fullname, String screenname, String dob, String gender, String status, String location) {
        JDBCConnection jdbc = JDBCConnection.getConnection();
        jdbc.getRegister(email, fullname, screenname, dob, gender, status, location);//Calls the getRegister method in JDBC
        return null;
    }
}
