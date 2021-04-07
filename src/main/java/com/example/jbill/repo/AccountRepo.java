package com.example.jbill.repo;

import com.example.jbill.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepo extends MongoRepository<Account, String> {
}
