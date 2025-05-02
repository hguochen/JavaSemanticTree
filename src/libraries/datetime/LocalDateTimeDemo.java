package libraries.datetime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * LocalDateTime is used to represent a combination of date and time. This is the commonly used date and time representation.
 */
public class LocalDateTimeDemo {
    public static void main(String[] args) {
        // get a LocalDateTime instance at system clock
        LocalDateTime now = LocalDateTime.now();

        // create an instance of a specific date time
        LocalDateTime dayX = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);

        // plus day 1
        LocalDateTime dayXMinusOne = dayX.minusDays(1);
        // minus 2 hours
        LocalDateTime dayXMinus2Hour = dayX.minusHours(2);

        // get specific units to the date time class
        Month month = dayX.getMonth();

        // Java 8 has added the toInstant() method, which helps to convert existing Date and Calendar instance to new Date and Time API:
        // Date and Calendar are old java classes used to represent date and time
//        LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
//        LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());

        // passes an ISO date format to format the local date
        String localDateString = dayX.format(DateTimeFormatter.ISO_DATE);
        System.out.println(localDateString);

        // DateTimeFormatter has various formatting options
        System.out.println(dayX.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        // We can pass in formatting style either as SHORT, LONG or MEDIUM as part of the formatting option.
        System.out.println(dayX.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.UK)));
    }
}
