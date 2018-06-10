package edu.kpi.repository;

import edu.kpi.domain.MathItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormulaRepository {
    private static String FIND_ALL = "select id, name, parameterized, description from items";
    private static String FIND_BY_ID = "select id, name, parameterized, description from items where id = ?";

    public List<MathItem> findAll(Connection con) throws SQLException{
        try (PreparedStatement stmt = con.prepareStatement(FIND_ALL)) {
            List<MathItem> items = new ArrayList<>();

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                items.add(unmap(resultSet));
            }
            return items;
        }
    }

    public MathItem findById(Connection con, String id) throws SQLException{
        try (PreparedStatement stmt = con.prepareStatement(FIND_BY_ID)) {
            stmt.setString(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return unmap(resultSet);
            }
            return null;
        }
    }

    private MathItem unmap(ResultSet rs) throws SQLException{
        int k = 1;
        String id = rs.getString(k++);
        String name = rs.getString(k++);
        String desc = rs.getString(k++);
        boolean param = rs.getBoolean(k);
        return new MathItem(id, name, desc, param);
    }
}
