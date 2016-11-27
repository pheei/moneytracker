package com.moneytracker.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneytracker.Services.GetTransactionFromFileService;
import com.moneytracker.Services.GetTransactionService;
import com.moneytracker.core.CoreFunctions;
import com.moneytracker.model.AllInfor;
import com.moneytracker.model.SpendAndIncome;
import com.moneytracker.model.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by hpishepei on 11/27/16.
 */
public class CoreFunctionsTest {



    private List<Transaction> transactionList;
    private CoreFunctions coreFunctions;

    @Before
    public void executedBefore(){
        GetTransactionService getTransactionService = new GetTransactionFromFileService();
        this.transactionList = getTransactionService.getAllInfor().getTransactions();
        this.coreFunctions = new CoreFunctions();
        coreFunctions.setTransactionList(transactionList);
    }

    @Test
    public void testGetAllTransactionsList(){
        assertTrue(coreFunctions.getTransactionList().size() == 21);
    }

    @Test
    public void testRemoveDonuts(){
        coreFunctions.removeDonuts();
        int size = coreFunctions.getTransactionList().size();
        assertTrue(size == 17);
    }

    @Test
    public void testRemoveCreditTransaction(){
        Set<Transaction> set = coreFunctions.removeCreditTransaction();
        int size = set.size();
        assertTrue(size == 2 && coreFunctions.getTransactionList().size() == 19);
    }

    @Test
    public void testGenerateOutput(){
        Map<String, SpendAndIncome> map = coreFunctions.generateOutput();

        AllInfor allInfor = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            allInfor = mapper.readValue(new File("./src/main/resources/data/TransactionTestData2"), AllInfor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        coreFunctions.setTransactionList(allInfor.getTransactions());
        map = coreFunctions.generateOutput();


        String income1 = map.get("2014-10").getIncome();
        String spent1 = map.get("2014-10").getSpent();
        String income2 = map.get("2014-11").getIncome();
        String spent2 = map.get("2014-11").getSpent();
        String averageIncome = map.get("average").getIncome();
        String averagaSpent = map.get("average").getSpent();
        assertTrue(income1.equals("$9.90"));
        assertTrue(spent1.equals("$6.45"));
        assertTrue(income2.equals("$0.00"));
        assertTrue(spent2.equals("$168.32"));
        assertTrue(averageIncome.equals("$4.95"));
        assertTrue(averagaSpent.equals("$87.39"));


    }
}
