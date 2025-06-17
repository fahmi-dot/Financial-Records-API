package com.fahmi.financial_records.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fahmi.financial_records.model.Transaction;
import com.fahmi.financial_records.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        transaction.setDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

    public Double getTotalBalance() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .mapToDouble(trx -> trx.getType().
                    equalsIgnoreCase("income") ? 
                    trx.getAmount() : 
                    -trx.getAmount()
                ).sum();
    }
}
