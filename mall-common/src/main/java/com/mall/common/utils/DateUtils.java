package com.mall.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateFormat(Date date, String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        if (date != null){
            return simpleDateFormat.format(date);
        }

        return "";
    }

    public static String dateFormat(Date date){
        return dateFormat(date, "yyyy-MM-dd hh:mm:ss");
    }

    public static Date getNowTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        String dateString = dateFormat(date);

        try {
            date = format.parse(dateString);
        }catch (ParseException exception){

        }

        return date;
    }

    public static Timestamp getNowTimestamp(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static int getUnixTimeByDate(Date date){
        return (int) (date.getTime() / 1000L);
    }
}
