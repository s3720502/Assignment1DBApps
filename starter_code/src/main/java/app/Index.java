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

      html = html + "<div>\n";

      html = html + "<div class='rmit'>\n";

      // Add HTML for the logo.png image
      html = html + "<img src='logo.png' height='60px' text-align='left'/>\n";

      html = html + "</div>\n";

      // Add the body
      html = html + "<body>\n";

      // Add HTML for the list of pages
      html = html + "<h2>FBL-HOME</h2>" + "<p>Links to sub-pages</p>" + "<ul>\n";

      // Add some CSS (external file)
      html = html + "<link rel='stylesheet' type='text/css' href='Index.css' />\n";

      // Link for each page
      html = html + "<a href='register.html'>Register</a>\n";
      html = html + "<a href='memberpage.html'>Member Details</a>\n";
      html = html + "<a href='updatedetails.html'>Update Details</a>\n";
      html = html + "<a href='page1.html'>Page 1</a>\n";
      html = html + "<a href='page2.html'>Page 2</a>\n";
      html = html + "<a href='page3.html'>Page 3</a>\n";
      html = html + "<a href='posts.html'>Create Post</a>\n";
      html = html + "<a href='postdisplay.html'>View Posts</a>\n";

      // Finish the List HTML
      html = html + "</ul>\n";

      // Finish the HTML webpage
      html = html + "</body>" + "</div>" + "</html>\n";

      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.html(html);
   }

}
