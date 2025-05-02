package libraries.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Period class is widely used to modify values of a given date or to obtain the difference between two dates
 */
public class PeriodDemo {
    public static void main(String[] args) {
        LocalDate initialDate = LocalDate.parse("2007-05-10");
        System.out.println(initialDate);

        // we can manipulate Date using Period
        LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        System.out.println(finalDate);

        // various getter methods such as getYears, getMonths and getDays to get values from a Period object
        int five = Period.between(initialDate, finalDate).getDays();

        // gegt the Period between two dates in a specific unit such ds days or months or years, using ChronoUnit.between
        long fiveDays = ChronoUnit.DAYS.between(initialDate, finalDate);
    }
}
