package com.moneytracker.model;

/**
 * Created by hpishepei on 11/24/16.
 */
public class SpendAndIncome {
    private String spent;
    private String income;

    public SpendAndIncome(String spent, String income) {
        this.spent = spent;
        this.income = income;
    }

    public String getSpent() {
        return spent;
    }

    public void setSpent(String spent) {
        this.spent = spent;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
