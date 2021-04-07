package com.example.jbill.repo;

import com.example.jbill.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepo extends MongoRepository<Transaction, String> {
}
