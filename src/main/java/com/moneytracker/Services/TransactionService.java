package com.moneytracker.Services;

import com.moneytracker.constants.Constants;
import com.moneytracker.model.AllTransactions;
import com.moneytracker.model.Args;
import com.moneytracker.model.Param;
import com.moneytracker.model.Transaction;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by hpishepei on 11/24/16.
 */
public class TransactionService {
    public AllTransactions getAllTransactions(){
        final String uri = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";
        Param p = new Param();
        p.setUid(Constants.uid);
        p.setToken(Constants.token);
        p.setApi_token(Constants.api_token);

        Args arg = new Args();
        arg.setArgs(p);


        RestTemplate restTemplate = new RestTemplate();
        AllTransactions allTransections = restTemplate.postForObject(uri, arg, AllTransactions.class);

        return allTransections;
    }

    public static void main(String args[]){
        TransactionService transactionService = new TransactionService();


    }
}
