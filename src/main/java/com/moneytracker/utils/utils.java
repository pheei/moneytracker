package com.moneytracker.utils;

import com.moneytracker.constants.Constants;
import org.junit.Test;

import java.security.spec.ECField;
import java.text.DateFormat;
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

    public static boolean isWithin24Hours(String time1, String time2){

        Date d1 = new Date();
        Date d2 = new Date();

        try {
            d1 = utils.stringToDate(time1);
            d2 = utils.stringToDate(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d2.getTime()-d1.getTime()< Constants.one_day;

    }

    /**
    @Test
    public void testStringToDate() throws ParseException {
        System.out.println("@Test stringToDate(): " + "2014-10-07T12:59:00.000Z" + "=" + "Tue Oct 07 12:59:00 EDT 2014");
        Date d = stringToDate("2014-10-07T12:59:00.000Z");
        assertTrue(d.toString().equals("Tue Oct 07 12:59:00 EDT 2014"));
    }

    */

    public static void main(String args[]) throws ParseException {
        String str1 = "2015-03-01T15:07:00.000Z";
        String str2 = "2015-03-02T15:07:00.000Z";

        Date d1 = utils.stringToDate(str1);
        Date d2 = utils.stringToDate(str2);

        System.out.print(d2.getTime()-d1.getTime());


        LinkedHashMap<Double, Integer> map = new LinkedHashMap<>();
        map.put(1.0, 10);
        map.put(1.5, 20);
    }

}
