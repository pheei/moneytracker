package com.moneytracker.controller;

import com.moneytracker.core.CoreFunctions;
import com.moneytracker.model.Result;
import com.moneytracker.model.ResultWithoutCreditPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class Controllers {

    @Autowired
    private CoreFunctions coreFunctions;

    @RequestMapping(value={"/transaction/{type}"}, method= RequestMethod.GET)
    public String getTransactions(Model model, @PathVariable("type") String type,
                                  @RequestParam(value = "no_donut", defaultValue = "false") boolean nodonut,
                                  @RequestParam(value = "no_credit", defaultValue = "false") boolean nocredit){

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
            ResultWithoutCreditPayment output = new ResultWithoutCreditPayment();
            output.setSet(coreFunctions.removeCreditTransaction());
            output.setMap(coreFunctions.generateOutput());
            model.addAttribute(output);
            return "jsonTemplate";
        }


        //return the data model
        Result result = new Result();
        result.setMap(coreFunctions.generateOutput());
        model.addAttribute(result);

        return "jsonTemplate";


    }


}
