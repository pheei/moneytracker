package com.moneytracker.core;

import com.moneytracker.Services.TransactionService;
import com.moneytracker.constants.Constants;
import com.moneytracker.constants.Filter;
import com.moneytracker.model.*;
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


    private List<Transaction> transactionList;


    public void getAllTransactionsList(){
        this.transactionList = new LinkedList<>(transactionService.getAllInfor().getTransactions());

    }

    public void removeDonuts(){
        Iterator<Transaction> it = transactionList.iterator();
        while(it.hasNext()){
            Transaction t = it.next();
            if (t.getMerchant().equals("Krispy Kreme Donuts")
                    ||t.getMerchant().equals("DUNKIN #336784")){
                it.remove();
            }
        }
    }

    public void addPredition(){
        Transaction lastTransaction = transactionList.get(transactionList.size()-1);
        Date date = new Date();

        try {
            date = utils.stringToDate(lastTransaction.getTransaction_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        List<Transaction> predictedList = transactionService.getPredictedInfor(year, month).getTransactions();
        for (Transaction t : predictedList){
            transactionList.add(t);
        }

    }

    public void removeCreditTransaction() {
        List<Transaction> indexList = new LinkedList<>(transactionList);

        List<Transaction> removedList = new ArrayList<>();


        if (transactionList.size()==0){
            return;
        }

        LinkedHashMap<Integer, Transaction> map = new LinkedHashMap<>();

        for (Transaction transaction : indexList){


            long time = 0;
            try {
                time = utils.stringToDate(transaction.getTransaction_time()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Iterator it = map.keySet().iterator();
            while(it.hasNext()){
                Transaction t = map.get((Integer) it.next());
                try {
                    if(time - utils.stringToDate(t.getTransaction_time()).getTime() >= Constants.one_day){
                        it.remove();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }



            if (map.containsKey(-transaction.getAmount())
                    && (transaction.getMerchant().equals("Credit Card Payment")
                    || map.get(-transaction.getAmount()).getMerchant().equals("Credit Card Payment"))){
                Transaction t = map.get(-transaction.getAmount());

                removedList.add(transaction);
                removedList.add(t);

                transactionList.remove(t);
                transactionList.remove(transaction);

            }
            else{
                map.put(transaction.getAmount(), transaction);
            }
        }

    }

    public Map<String, SpendAndIncome> generateOutput(){
        Map<String, SpendAndIncome> map = new TreeMap<>();
        double totalSpent = 0.0, totalIncome = 0.0;


        for(Transaction transaction : transactionList){

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
                    totalIncome += transaction.getAmount()/10000.0;
                    String income = utils.addMoney(spendAndIncome.getIncome(), "$"+String.format("%.2f", transaction.getAmount()/10000.0));
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

/**

    public Map<String, SpendAndIncome> getMonthlyTransaction(boolean hasDonutFilter, boolean hasPrediction, boolean hasCreditFilter){

        List<Transaction> transactionList = transactionService.getAllInfor().getTransactions();

        if(hasPrediction){
            addPreditionalToList(transactionList);
        }

        Map<String, SpendAndIncome> map = new TreeMap<>();

        if (hasCreditFilter){
            removeCreditTransaction(transactionList);
        }


        map = this.generateMonthlyStats(transactionList, hasDonutFilter);

        return map;
    }



    private void addPreditionalToList(List<Transaction> transactionList){
        Transaction lastTransaction = transactionList.get(transactionList.size()-1);
        Date date = new Date();

        try {
            date = utils.stringToDate(lastTransaction.getTransaction_time());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        List<Transaction> predictedList = transactionService.getPredictedInfor(year, month).getTransactions();
        for (Transaction t : predictedList){
            transactionList.add(t);
        }

    }



    private Map<String, SpendAndIncome> generateMonthlyStats(List<Transaction> transactionList, boolean hasDonutFilter){
        Map<String, SpendAndIncome> map = new TreeMap<>();
        double totalSpent = 0.0, totalIncome = 0.0;


        for(Transaction transaction : transactionList){

            if (hasDonutFilter){
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
                    totalIncome += transaction.getAmount()/10000.0;
                    String income = utils.addMoney(spendAndIncome.getIncome(), "$"+String.format("%.2f", transaction.getAmount()/10000.0));
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


    private void removeCreditTransaction(List<Transaction> transactionList) {
        List<Transaction> originalList = new LinkedList<>(transactionList);
        List<Transaction> indexList = new LinkedList<>(transactionList);

        List<Transaction> removedList = new ArrayList<>();


        if (originalList.size()==0){
            return;
        }

        LinkedHashMap<Integer, Transaction> map = new LinkedHashMap<>();

        for (Transaction transaction : indexList){


            long time = 0;
            try {
                time = utils.stringToDate(transaction.getTransaction_time()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Iterator it = map.keySet().iterator();
            while(it.hasNext()){
                Transaction t = map.get((Integer) it.next());
                try {
                    if(time - utils.stringToDate(t.getTransaction_time()).getTime() >= Constants.one_day){
                        it.remove();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }



            if (map.containsKey(-transaction.getAmount())
                    && (transaction.getMerchant().equals("Credit Card Payment")
                    || map.get(-transaction.getAmount()).equals("Credit Card Payment"))){
                Transaction t = map.get(-transaction.getAmount());

                removedList.add(transaction);
                removedList.add(t);

                originalList.remove(t);
                originalList.remove(transaction);

            }
            else{
                map.put(transaction.getAmount(), transaction);
            }
        }
        transactionList = originalList;

    }

    public static void main(String args[]){





    }

 */

}

