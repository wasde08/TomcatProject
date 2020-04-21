package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Welcome to servlets</h2>");
            Cookie[] c=request.getCookies();
            out.println("Name: "+c[1].getValue());
            out.println("<div><a href=\"/image\">Image Click here</a></div>");
            out.println("<div><a href=\"/upload.html\">Upload</a></div>");
            out.println("<div><a href=\"/logout\">Back</a></div>");

        }catch(Exception exp){
            System.out.println(exp);
        }
    }
}
