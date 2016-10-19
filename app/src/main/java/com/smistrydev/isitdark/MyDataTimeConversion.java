package com.smistrydev.isitdark;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by sanjaymistry on 16/10/2016.
 */

public class MyDataTimeConversion {


    public static void main(String[] args) throws ParseException {

        System.out.println("Start");

        SimpleDateFormat sdfUTC = new SimpleDateFormat("h:mm:ss aa");
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdfHere = new SimpleDateFormat("h:mm:ss aa");

        Calendar utcCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar hereCal = Calendar.getInstance();

        String utcTime = "7:18:31 PM";
        Date utcDate = sdfUTC.parse(utcTime);
        long utcMS = utcDate.getTime();
        utcCal.setTimeInMillis(utcMS);


        System.out.println("utc date: " + utcDate);
        System.out.println("utc cale: " + utcCal.getTime());
        System.out.println("===========");
        System.out.println("time here: " + hereCal.getTime());
        hereCal.setTimeInMillis(utcCal.getTimeInMillis());
        System.out.println("time utc here: " + hereCal.getTime());

        TimeZone hereTimeZone = TimeZone.getTimeZone(hereCal.getTimeZone().getID());
        utcCal.setTimeZone(hereTimeZone);

        System.out.println("utc here time: " + sdfHere.format(utcDate));

    }


}
