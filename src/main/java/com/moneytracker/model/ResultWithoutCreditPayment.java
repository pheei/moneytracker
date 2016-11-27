package com.moneytracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Set;

/**
 * Created by hpishepei on 11/26/16.
 */
public class ResultWithoutCreditPayment {
    @JsonProperty("All_Transactions")
    private Map<String, SpendAndIncome> map;

    @JsonProperty("Removed_Transactions")
    private Set<Transaction> set;


    public Map<String, SpendAndIncome> getMap() {
        return map;
    }

    public void setMap(Map<String, SpendAndIncome> map) {
        this.map = map;
    }

    public Set<Transaction> getSet() {
        return set;
    }

    public void setSet(Set<Transaction> set) {
        this.set = set;
    }
}
