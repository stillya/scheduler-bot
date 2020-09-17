package org.omstu.bot.scheduler.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.scheduling.support.CronTrigger;

public class CronUtil {

    public static CronTrigger toCronTrigger(Date date) {
        Calendar startDate = Calendar.getInstance();
        //startDate.setTime(date);
        startDate.setTime(new Date());

        String daysOfWeek = "?";
        String minute = String.valueOf(startDate.get(Calendar.MINUTE) + 1);
        String hour = String.valueOf(startDate.get(Calendar.HOUR_OF_DAY));
        String daysOfMonth = String.valueOf(startDate.get(Calendar.DAY_OF_MONTH));
        String month = String.valueOf(startDate.get(Calendar.MONTH) + 1);

        return new CronTrigger("0 " + minute + " " + hour + " " + daysOfMonth + " " + month + " " + daysOfWeek);
    }

}
