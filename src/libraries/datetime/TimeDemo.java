package libraries.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * java.time package is divied into following categories
 *
 * 1. Date & Time classes
 * Class	Description
 * LocalDate	Represents a date without time (e.g., 2025-04-17)
 * LocalTime	Represents time without a date (e.g., 14:30:00)
 * LocalDateTime	Combines date and time (e.g., 2025-04-17T14:30)
 * ZonedDateTime	Date and time with a time zone (e.g., 2025-04-17T14:30+08:00[Asia/Shanghai])
 * OffsetDateTime	Date and time with an offset (e.g., 2025-04-17T14:30+08:00)
 *
 * 2. Time Zone and Offset Classes
 * Class	Description
 * ZoneId	Represents a time zone ID (e.g., Asia/Shanghai, America/New_York)
 * ZoneOffset	Fixed time offset from UTC (e.g., +02:00)
 *
 * 3. Amount of Time
 * Class	Description
 * Duration	Amount of time in seconds or nanoseconds (e.g., between two times)
 * Period	Amount of time in years, months, and days (e.g., between two dates)
 *
 * 4. Formatting and Parsing
 * Class	Description
 * DateTimeFormatter	Formats and parses date-time objects
 */
public class TimeDemo {
    public static void main(String[] args) {
        // Here's a java Date-Time cheat sheet

        // get current Date and Time
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        // create specific Date/Time
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        LocalTime meeting = LocalTime.of(14, 30);
        LocalDateTime appointment = LocalDateTime.of(2025, 4, 17, 10, 15);

        // add/subtract Time
        LocalDate nextWeek = currentDate.plusWeeks(1);
        LocalDate lastMonth = currentDate.minusMonths(1);
        LocalTime lunchTime = currentTime.plusHours(2);

        // Duration & Period(Time/Date difference)
        Period period = Period.between(LocalDate.of(2020, 1, 1), currentDate);
        Duration duration = Duration.between(LocalTime.of(9, 0), currentTime);

        // format & parse
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatted = currentDateTime.format(formatter);
        LocalDate parsedDate = LocalDate.parse("2025-04-17");

        // time zones
        ZoneId zoneNY = ZoneId.of("America/New_York");
        ZonedDateTime nyTime = ZonedDateTime.now(zoneNY);

        // legacy Date conversion
        Date legacyDate = new Date();
        LocalDateTime modern = legacyDate.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDateTime();

        Date backToLegacy = Date.from(modern.atZone(ZoneId.systemDefault()).toInstant());
    }
}
