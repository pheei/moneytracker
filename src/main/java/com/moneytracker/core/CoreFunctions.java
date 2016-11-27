package com.moneytracker.core;

import com.moneytracker.Services.TransactionService;
import com.moneytracker.constants.Constants;
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

    //use LinkedList instead of ArrayList, since we have a lot of remove operation and don't need random access
    public void getAllTransactionsList(){
        this.transactionList = new LinkedList<>(transactionService.getAllInfor().getTransactions());

    }

    //method for removing donuts related transactions
    public void removeDonuts(){
        Iterator<Transaction> it = transactionList.iterator();

        while(it.hasNext()){

            Transaction t = it.next();

            //remove the transactions which have “Krispy Kreme Donuts” or “DUNKIN #336784” in merchant field
            if (t.getMerchant().equals("Krispy Kreme Donuts")
                    ||t.getMerchant().equals("DUNKIN #336784")){
                it.remove();
            }
        }
    }

    //add predicted transaction for the rest of current month
    public void addPredition(){

        //since the system current time may not be the same date as the last transaction recorded,
        //it is better to get time information from the last transaction
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

        //add prediction to the original transaction list
        List<Transaction> predictedList = transactionService.getPredictedInfor(year, month).getTransactions();
        for (Transaction t : predictedList){
            transactionList.add(t);
        }

    }

    //remove all the credit card transactions
    public Set<Transaction> removeCreditTransaction() {

        //create a set for recording all the removed transactions and their orders
        Set<Transaction> removedSet = new LinkedHashSet<>();

        //check whether the original list has at least one transaction. If not, we don't need to add prediction.
        if (transactionList.size()==0){
            return removedSet;
        }

        //use LinkedHashMap data structure to achieve O(n) linear time complexity
        //keep only the transactions within 24 hours interval in the map
        //every time add a new transaction, the map will discard the elements that beyond 24 hours from the head of the map
        //expose transaction amount as Key in the map, in order to have constant time access of the existing transaction that has same amount but opposite sign to current one
        //the space complexity will be O(n), where n represents the average number of transactions within 24 hours
        //the time complexity will be O(n) too, since we combined the advantage of both queue and hashmap

        //another approach will be more straight forward but lease efficient
        //we can simply use two ListIterators to compare all the transactions within 24 hours before and after current transaction to find a match
        //however, the time complexity will be O(m*n), where m represents the average number of transactions within 48 hours
        //therefore, when the number of transactions within 48 is large, the time complexity will even be close to O(n^2) in the worst case
        //considering the number of transactions will be huge for the real system of CapitalOne, first approach with LinkedHashMap can provide better performance, even though it is not noticeable for small list

        LinkedHashMap<Integer, Transaction> map = new LinkedHashMap<>();

        for (Transaction transaction : transactionList){

            //keep the current transaction time as long, in order to find time difference below
            long time = 0;
            try {
                time = utils.stringToDate(transaction.getTransaction_time()).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //remove the transactions that beyond 24 hours time frame from the head of map
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



            //check whether there is any matched transaction existing in the map, which has same amount but opposite sign and has credit card payment in the merchant field
            if (map.containsKey(-transaction.getAmount())
                    && (transaction.getMerchant().equals("Credit Card Payment")
                    || map.get(-transaction.getAmount()).getMerchant().equals("Credit Card Payment"))){
                Transaction t = map.get(-transaction.getAmount());

                //add to the removed transaction set
                removedSet.add(transaction);
                removedSet.add(t);

            }
            else{
                map.put(transaction.getAmount(), transaction);
            }
        }

        //remove deleted transactions from original list, using set to achieve constant time for finding a match
        removeFromSet(removedSet);

        return removedSet;
    }


    //generate monthly statistic
    public Map<String, SpendAndIncome> generateOutput(){
        Map<String, SpendAndIncome> map = new TreeMap<>();
        double totalSpent = 0.0, totalIncome = 0.0;


        for(Transaction transaction : transactionList){

            try {
                Date date = utils.stringToDate(transaction.getTransaction_time());
                YearMonth yearMonth = utils.dateToYearMonth(date);

                //add the initial element of a month
                if(!map.containsKey(yearMonth.toString())){
                    map.put(yearMonth.toString(), new SpendAndIncome("$0.00", "$0.00"));
                }

                SpendAndIncome spendAndIncome = map.get(yearMonth.toString());

                //add current transaction amount to the monthly aggregation value
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

        //calculate the average income and spent, which is generated by total number divided by the number of months
        totalIncome /= map.size();
        totalSpent /= map.size();
        SpendAndIncome si = new SpendAndIncome("$"+String.format("%.2f",totalSpent), "$"+String.format("%.2f",totalIncome));
        map.put("average", si);
        return map;
    }

    //remove the credit card payment transaction from the list
    public void removeFromSet(Set<Transaction> set){
        Iterator<Transaction> it = transactionList.iterator();
        while(it.hasNext()){
            Transaction t = it.next();
            if(set.contains(t)){
                it.remove();
            }
        }
    }

}

