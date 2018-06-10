package edu.kpi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import edu.kpi.domain.CartItem;

public class OrderItemRepository {
    private static String INSERT_ORDER = "insert into order_items (order_id, item_id, count, price) values (?, ?, ?, ?)";

	public void create(Connection con, int orderId, List<CartItem> items) throws SQLException{
        try (PreparedStatement stmt = con.prepareStatement(INSERT_ORDER)) {
        	for (CartItem item : items) {
        		int i = 1;
        		stmt.setInt(i++, orderId);
        		stmt.setString(i++, item.getOperation().getId());
        		stmt.setInt(i++, 1);   	//TODO this constant is invalid for shop
        		stmt.setInt(i, 0);    	//TODO this constant is invalid for shop
        		stmt.executeUpdate();
            }
        }
	}

}
