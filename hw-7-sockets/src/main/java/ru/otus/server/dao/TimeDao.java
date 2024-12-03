package ru.otus.server.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeDao {

    public static TimeDTO createTimeObject() {
        TimeDTO timeDTOObject = new TimeDTO();
        String formattedDate = threadSafeFormat(new Date());
        timeDTOObject.setTime(formattedDate);
        return timeDTOObject;
    }

    public static String threadSafeFormat(Date date) {
        DateFormat formatter = PerThreadFormatter.getDateFormatter();
        return formatter.format(date);
    }
}

class PerThreadFormatter {
    private static final ThreadLocal<SimpleDateFormat> dateFormatHolder =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));

    public static DateFormat getDateFormatter() {
        return dateFormatHolder.get();
    }
}