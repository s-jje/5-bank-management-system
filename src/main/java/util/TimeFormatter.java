package util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {
    public static String format(ZonedDateTime zonedDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(zonedDateTime.now());
    }
}
