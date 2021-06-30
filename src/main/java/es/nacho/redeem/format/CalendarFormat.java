package es.nacho.redeem.format;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CalendarFormat {

    public static String getStringFromCalendar(Calendar calendar){

        String month = "" + (calendar.get(Calendar.MONTH)+1);
        if(month.length() < 2) month = "0" + month;
        String day = "" +calendar.get(Calendar.DAY_OF_MONTH);
        if(day.length() < 2) day = "0" + day;


        return calendar.get(Calendar.YEAR) + "-" + month + "-" + day;

    }

    public static String formatLocalDateTime(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    public static String formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
