package com.moneytracker.Services;

import com.moneytracker.constants.Constants;
import com.moneytracker.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hpishepei on 11/24/16.
 */

@Component(value="getTransactionFromAPIService")
public class GetTransactionFromAPIService implements GetTransactionService{

    public AllInfor getAllInfor(){
        final String uri = Constants.uri_getAllTransaction;
        Param p = new Param();
        p.setUid(Constants.uid);
        p.setToken(Constants.token);
        p.setApi_token(Constants.api_token);

        Args arg = new Args(p);


        RestTemplate restTemplate = new RestTemplate();
        AllInfor allInfor = restTemplate.postForObject(uri, arg, AllInfor.class);



        return allInfor;
    }

    public AllInfor getPredictedInfor(int year, int month){

        String uri = Constants.uri_getForMonth;
        Param p = new Param();
        p.setUid(Constants.uid);
        p.setToken(Constants.token);
        p.setApi_token(Constants.api_token);

        PredictionArgs predictionArgs = new PredictionArgs(p, year, month);

        RestTemplate restTemplate = new RestTemplate();
        AllInfor allInfor = restTemplate.postForObject(uri, predictionArgs, AllInfor.class);

        return allInfor;


    }


}
