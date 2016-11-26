package com.moneytracker.Services;

import com.moneytracker.constants.Constants;
import com.moneytracker.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by hpishepei on 11/24/16.
 */

@Component
public class TransactionService {
    public AllInfor getAllInfor(){
        final String uri = "https://2016.api.levelmoney.com/api/v2/core/get-all-transactions";
        Param p = new Param();
        p.setUid(Constants.uid);
        p.setToken(Constants.token);
        p.setApi_token(Constants.api_token);

        Args arg = new Args();
        arg.setArgs(p);


        RestTemplate restTemplate = new RestTemplate();
        AllInfor allInfor = restTemplate.postForObject(uri, arg, AllInfor.class);


        return allInfor;
    }

    public static void main(String args[]){
        TransactionService transactionService = new TransactionService();
        List<Transaction> transactions = transactionService.getAllInfor().getTransactions();
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getAmount() / 10000.0 + "---" + transaction.getMerchant());
        }

    }
}
