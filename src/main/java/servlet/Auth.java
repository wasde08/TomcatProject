package servlet;

import DAO.JDBCConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "Auth", urlPatterns = "/")
public class Auth implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String login = req.getParameter("name");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        if ((session) != null && (session.getAttribute("user")) != null) {
            resp.sendRedirect("/user");
            //req.getRequestDispatcher("user.jsp").forward(req, resp);
        } else if (login==null) {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }else {
            String validate = null;
            if (JDBCConnection.validate(login, password)) {
                validate = "true";
            } else {
                validate = "false";
            }
            if ("true".equals(validate)) {
                resp.addCookie(new Cookie("user1", login));
                req.getSession().setAttribute("user","name");
                resp.sendRedirect("/user");
                //req.getRequestDispatcher("/user").forward(req, resp);
            } else {
                req.getSession().setAttribute("user", "userName");
                req.getRequestDispatcher("invalidLogin.jsp").forward(req, resp);
            }
        }
    }
}
