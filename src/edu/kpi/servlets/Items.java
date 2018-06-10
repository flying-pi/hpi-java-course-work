package edu.kpi.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kpi.domain.Cart;
import edu.kpi.services.FormulaService;
import edu.kpi.domain.MathItem;

import java.util.List;

/**
 * Servlet implementation class Items
 */
@WebServlet("/items")
public class Items extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FormulaService formulaService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Items() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig cfg) throws ServletException {
    	super.init(cfg);
    	formulaService = (FormulaService)cfg.getServletContext().getAttribute("edu.kpi.services.formulaService");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MathItem> list = formulaService.getAllTovars();
		CartUtils.getCart(request.getSession());
		request.setAttribute(Constants.ATTRIBUTE_ITEMS, list);
		getServletContext().getRequestDispatcher("/items.jsp").forward(request, response);
	}
}
