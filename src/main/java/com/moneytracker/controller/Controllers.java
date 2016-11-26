package com.moneytracker.controller;

import com.moneytracker.constants.Filter;
import com.moneytracker.core.CoreFunctions;
import com.moneytracker.model.SpendAndIncome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hpishepei on 11/24/16.
 */

@RestController
public class Controllers {

    @Autowired
    private CoreFunctions coreFunctions;

    @RequestMapping(value={"/transaction/{type}"}, method= RequestMethod.GET)
    public Map<String, SpendAndIncome> getTransactions(@PathVariable("type") String type,
                                                       @RequestParam(value = "no-donut", defaultValue = "false") boolean nodonut,
                                                       @RequestParam(value = "no-credit", defaultValue = "false") boolean nocredit){


        coreFunctions.getAllTransactionsList();

        if (type.equals("predict")){
            coreFunctions.addPredition();
        }

        if (nodonut){
            coreFunctions.removeDonuts();
        }

        if (nocredit){
            coreFunctions.removeCreditTransaction();
        }

        return coreFunctions.generateOutput();

    }



    @RequestMapping("/test")
    public Map<String, String> test(){
        Map<String, String> map = new HashMap<>();
        map.put("2010-2", "jajha");
        map.put("2014-2", "afaf");
        return map;
    }

}
