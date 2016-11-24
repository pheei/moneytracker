package com.moneytracker.core;

import com.moneytracker.Services.TransactionService;
import com.moneytracker.model.AllTransactions;
import com.moneytracker.model.MonthlyTransaction;
import com.moneytracker.model.Transaction;
import com.moneytracker.utils.utils;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.*;

/**
 * Created by hpishepei on 11/24/16.
 */
public class CoreFunctions {

    public List<MonthlyTransaction> getMonthlyTransaction(AllTransactions allTransactions){
        List<MonthlyTransaction> monthlyTransactionList = new ArrayList<>();

        List<Transaction> transactionList = allTransactions.getTransactions();

        Map<String, MonthlyTransaction> map = new TreeMap<>();

        for(Transaction transaction : transactionList){
            try {
                Date date = utils.stringToDate(transaction.getTransaction_time());
                YearMonth yearMonth = utils.dateToYearMonth(date);


                if(!map.containsKey(yearMonth.toString())){

                    MonthlyTransaction monthlyTransaction = new MonthlyTransaction(yearMonth.toString(), "$0.00", "$0.00");

                    if(transaction.getAmount()<0){
                        String spent = "$" + String.format("%.2f", -transaction.getAmount()/10000.0);
                        monthlyTransaction.setSpent(spent);
                    }
                    else{
                        String income = "$" + String.format("%.2f", transaction.getAmount()/10000.0);
                        monthlyTransaction.setIncome(income);
                    }
                    map.put(yearMonth.toString(), monthlyTransaction);
                }

                else{
                    MonthlyTransaction monthlyTransaction = map.get(yearMonth.toString());
                    if(transaction.getAmount()<0){
                        String spent = utils.addMoney(monthlyTransaction.getSpent(), String.format("%.2f", -transaction.getAmount()/10000.0));
                        monthlyTransaction.setSpent(spent);
                    }
                    else{
                        String income = utils.addMoney(monthlyTransaction.getIncome(), String.format("%.2f", transaction.getAmount()/10000.0));
                        monthlyTransaction.setIncome(income);
                    }
                    map.put(yearMonth.toString(), monthlyTransaction);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            monthlyTransactionList.add((MonthlyTransaction) pair.getValue());
        }


        return monthlyTransactionList;
    }


    public static void main(String args[]){
        TransactionService transactionService = new TransactionService();
        AllTransactions allTransactions = transactionService.getAllTransactions();

        CoreFunctions coreFunctions = new CoreFunctions();
        List<MonthlyTransaction> li = coreFunctions.getMonthlyTransaction(allTransactions);


        for (MonthlyTransaction m : li){
            System.out.println(m.toString());
        }
    }
}
