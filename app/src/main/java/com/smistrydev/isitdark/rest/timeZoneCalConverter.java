package com.smistrydev.isitdark.rest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by sanjaymistry on 19/10/2016.
 */

public class TimeZoneCalConverter {

    private static final String TAG = "RemoteLocal";


    private TimeZone timeZone;

    public TimeZoneCalConverter(TimeZone timeZoneRemote) {
        this.timeZone = timeZoneRemote;
    }

    public static void main(String[] args) {
        TimeZoneCalConverter converter = new TimeZoneCalConverter(TimeZone.getTimeZone("UTC"));


        Calendar sunRise = converter.getConvertedCal( "7:18:31 PM", "h:mm:ss aa");
        Calendar sunSet = converter.getConvertedCal( "8:19:20 AM", "h:mm:ss aa");

        Calendar now = Calendar.getInstance();
        if (now.before(sunRise) || now.after(sunSet)) {
            System.out.println("It is dark outside!!!!!!!");
        } else {
            System.out.println("It is NOT dark outside!!!!!!!");
        }

        System.out.println("Sun Rise: " + sunRise.getTime());
        System.out.println("Sun Set : " + sunSet.getTime());

    }

    public void test(TimeZone timeZoneRemote) {

        SimpleDateFormat sdfUTC1 = new SimpleDateFormat("yyyy-MM-dd h:mm:ss aa");

        TimeZone tz1 = TimeZone.getDefault();
        TimeZone tz2 = TimeZone.getTimeZone("UTC");

        long timeDifference = (tz1.getRawOffset() - tz2.getRawOffset());

        Calendar calHere = Calendar.getInstance();
        calHere.set(Calendar.HOUR_OF_DAY, 5);
        calHere.set(Calendar.MINUTE, 18);
        calHere.set(Calendar.SECOND, 31);

        System.out.println("Test: " + sdfUTC1.format(calHere.getTime()));

        Calendar calThere = Calendar.getInstance();
        calThere.setTimeInMillis(calHere.getTimeInMillis() - timeDifference);
        System.out.println("Test: " + sdfUTC1.format(calThere.getTime()));


        String utcSunrise = "7:18:31 PM";
        SimpleDateFormat sdfUTC = new SimpleDateFormat("h:mm:ss aa");
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


        try {
            Date utcSunriseD = sdfUTC.parse(utcSunrise);
            System.out.println("date: " + simpleDateFormat.format(utcSunriseD));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("timeDifference = [" + timeDifference + "]");


        String tL1 = "2016-10-19 12:19:56";
        String tR1 = "2016-10-19 01:19:56";

        try {
            Date dL = simpleDateFormat.parse(tL1);
            Date dR = simpleDateFormat.parse(tR1);

            System.out.println("local : " + dL.getTime());
            System.out.println("remote: " + dR.getTime());
            System.out.println("diff  : " + (dL.getTime() - dR.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Calendar calLocal = Calendar.getInstance();

        simpleDateFormat.setTimeZone(calLocal.getTimeZone());

        System.out.println("calLocal1  = [" + simpleDateFormat.format(calLocal.getTime()) + "]");

        Calendar calRemote = Calendar.getInstance(timeZoneRemote);
        simpleDateFormat.setTimeZone(calRemote.getTimeZone());


        System.out.println("calRemote2 = [" + simpleDateFormat.format(calLocal.getTime()) + "]");


        System.out.println("calLocal  = [" + calLocal.getTime().getTime() + "]");
        System.out.println("calRemote = [" + calRemote.getTime().getTime() + "]");
        System.out.println("diff      = [" + (calLocal.getTime().getTime() - calRemote.getTime().getTime()) + "]");

//        Log.d(TAG, "Local : " + calLocal.getTime().getTime());
//        Log.d(TAG, "Remote: " + calRemote.getTime().getTime());
//        Log.d(TAG, "Diff  : " + (calLocal.getTime().getTime() - calRemote.getTime().getTime()));

    }

    public Calendar getConvertedCal(String remoteTime, String format) {

        Calendar cal = Calendar.getInstance();

        try {
            SimpleDateFormat sdfUTC = new SimpleDateFormat(format);
            sdfUTC.setTimeZone(this.timeZone);
            Date localDate = sdfUTC.parse(remoteTime);
            Calendar today = Calendar.getInstance();
            cal.setTime(localDate);
            cal.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return cal;

    }

}
