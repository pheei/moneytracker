package com.moneytracker.core;

import com.moneytracker.Services.TransactionService;
import com.moneytracker.constants.Filter;
import com.moneytracker.model.AllInfor;
import com.moneytracker.model.MonthlyTransaction;
import com.moneytracker.model.SpendAndIncome;
import com.moneytracker.model.Transaction;
import com.moneytracker.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.*;

/**
 * Created by hpishepei on 11/24/16.
 */

@Component
public class CoreFunctions {

    @Autowired
    private TransactionService transactionService;


    public Map<String, SpendAndIncome> getMonthlyTransaction(Filter filter){

        List<Transaction> transactionList = transactionService.getAllInfor().getTransactions();

        Map<String, SpendAndIncome> map = new TreeMap<>();
        double totalSpent = 0.0, totalIncome = 0.0;


        for(Transaction transaction : transactionList){

            if (filter.equals(Filter.IGNORE_DONUTS)){
                if (transaction.getMerchant().equals("Krispy Kreme Donuts")
                        ||transaction.getMerchant().equals("DUNKIN #336784")){
                    continue;
                }
            }

            try {
                Date date = utils.stringToDate(transaction.getTransaction_time());
                YearMonth yearMonth = utils.dateToYearMonth(date);

                if(!map.containsKey(yearMonth.toString())){
                    map.put(yearMonth.toString(), new SpendAndIncome("$0.00", "$0.00"));
                }

                SpendAndIncome spendAndIncome = map.get(yearMonth.toString());

                if(transaction.getAmount()<0){
                    totalSpent += -transaction.getAmount()/10000.0;
                    String spent = utils.addMoney(spendAndIncome.getSpent(), "$"+String.format("%.2f", -transaction.getAmount()/10000.0));
                    spendAndIncome.setSpent(spent);

                }
                else{
                    System.out.println(transaction.getAmount());
                    totalIncome += transaction.getAmount()/10000.0;
                    String income = utils.addMoney(spendAndIncome.getIncome(), "$"+String.format("%.2f", transaction.getAmount()/10000.0));
                    System.out.println(yearMonth+" -- "+income);
                    spendAndIncome.setIncome(income);
                }
                map.put(yearMonth.toString(), spendAndIncome);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        totalIncome /= map.size();
        totalSpent /= map.size();
        SpendAndIncome si = new SpendAndIncome("$"+String.format("%.2f",totalSpent), "$"+String.format("%.2f",totalIncome));
        map.put("average", si);




        return map;
    }


    public static void main(String args[]){

        CoreFunctions coreFunctions = new CoreFunctions();
        Map<String, SpendAndIncome> map = coreFunctions.getMonthlyTransaction(Filter.NO_FILTER);



    }
}
