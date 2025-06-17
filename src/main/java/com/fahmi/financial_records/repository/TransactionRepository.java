package com.fahmi.financial_records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fahmi.financial_records.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String>{
    List<Transaction> findByType(String type);   
}

