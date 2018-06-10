package edu.kpi.repository;

import edu.kpi.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private static String INSERT_USER = "insert into users (login, password, name, email, advertising) values (?, ?, ?, ?, ?)";
    private static String FIND_BY_ID = "select login, password, name, email, advertising from users where login = ?";

    public void create(Connection con, User user) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(INSERT_USER)) {
            int i = 1;
            stmt.setString(i++, user.getLogin());
            stmt.setString(i++, user.getPassword());
            stmt.setString(i++, user.getName());
            stmt.setString(i++, user.getEmail());
            stmt.setInt(i++, user.isAdvertising()?1:0);
            stmt.executeUpdate();
        }
    }

    public User findById(Connection con, String login) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(FIND_BY_ID)) {
            stmt.setString(1, login);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setLogin(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setAdvertising(resultSet.getBoolean(5));
                return user;
            } else {
                return null;
            }
        }
    }
}
