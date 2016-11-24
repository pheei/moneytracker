package com.moneytracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

/**
 * Created by hpishepei on 11/24/16.
 */
public class Transactions {


    private List<Transaction> transactions;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
