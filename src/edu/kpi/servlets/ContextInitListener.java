package edu.kpi.servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import edu.kpi.repository.FormulaRepository;
import edu.kpi.repository.OrderItemRepository;
import edu.kpi.repository.OrderRepository;
import edu.kpi.repository.RepositoryException;
import edu.kpi.repository.UserRepository;
import edu.kpi.services.FormulaService;
import edu.kpi.services.OrderService;
import edu.kpi.services.TransactionManager;
import edu.kpi.services.UserService;

/**
 * Application Lifecycle Listener implementation class ContextInitListener
 *
 */
@WebListener
public class ContextInitListener implements ServletContextListener {
    /**
     * Default constructor. 
     */
    public ContextInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        DataSource dataSource = null;
        try {
            Context initCtx = new InitialContext();
            dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/math_ds");
        }catch (Exception ex) {
            throw new RepositoryException(ex);
        }
        TransactionManager transactionManager = new TransactionManager(dataSource);

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(transactionManager, userRepository);
        arg0.getServletContext().setAttribute(Constants.ATTRIBUTE_USER_SERVICE, userService);

        FormulaRepository formulaRepository = new FormulaRepository();
        FormulaService formulaService = new FormulaService(transactionManager, formulaRepository);
        arg0.getServletContext().setAttribute(Constants.ATTRIBUTE_FORMULA_SERVICE, formulaService);

        OrderRepository orderRepository = new OrderRepository();
        OrderItemRepository orderItemRepository = new OrderItemRepository();
        OrderService orderService = new OrderService(transactionManager, orderRepository, orderItemRepository);;
        arg0.getServletContext().setAttribute(Constants.ATTRIBUTE_ORDER_SERVICE, orderService);

    }
	
}
