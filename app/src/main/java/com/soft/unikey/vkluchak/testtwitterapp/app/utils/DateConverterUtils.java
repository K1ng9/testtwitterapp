package com.soft.unikey.vkluchak.testtwitterapp.app.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Nullable;

/**
 * Created by Vlad Kliuchak on 01.10.17.
 */

public class DateConverterUtils {
    private static final String DATE_TIME_FORMAT_TWITTER_PATTERN="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    private static final String DATE_TIME_FORMAT_UI_PATTERN = "dd/MM/yyyy HH:mm";
    private static final SimpleDateFormat simpleTwitterDateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT_TWITTER_PATTERN, Locale.ENGLISH);
    private static final SimpleDateFormat simpleUIDateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT_UI_PATTERN, Locale.ENGLISH);

    /** Converters for UI **/
    // convert server format UTC to UI format
    public static String convertFromServerDateToUiFormat(String twitterDate) {
        Date myDate = getDateFromStringServerDate(twitterDate);
        return myDate != null ? simpleUIDateTimeFormat.format(myDate) : null;
    }
    // convert app format Millis to UI format
    public static String convertFromMillisToUIDateFormat(long milliSeconds) {
        return getStringFromMillis(milliSeconds, simpleUIDateTimeFormat);
    }

    // convert form Server format UTC to app format millis
    public static long convertFromServerDateToMillis(String utcDate) {
        Date myDate = getDateFromStringServerDate(utcDate);
        return myDate != null ? myDate.getTime() : 0;
    }
    /** Instruments for converting **/
    private static Date getDateFromStringServerDate(String twitterServerDate) {
       // TimeZone utcZone = TimeZone.getTimeZone(UTC_TIME_ZONE);
       // Calendar calendar = Calendar.getInstance(utcZone);
       // simpleTwitterDateTimeFormat.setTimeZone(calendar.getTimeZone());
        return parseTwitterDateStringToDate(twitterServerDate);
    }

    @Nullable
    private static Date parseTwitterDateStringToDate(String twitterServerDate){
        try {
            return !TextUtils.isEmpty(twitterServerDate) ? simpleTwitterDateTimeFormat.parse(twitterServerDate) : null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getStringFromMillis(long milliSeconds, SimpleDateFormat formatter) {
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
