package edu.kpi.services;

import edu.kpi.domain.MathItem;
import edu.kpi.repository.FormulaRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class FormulaService {
   private TransactionManager transactionManager;
   private FormulaRepository formulaRepository;

   public FormulaService(TransactionManager transactionManager, FormulaRepository formulaRepository) {
      this.transactionManager = transactionManager;
      this.formulaRepository = formulaRepository;
   }

   public List<MathItem> getAllTovars() {
      return transactionManager.doInTransaction(new TransactionOperation<List<MathItem>>() {
         @Override
         public List<MathItem> operation(Connection con) throws SQLException {
            return formulaRepository.findAll(con);
         }
      });
   }

   public MathItem getById(final String id){
      return transactionManager.doInTransaction(new TransactionOperation<MathItem>() {
         @Override
         public MathItem operation(Connection con) throws SQLException {
            return formulaRepository.findById(con, id);
         }
      });
   }

}
