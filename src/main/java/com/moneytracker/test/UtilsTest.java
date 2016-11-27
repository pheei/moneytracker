package com.moneytracker.test;

import com.moneytracker.utils.Utils;
import org.junit.Test;

import java.text.ParseException;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.moneytracker.utils.Utils.stringToDate;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by hpishepei on 11/27/16.
 */
public class UtilsTest {
    private String testDate = "2014-10-07T12:59:00.000Z";


    @Test
    public void testStringToDate(){
        //System.out.println("@Test stringToDate(): " + "2014-10-07T12:59:00.000Z" + "=" + "Tue Oct 07 12:59:00 EDT 2014");
        Date d = null;
        d = Utils.stringToDate(testDate);
        assertTrue(d.toString().equals("Tue Oct 07 12:59:00 EDT 2014"));

    }

    @Test
    public void testDateToYearMonth(){
        Date date = new GregorianCalendar(2016, Calendar.FEBRUARY, 11).getTime();
        YearMonth yearMonth = Utils.dateToYearMonth(date);
        assertTrue(yearMonth.toString().equals("2016-02"));

    }

    @Test
    public void testAddMoney(){
        String str1 = "$20.55";
        String str2 = "$10.45";

        String str3 = "$0.0";
        String str4 = "$0.23";
        String result1 = Utils.addMoney(str1, str2);
        String result2 = Utils.addMoney(str3, str4);
        assertTrue(result1.equals("$31.00"));
        assertTrue(result2.equals("$0.23"));

    }
}
