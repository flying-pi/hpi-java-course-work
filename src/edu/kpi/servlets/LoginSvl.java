package edu.kpi.servlets;

import edu.kpi.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginSvl extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService)config.getServletContext().getAttribute(Constants.ATTRIBUTE_USER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        if (userService.login(login, pwd)) {
            HttpSession session = request.getSession();
            session.setAttribute(Constants.ATTRIBUTE_LOGIN, login);

            //TODO Not so good solution )) Rather hack than good one.
            if (request.getHeader("Referer") != null && request.getHeader("Referer").contains("/login")) {
                CartUtils.redirect(request, response, "/items");
            } else {
                CartUtils.redirectBack(request, response);
            }
        } else {
            CartUtils.redirect(request, response, "/login?error=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
