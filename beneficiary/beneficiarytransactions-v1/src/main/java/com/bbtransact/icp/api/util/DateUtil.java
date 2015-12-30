package com.bbtransact.icp.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Component;

import com.bbtransact.icp.api.exception.custom.DatePatternMismatchException;

@Component
public class DateUtil {

    public static String getUTCDate() {

        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        Calendar calendar = Calendar.getInstance(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getUTCDateFormat(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        // Calendar calendar = Calendar.getInstance(timeZone);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }

    public static java.sql.Timestamp getSqlStartDate(String date) {
        if (date.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            String startDate = date + " 00:00:00.000";
            return java.sql.Timestamp.valueOf(startDate);
        } else {
            throw new DatePatternMismatchException();

        }
    }

    public static java.sql.Timestamp getSqlEndDate(String date) {

        if (date.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
            String endDate = date + " 24:00:00.000";
            return java.sql.Timestamp.valueOf(endDate);
        } else {
            throw new DatePatternMismatchException();

        }

    }
}