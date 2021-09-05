package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class UpdateDetails implements Handler {


    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/updatedetails.html";
    JDBCConnection jdbc = JDBCConnection.getConnection();
    
    //THIS PAGE WILL UPDATE A MEMBERS DETAILS
    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>\n";

        //These Lines of Code Below get the Details from our UpdateDetails.html form
        String email = context.formParam("upemail");
        if (email == null || email == ""){
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        String screenname = context.formParam("upscreen");
        if (email == null || email == ""){
             html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        String status = context.formParam("upstatus");
        if (email == null || email == ""){
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        String visi = context.formParam("upvisi");
        if (email == null || email == ""){
            html = html + "<h2><i>No Results to show for textbox</i></h2>\n";
        }
        updateMemDetails(screenname, status, visi, email);//Gets sent into this method
      
        context.html(html);
        context.render("updatedetails.html");
    }

    public String updateMemDetails(String screenname, String status, String visi, String email){
        JDBCConnection jdbc = JDBCConnection.getConnection();
        jdbc.updateDetails(screenname, status, visi, email);//sends it through to our updateDetails in the JDBC
        return null;
    }
}