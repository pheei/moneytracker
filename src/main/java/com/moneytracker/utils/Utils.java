package com.moneytracker.utils;

import com.moneytracker.constants.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by hpishepei on 11/24/16.
 */
public class Utils {
    public static Date stringToDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date d = null;
        try{
            d = formatter.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return d;
    }

    public static YearMonth dateToYearMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);

        return YearMonth.of(year, month);
    }

    public static String addMoney(String originalAmount, String newAmount){
        originalAmount = originalAmount.substring(1);
        newAmount = newAmount.substring(1);
        double num1 = Double.parseDouble(originalAmount);
        double num2 = Double.parseDouble(newAmount);
        double num3 = num1+num2;
        return "$" + String.format("%.2f", num3);
    }

    public static boolean isWithin24Hours(String time1, String time2){

        Date d1 = new Date();
        Date d2 = new Date();

        d1 = Utils.stringToDate(time1);
        d2 = Utils.stringToDate(time2);

        return d2.getTime()-d1.getTime()< Constants.one_day;

    }


}
