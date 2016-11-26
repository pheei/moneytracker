package com.moneytracker.model;

/**
 * Created by hpishepei on 11/26/16.
 */
public class PredictionArgs {
    Param args;
    int year;
    int month;

    public PredictionArgs(Param args, int year, int month) {
        this.args = args;
        this.year = year;
        this.month = month;
    }

    public Param getArgs() {
        return args;
    }

    public void setArgs(Param args) {
        this.args = args;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
