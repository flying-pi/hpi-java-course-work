package edu.kpi.servlets;

import java.io.IOException;

import edu.kpi.domain.Cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartUtils {
    public static Cart getCart(HttpSession session){
        Cart cart;
        if (session.getAttribute(Constants.ATTRIBUTE_CART) == null) {
            cart = new Cart();
            session.setAttribute(Constants.ATTRIBUTE_CART, cart);
        } else {
            cart = (Cart)session.getAttribute(Constants.ATTRIBUTE_CART);
        }
        return cart;
    }

    public static void clearCart(HttpSession session){
        session.setAttribute(Constants.ATTRIBUTE_CART, new Cart());
    }

    public static void redirect(HttpServletRequest req, HttpServletResponse res, String path) throws IOException {
        res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + path)); 
    }

    public static void redirectBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getHeader("Referer") != null) {
            response.sendRedirect(request.getHeader("Referer"));
        } else {
            CartUtils.redirect(request, response, "/items");
        }
    }
}
