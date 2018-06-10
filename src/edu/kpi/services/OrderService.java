package edu.kpi.services;

import edu.kpi.domain.MathItem;
import edu.kpi.domain.Order;
import edu.kpi.repository.FormulaRepository;
import edu.kpi.repository.OrderItemRepository;
import edu.kpi.repository.OrderRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderService {
	private TransactionManager transactionManager;
	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;
	
    public OrderService(TransactionManager transactionManager,
			OrderRepository orderRepository,
			OrderItemRepository orderItemRepository) {
		super();
		this.transactionManager = transactionManager;
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
	}

	public Order create(final Order src) {
		return transactionManager.doInTransaction(new TransactionOperation<Order>() {
	          @Override
	          public Order operation(Connection con) throws SQLException {
	        	  Order returned = orderRepository.create(con, src);
	        	  orderItemRepository.create(con, returned.getOrderNumber(), src.getItems());
	        	  return returned;
	          }
	    });
    }
}
