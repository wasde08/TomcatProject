package servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Welcome to servlets</h2>");
            Cookie[] c = request.getCookies();
            out.println("Name: " + c[1].getValue());
            out.println("<div><a href=\"/image\">Image Click here</a></div>");
            out.println("<div><a href=\"/upload.html\">Upload</a></div>");
            out.println("<div><a href=\"/logout\">Back</a></div>");

        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}
