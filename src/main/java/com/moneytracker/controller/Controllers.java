package com.moneytracker.controller;

import com.moneytracker.Services.TransactionService;

/**
 * Created by hpishepei on 11/24/16.
 */
public class Controllers {

    TransactionService transactionService;

    public Controllers(TransactionService transactionService){
        this.transactionService = transactionService;
    }


}
