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
      html = html + "<link rel='stylesheet' type='text/css' href='common.css' />\n";

      // Add the body
      html = html + "<body>\n";

      // Add HTML for the logo.png image
      html = html + "<img src='logo.png' height='200px'/>\n";

      // Add HTML for the list of pages
      html = html + "<h1>Homepage</h1>" + "<p>Links to sub-pages</p>" + "<ul>\n";

      // Link for each page
      html = html + "<li> <a href='page1.html'>Page 1</a> </li>\n";
      html = html + "<li> <a href='page2.html'>Page 2</a> </li>\n";
      html = html + "<li> <a href='page3.html'>Page 3</a> </li>\n";
      html = html + "<li> <a href='page4.html'>Page 4</a> </li>\n";
      html = html + "<li> <a href='page5.html'>Page 5</a> </li>\n";
      html = html + "<li> <a href='page6.html'>Page 6</a> </li>\n";

      // Finish the List HTML
      html = html + "</ul>\n";

      // Finish the HTML webpage
      html = html + "</body>" + "</html>\n";

      // DO NOT MODIFY THIS
      // Makes Javalin render the webpage
      context.html(html);
   }

}