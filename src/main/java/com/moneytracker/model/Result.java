package com.moneytracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by hpishepei on 11/26/16.
 */
public class Result {
    @JsonProperty("All_Transactions")
    private Map<String, SpendAndIncome> map;

    public Map<String, SpendAndIncome> getMap() {
        return map;
    }

    public void setMap(Map<String, SpendAndIncome> map) {
        this.map = map;
    }
}
