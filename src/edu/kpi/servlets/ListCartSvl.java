package edu.kpi.servlets;

import edu.kpi.domain.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class ListCartSvl extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartUtils.getCart(req.getSession());
        getServletContext().getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}
