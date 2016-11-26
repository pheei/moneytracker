package com.moneytracker.controller;

import com.moneytracker.constants.Filter;
import com.moneytracker.core.CoreFunctions;
import com.moneytracker.model.SpendAndIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hpishepei on 11/24/16.
 */

@RestController
public class Controllers {

    @Autowired
    private CoreFunctions coreFunctions;

    /**
    @RequestMapping(value={"/transactions/{type}"}, method= RequestMethod.GET)
    public Map<String, SpendAndIncome> getTransactions(@PathVariable("type") String type,
                                                       @RequestParam("donut_filter") boolean hasDonutFilter,
                                                       @RequestParam("credit_filter") boolean hasCreditFilter){

        Map<String, SpendAndIncome> monthlyIncomeAndSpentMap = new TreeMap<>();

        if(type.equals("with-prediction")){
            monthlyIncomeAndSpentMap = coreFunctions.getMonthlyTransaction(hasDonutFilter, true, hasCreditFilter);
        }

        return monthlyIncomeAndSpentMap;
    }
     */

    @RequestMapping(value={"/transactions"}, method= RequestMethod.GET)
    public Map<String, SpendAndIncome> getTransactions(){


        coreFunctions.getAllTransactionsList();

        return coreFunctions.generateOutput();

    }

    @RequestMapping(value={"/transactions/pre"}, method= RequestMethod.GET)
    public Map<String, SpendAndIncome> remove(){


        coreFunctions.getAllTransactionsList();

        coreFunctions.removeCreditTransaction();

        return coreFunctions.generateOutput();

    }

    @RequestMapping("/test")
    public Map<String, String> test(){
        Map<String, String> map = new HashMap<>();
        map.put("2010-2", "jajha");
        map.put("2014-2", "afaf");
        return map;
    }

}
