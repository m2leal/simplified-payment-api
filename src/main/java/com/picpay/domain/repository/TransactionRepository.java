package com.picpay.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.picpay.domain.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
}
