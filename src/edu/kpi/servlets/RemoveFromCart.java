package edu.kpi.servlets;

import edu.kpi.domain.Cart;
import edu.kpi.services.FormulaService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remove_from_cart")
public class RemoveFromCart extends HttpServlet {
    public static final String REMOVE_PARAMETER_POSITION = "position";
    private FormulaService formulaService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        formulaService = (FormulaService)config.getServletContext().getAttribute(Constants.ATTRIBUTE_FORMULA_SERVICE);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter(REMOVE_PARAMETER_POSITION) == null) {
        	CartUtils.redirect(request, response, "/cart?error=Item to remove has not been specified");
            return;
        }
        int pos = -1;
        try {
            pos = Integer.parseInt(request.getParameter(REMOVE_PARAMETER_POSITION));
        } catch(NumberFormatException nfe){
        	CartUtils.redirect(request, response, "/cart?error=Position should be numeric");
            return;
        }

        Cart cart = CartUtils.getCart(request.getSession());
        if (pos < 0 || pos >= cart.getCount()) {
        	CartUtils.redirect(request, response, "/cart?error=Invalid item number");
            return;
        }

        cart.remove(pos);
        CartUtils.redirect(request, response, "/cart");
    }
}
