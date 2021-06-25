package es.nacho.redeem.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CalendarFormat {

    public static String getStringFromCalendar(Calendar calendar){

        String month = "" + calendar.get(Calendar.MONTH);
        if(month.length() < 2) month = "0" + month;

        return calendar.get(Calendar.YEAR) + "-" + month + "-" + calendar.get(Calendar.DAY_OF_MONTH);

    }

    public static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

}
