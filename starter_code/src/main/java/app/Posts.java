package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Posts implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/posts.html";
   private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   //THIS PAGE WILL HANDLE THE POSTS INSERTION

   @Override
   public void handle(Context context) throws Exception {
      String html = "";

      Timestamp ts = new Timestamp(System.currentTimeMillis());//Get a timestamp of users system

      //System.out.println(ts); //this is for console debugging
      
      //This gets the data that the user inputs from our posts.html page
      String postID = context.formParam("postid");
      if (postID == null || postID == ""){
          postID = null;
      }
      String content = context.formParam("content");
      if (content == null || content == ""){
          content = null;
      }
      String posttime = sdf3.format(ts);
      String parpostID = context.formParam("postid");
      if (parpostID == null || parpostID == ""){
          parpostID = null;
      }
      String postemail = context.formParam("postemail");
      if (postemail == null || postemail == ""){
          postemail = null;
      }

      makePosts(postID, content, posttime, parpostID, postemail);
      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.html(html);
      context.render("posts.html");
   }

   public String makePosts(String postID, String content, String posttime, String parpostID, String postemail){
      JDBCConnection jdbc = JDBCConnection.getConnection();
      jdbc.insertPosts(postID, content, posttime, parpostID, postemail);//Calls insertposts in the JDBC
      return null;
   }
}
