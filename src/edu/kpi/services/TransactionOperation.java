package edu.kpi.services;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionOperation<E> {
    E operation(Connection con) throws SQLException;
}
