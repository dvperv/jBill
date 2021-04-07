package com.example.jbill.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private String name;
    private int balance = 0;
    private String owner;
    private String ownerGroup;

    public Account(String name) {
        this.name = name;
    }

    public void changeBalance(int amount){
        this.balance += amount;
    }
}

/*
Two own accounts: Cash (actual Bank position) and Earnings (money, earned from clients for services)
Own accounts are only accessible to ownerGroup group members
Client accounts are accessed by anyone from ownerGroup members, and by owner (an employee of a client)

Payment from client in advance: Dr Cash, Cr AP Client
Client expenditure: Dr AP Client, Cr Earnings
Withdrawal from own money ("dividends" payment to owners): Dr Earnings, Cr Cash
*/
