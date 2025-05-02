package libraries.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * LocalDate represents a date in ISO format(yyyy-MM-dd) without time.
 */
public class LocalDateDemo {
    public static void main(String[] args) {
        // create an instance of current date from system clock
        LocalDate localDate = LocalDate.now();

        // we can get LocalDate representing a specific day, month and year
        LocalDate feb202015 = LocalDate.of(2015, 02, 20);
        LocalDate.parse("2015-02-20");

        // adds 1 day
        LocalDate tomorrow = LocalDate.now().plusDays(1);

        // gets current date and subtracts one month
        LocalDate previousMonthSameDay = LocalDate.now().minusMonths(1);
        System.out.println(previousMonthSameDay);

        // get day of the week from a localdate
        DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
        //  get day of month from a localdate
        int twelve = LocalDate.parse("2016-05-12").getDayOfMonth();
        System.out.println(twelve);

        // test if a date occurs in a leap year
        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println(leapYear);

        // determine the relationship of a date to another date
        boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.parse("2016-06-11"));
        boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.parse("2016-06-11"));

        // get the LocalDateTime tha represents the beginning of the day
        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
    }
}
