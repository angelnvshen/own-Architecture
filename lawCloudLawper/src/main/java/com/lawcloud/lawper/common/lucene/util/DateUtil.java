package com.lawcloud.lawper.common.lucene.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDate() {
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(d);
		return date;
	}
	
	public static String getDate(long long_date) {
		Date d = new Date(long_date);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(d);
		return date;
	}

	public static String getTwoDay(String sj1, String sj2) {   
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");   
        long day = 0;   
        try {   
         Date date = myFormatter.parse(sj1);
         Date mydate = myFormatter.parse(sj2);
         day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);   
        } catch (Exception e) {   
         return "";   
        }   
        return day + "";   
    }   

}
