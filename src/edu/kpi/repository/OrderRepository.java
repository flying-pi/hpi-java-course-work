package edu.kpi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import edu.kpi.domain.Order;

public class OrderRepository {
    private static String INSERT_ORDER = "insert into orders (user, addr, card, created) values (?, ?, ?, ?)";

	public Order create(Connection con, Order order) throws SQLException{
        try (PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int i = 1, id;
            stmt.setString(i++, order.getUserId());
            stmt.setString(i++, order.getAddress());
            stmt.setString(i++, order.getCard());
            stmt.setTimestamp(i++, new Timestamp(new Date().getTime()));
            stmt.executeUpdate();
            try(ResultSet rs = stmt.getGeneratedKeys()){
            	if (!rs.next()){
            		throw new RepositoryException("Can't find generated fields");
            	}
            	id = rs.getInt(1);
            }
            
            return new Order(id, order);
        }
	}
}
