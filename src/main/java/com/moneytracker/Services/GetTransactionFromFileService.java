package com.moneytracker.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneytracker.model.AllInfor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by hpishepei on 11/27/16.
 */

@Component
@Qualifier("getTransactionFromFile")
public class GetTransactionFromFileService implements GetTransactionService {

    @Override
    public AllInfor getAllInfor() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            AllInfor allInfor = mapper.readValue(new File("./src/main/resources/data/TransactionTestData"), AllInfor.class);
            return allInfor;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AllInfor();
    }

    @Override
    public AllInfor getPredictedInfor(int year, int month) {
        return null;
    }

    public static void main(String args[]){
        GetTransactionFromFileService get = new GetTransactionFromFileService();
        AllInfor all = get.getAllInfor();
        System.out.print(all.toString());
    }
}
