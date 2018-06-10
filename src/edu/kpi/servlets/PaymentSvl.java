package edu.kpi.servlets;

import edu.kpi.domain.Cart;
import edu.kpi.domain.Order;
import edu.kpi.services.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/payment")
public class PaymentSvl extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        orderService = (OrderService)config.getServletContext().getAttribute(Constants.ATTRIBUTE_ORDER_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = CartUtils.getCart(request.getSession());
        if (cart.getCount() == 0) {
            CartUtils.redirect(request, response, "/payment?error=Empty cart");
            return;
        }
        //TODO Add more strict validation rules
        if (request.getParameter("addr") == null || request.getParameter("credit_card") == null) {
            CartUtils.redirect(request, response, "/payment?error=Invalid address or credit card");
            return;
        }
        Order created = new Order(0, (String)request.getSession().getAttribute(Constants.ATTRIBUTE_LOGIN),
                                  request.getParameter("credit_card"), request.getParameter("addr"), cart.getItems());
        Order order = orderService.create(created);
        CartUtils.clearCart(request.getSession());
        request.setAttribute("order", order);
        getServletContext().getRequestDispatcher("/order_confirm.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = CartUtils.getCart(request.getSession());
        getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
    }
}
