package util;

import java.time.Instant;
import java.time.ZonedDateTime;

public class Time {

    public static long convertDateTimeToSecond(ZonedDateTime zonedDateTime) {
        Instant instant = Instant.ofEpochSecond(zonedDateTime.toEpochSecond());
        return zonedDateTime.toEpochSecond();
    }

    public static ZonedDateTime getCurrentDateTime() {
        return ZonedDateTime.now();
    }
}
