package com.moneytracker.controller;

import com.moneytracker.Services.TransactionService;
import com.moneytracker.constants.Filter;
import com.moneytracker.core.CoreFunctions;
import com.moneytracker.model.MonthlyTransaction;
import com.moneytracker.model.SpendAndIncome;
import com.moneytracker.model.Transaction;
import com.moneytracker.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hpishepei on 11/24/16.
 */

@RestController
public class Controllers {

    @Autowired
    private CoreFunctions coreFunctions;

    @RequestMapping("/transactions")
    public Map<String, SpendAndIncome> getTransactions(){
        Map<String, SpendAndIncome> monthlyIncomeAndSpentMap = coreFunctions.getMonthlyTransaction(Filter.NO_FILTER);
        return monthlyIncomeAndSpentMap;
    }

    @RequestMapping("/test")
    public Map<String, String> test(){
        Map<String, String> map = new HashMap<>();
        map.put("2010-2", "jajha");
        map.put("2014-2", "afaf");
        return map;
    }

}
