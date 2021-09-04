package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PostsDisplay implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/postdisplay.html";

   //THIS PAGE WILL DISPLAY ALL MEMBER DETAILS
   @Override
   public void handle(Context context) throws Exception {
      // Create a simple HTML webpage in a String
      String html = "<html>\n";

      html = html + "<head>" + "<title>All Posts</title>\n";

      html = html + "<link rel='stylesheet' type='text/css' href='common.css' />\n";

      html = html + "<body>\n";

      JDBCConnection jdbc = JDBCConnection.getConnection();
      ArrayList<String> disPosts = jdbc.displayPosts();
      
      html = html + "<h2>All Posts</h2>\n";

      html = html + "<ul>";
      for (String disPo : disPosts){
         html = html + "<li>" + disPo + "</li>\n";
      }
      html = html + "</ul>";

      html = html + "<p>Return to Homepage: \n";
      html = html + "<a href='/'>Link to Homepage</a>\n";
      html = html + "</p>\n";
      
      html = html + "</body>" + "</html>\n";
      context.html(html);
   }

   // public String outputPosts(){
   //    String html = "";
   //    html = html + "<h2>All Posts</h2>\n";

   //    JDBCConnection jdbc = JDBCConnection.getConnection();
   //    ArrayList<String> disPosts = jdbc.displayPosts(disPosts);
   
   //    html = html + "<ul>";
   //    for (String disPo : disPosts){
   //       html = html + "<li>" + disPo + "</li>\n";
   //    }
   //    html = html + "</ul>";

   //    return html;
   // }
}