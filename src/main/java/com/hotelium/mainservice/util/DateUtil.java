package com.hotelium.mainservice.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Mete Aydin
 * @date 24.10.2021
 */


public final class DateUtil {
    public static final String FORMAT_1 = "dd-MM-yyyy hh:mm:ss";
    public static final String FORMAT_2 = "dd-MM-yyyy";
    public static final String FORMAT_UTC = "yyyyMMdd'T'HHmmss";
    public static final String FORMAT_TIMESPAN = "HH:mm:ss.SSS";
    public static final String FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final Long MILLIS_PER_12_HOURS = 43200000L;
    public static final Long MILLIS_PER_2_HOURS = 7200000L;
    public static final Long MILLIS_PER_30_MINUTES = 1800000L;
    public static final Long MILLIS_PER_10_MINUTES = 600000L;
    public static final Long MILLIS_PER_5_MINUTES = 300000L;

    private DateUtil() {
    }

    public static String getMonthName(Locale locale, int month) {
        return (new DateFormatSymbols(locale)).getMonths()[month];
    }

    public static Date convertStringToDate(String stringDate, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(stringDate);

    }

    public static LocalDate convertStringToLocalDate(String stringDate, String pattern) {
        return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(pattern));
    }

    public static Date stringToDate(String stringDate, String pattern) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);


        date = sdf.parse(stringDate);


        return date;
    }

    public static String dateToString(Date date, String pattern) {
        return (new SimpleDateFormat(pattern)).format(date);

    }

    public static String dateToString(LocalDate date, String pattern) {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern(pattern));
        }
        return null;
    }

    public static String dateToString(long dateTime, String pattern) {
        Calendar cal = Calendar.getInstance();
        long time = dateTime - (long) cal.get(15);
        return dateToString(new Date(time), pattern);
    }

    public static Date startOfToday() {
        return startOfDay(new Date());
    }

    public static Date endOfToday() {
        return endOfDay(new Date());
    }

    public static Date startOfDay(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.setTime(date);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    public static Date startDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, 0);
        calendar.set(6, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date endDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, 11);
        calendar.set(5, 31);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date endOfDay(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    public static Date startDayOfWeek(Date date, Locale locale) {
        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(date);
        cal.set(7, cal.getFirstDayOfWeek());
        return startOfDay(cal.getTime());
    }

    public static Date endDayOfWeek(Date date, Locale locale) {
        Calendar cal = Calendar.getInstance(locale);
        cal.setTime(date);

        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DATE, 1);
        }

        return endOfDay(cal.getTime());
    }

    public static Date startOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, cal.getActualMinimum(5));
        return startOfDay(cal.getTime());
    }

    public static Date endOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, cal.getActualMaximum(5));
        return endOfDay(cal.getTime());
    }

    public static boolean isDateAfter(Date date1, Date date2, Long timeDiff) {
        if (timeDiff != null) {
            date2 = new Date(date2.getTime() + timeDiff.longValue());
        }

        return date1.after(date2);
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public enum DateModifyType {
        DAY,
        MONTH,
        YEAR,
        MINUTE;

        DateModifyType() {
        }
    }
}
