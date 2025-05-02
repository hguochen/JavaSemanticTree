package libraries.datetime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * ZonedDateTime is used when we need to deal with time-zone specific date and time.
 * - ZoneId is an identifier to represent different zones
 * - there are about 40 different timezones
 */
public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        // create Zone for Paris
        ZoneId zoneId = ZoneId.of("Europe/Paris");

        // get all zone ids
        Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(allZoneIds);

        // LocalDateTime can be converted to a specific zone
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);
        System.out.println(zonedDateTime);

        // Another way to work with time zone is by using OffsetDateTime. The OffsetDateTime is an immutable representation of a date-time with an offset. This class stores all date and time fields, to a precision of nanoseconds, as well as the offset from UTC/Greenwich.
        //
        //The OffSetDateTime instance can be created using ZoneOffset. Here, we create a LocalDateTime representing 6:30 a.m. on February 20, 2015:
        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);

        // Then we add two hours to the time by creating a ZoneOffset and setting for the localDateTime instance:
        ZoneOffset offset = ZoneOffset.of("+02:00");

        // We now have a localDateTime of 2015-02-20 06:30 +02:00.
        OffsetDateTime offSetByTwo = OffsetDateTime
                .of(localDateTime, offset);
    }
}
