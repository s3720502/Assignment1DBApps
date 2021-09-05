package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin by writing the raw HTML into a Java
 * String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Index implements Handler {

   // URL of this page relative to http://localhost:7000/
   public static final String URL = "/homepage.html";

   @Override
   public void handle(Context context) throws Exception {
      // Create a simple HTML webpage in a String
      String html = "<html>\n";

      // Add some Header information
      html = html + "<head>" + "<title>Homepage</title>\n";

      // Add some CSS (external file)
      html = html + "<link rel='stylesheet' type='text/css' href='Index.css' />\n";

      // Add the body
      html = html + "<body>\n";

      // Add HTML for the logo.png image
      html = html + "<img src='logo.png' height='100px'/>\n";

      // Add HTML for the list of pages
      html = html + "<h1>Homepage</h1>" + "<p>Links to sub-pages</p>" + "<ul>\n";

      // Link for each page
      html = html + "<li> <a href='displaymembs.html'>Display All Members</a> </li>\n";
      html = html + "<li> <a href='memberpage.html'>Member Search</a> </li>\n";
      html = html + "<li> <a href='updatedetails.html'>Update Details</a> </li>\n";
      html = html + "<li> <a href='posts.html'>Make a Post</a> </li>\n";
      html = html + "<li> <a href='postdisplay.html'>All Posts</a> </li>\n";
      html = html + "<li> <a href='friends.html'>Friends</a> </li>\n";
      html = html + "<li> <a href='friendreq.html'>Friend Requests</a> </li>\n";
      
      // Finish the List HTML
      html = html + "</ul>\n";

      // Finish the HTML webpage
      html = html + "</body>" + "</html>\n";

      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.html(html);
   }

}
