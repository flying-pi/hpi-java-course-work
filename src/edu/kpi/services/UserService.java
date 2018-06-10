package edu.kpi.services;

import edu.kpi.domain.User;
import edu.kpi.repository.RepositoryException;
import edu.kpi.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    TransactionManager transactionManager;
    UserRepository userRepository;

    public UserService(TransactionManager transactionManager, UserRepository userRepository) {
        this.transactionManager = transactionManager;
        this.userRepository = userRepository;
    }

    public void add(final User user) {
        transactionManager.doInTransaction(new TransactionOperation<Void>() {
            @Override
            public Void operation(Connection con) throws SQLException {
                userRepository.create(con, user);
                return null;
            }
        });
    }

    public boolean isLoginUsed(final String login) {
        return transactionManager.doInTransaction(new TransactionOperation<Boolean>() {
            @Override
            public Boolean operation(Connection con) throws SQLException{
                return userRepository.findById(con, login) != null;
            }
        });
    }

    public boolean login(final String login, final String password) {
        return transactionManager.doInTransaction(new TransactionOperation<Boolean>() {
            @Override
            public Boolean operation(Connection con) throws SQLException {
                User user = userRepository.findById(con, login);
                return user != null && user.getPassword() != null && user.getPassword().equals(password);
            }
        });
    }
}
