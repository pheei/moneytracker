package com.moneytracker.utils;

import org.junit.Test;

import java.security.spec.ECField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by hpishepei on 11/24/16.
 */
public class utils {
    public static Date stringToDate(String date) throws ParseException {
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

    /**
    @Test
    public void testStringToDate() throws ParseException {
        System.out.println("@Test stringToDate(): " + "2014-10-07T12:59:00.000Z" + "=" + "Tue Oct 07 12:59:00 EDT 2014");
        Date d = stringToDate("2014-10-07T12:59:00.000Z");
        assertTrue(d.toString().equals("Tue Oct 07 12:59:00 EDT 2014"));
    }

    */

    public static void main(String args[]){
        System.out.print(addMoney("$13.14","$42.00"));
        String str = "2015-03-01T15:07:00.000Z";
        try {
            Date d = stringToDate(str);
            System.out.println(d.toString());
            YearMonth y = dateToYearMonth(stringToDate(str));
            System.out.println(y.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
