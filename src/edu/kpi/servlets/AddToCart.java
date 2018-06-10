package edu.kpi.servlets;

import edu.kpi.domain.Cart;
import edu.kpi.domain.CartItem;
import edu.kpi.domain.MathItem;
import edu.kpi.services.FormulaService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add_to_cart")
public class AddToCart extends HttpServlet {
    private FormulaService formulaService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        formulaService = (FormulaService)config.getServletContext().getAttribute(Constants.ATTRIBUTE_FORMULA_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operatorId = request.getParameter(Constants.ADD_CART_PARAM_ID);
        String operandString = request.getParameter(Constants.ADD_CART_OPERAND);
        if (operandString == null || operatorId == null) {
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        MathItem operator = formulaService.getById(operatorId);
        if (operator == null) {
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int operand = 0;
        if (! operandString.trim().equals("")) {
            try {
                operand = Integer.parseInt(operandString);
            } catch(NumberFormatException nfe) {
            	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        HttpSession session = request.getSession();
        Cart cart = CartUtils.getCart(session);
        cart.add(new CartItem(operator, operand));
        response.getWriter().println(cart.getCount());
    }
}
