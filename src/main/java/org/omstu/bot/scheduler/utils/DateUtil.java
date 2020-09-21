package org.omstu.bot.scheduler.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }

    public static Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        Long newDate = date.getTime() + (minutes * CronUtil.ONE_MINUTE_IN_MILLIS);
        cal.setTime(new Date(newDate));
        return cal.getTime();
    }

    public static Date fromString(String date, String time) throws ParseException {
        String dateInString = date + '/' + time;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd/hh:mm");
        Date parsedDate = formatter.parse(dateInString);
        return parsedDate;
    }
}
