package servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie ck=new Cookie("user1","");
        ck.setMaxAge(0);
        resp.addCookie(ck);

        HttpSession session = req.getSession();

        session.removeAttribute("user");
        session.invalidate();

        resp.sendRedirect("/");
    }
}
