package com.example.jbill.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Transaction {
    @Id
    private String id;
    @DBRef
    private Account debit;
    @DBRef
    private Account credit;
    private int amount;

    public Transaction(Account debit, Account credit, int amount) {
        this.debit = debit;
        this.credit = credit;
        this.amount = amount;
    }
}
