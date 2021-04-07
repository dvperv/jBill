package com.example.jbill.service;

import com.example.jbill.model.Account;
import com.example.jbill.model.Transaction;
import com.example.jbill.repo.AccountRepo;
import com.example.jbill.repo.TransactionRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntryService {
    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;

    public EntryService(TransactionRepo transactionRepo, AccountRepo accountRepo) {
        this.transactionRepo = transactionRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    public void post(Account debtor, Account creditor, int amount){
        Account d = accountRepo.findById(debtor.getId()).get();//TODO Check is null
        Account c = accountRepo.findById(creditor.getId()).get();//TODO Check is null
        transactionRepo.save(new Transaction(debtor, creditor, amount));
        d.changeBalance(amount);
        accountRepo.save(d);
        c.changeBalance(-amount);
        accountRepo.save(c);
    }
}
