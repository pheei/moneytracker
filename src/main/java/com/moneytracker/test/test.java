package com.moneytracker.test;

import com.moneytracker.constants.Constants;
import com.moneytracker.model.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by hpishepei on 11/24/16.
 */
public class test {
    public static void main(String args[]){
        final String uri = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";


        Param p = new Param();
        p.setUid(Constants.uid);
        p.setToken(Constants.token);
        p.setApi_token(Constants.api_token);

        Args arg = new Args();
        arg.setArgs(p);


        RestTemplate restTemplate = new RestTemplate();
        AllInfor allInfor = restTemplate.postForObject(uri, arg, AllInfor.class);

        List<Transaction> transactionList = allInfor.getTransactions();

        for(Transaction transaction : transactionList){
            System.out.println(transaction.toString());
        }


    }
}
