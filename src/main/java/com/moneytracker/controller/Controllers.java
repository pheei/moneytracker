package com.moneytracker.controller;

import com.moneytracker.core.CoreFunctions;
import com.moneytracker.model.PredictOutput;
import com.moneytracker.model.SpendAndIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class Controllers {

    @Autowired
    private CoreFunctions coreFunctions;

    @RequestMapping(value={"/transaction/{type}"}, method= RequestMethod.GET)
    public Object getTransactions(@PathVariable("type") String type,
                                  @RequestParam(value = "no-donut", defaultValue = "false") boolean nodonut,
                                  @RequestParam(value = "no-credit", defaultValue = "false") boolean nocredit){

        //load all transactions from the GetAllTransactions endpoint
        coreFunctions.getAllTransactionsList();

        //add prediction
        if (type.equals("predict")){
            coreFunctions.addPredition();
        }

        //remove donut related transaction
        if (nodonut){
            coreFunctions.removeDonuts();
        }

        //remove transactions that has credit card payment
        if (nocredit){
            PredictOutput output = new PredictOutput();
            output.setSet(coreFunctions.removeCreditTransaction());
            output.setMap(coreFunctions.generateOutput());
            return output;
        }


        //return the data model
        return coreFunctions.generateOutput();

    }


}
