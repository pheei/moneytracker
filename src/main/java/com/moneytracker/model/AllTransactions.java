package com.moneytracker.model;

import java.util.List;

/**
 * Created by hpishepei on 11/24/16.
 */
public class AllTransactions {
    private String error = "";
    private List<Transaction> transactions;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
