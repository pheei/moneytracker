package com.moneytracker.model;

/**
 * Created by hpishepei on 11/24/16.
 */
public class MonthlyTransaction {
    private String month;
    private String spent;
    private String income;

    public MonthlyTransaction(String month, String spent, String income){
        this.income = income;
        this.spent = spent;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    @Override
    public String toString() {
        return "month: "+month+" spend:  "+spent + "  income:  "+income;
    }
}
