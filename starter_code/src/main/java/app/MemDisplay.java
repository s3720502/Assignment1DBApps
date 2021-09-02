package app;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class MemDisplay implements Handler {
    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/memdisplay.html";
    JDBCConnection jdbc = JDBCConnection.getConnection();

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>\n";
        context.html(html);
        context.render("register.html");
    }
}
