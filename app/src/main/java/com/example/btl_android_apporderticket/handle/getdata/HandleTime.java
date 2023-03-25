package com.example.btl_android_apporderticket.handle.getdata;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HandleTime {
    public static String getHourAndMinute(String time) {
        String[] timeSplit = time.split("T");
        String[] timeSplit2 = timeSplit[1].split(":");
        return timeSplit2[0] + ":" + timeSplit2[1];
    }

    public static int CompareDate(String localDateTimeString, Date date, int n) {
        LocalDateTime localDateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            localDateTime = LocalDateTime.parse(localDateTimeString);
        }
        LocalDateTime newLocalDateTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            newLocalDateTime = localDateTime.plusDays(n);
        }
        Date newDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            newDate = Date.from(newLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        }
        return date.compareTo(newDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isSameDay(Date date, String showTime, int plusDays) {
        LocalDate dateTime = LocalDate.parse(showTime, DateTimeFormatter.ISO_DATE_TIME);
        LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(plusDays);
        return dateTime.isEqual(date1);
    }
}
