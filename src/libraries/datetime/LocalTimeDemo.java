package libraries.datetime;

import java.time.LocalTime;

/**
 * LocalTime represents time without a date
 */
public class LocalTimeDemo {
    public static void main(String[] args) {
        // create an instance of LocalTime from system clock
        LocalTime now = LocalTime.now();

        // create a localtime representing a fixed time
        LocalTime sixThirty = LocalTime.parse("06:30");

        // create a localtime
        LocalTime sixThirtyTwo = LocalTime.of(6, 32);

        // get specific units like hour, min and secs
        int six = LocalTime.parse("06:30").getHour();

        // check if a specific time is before/after a specific time
        boolean isBefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        System.out.println(isBefore);

        // max, min and noon time of a day
        LocalTime maxTime = LocalTime.MAX;
        LocalTime minTime = LocalTime.MIN;
        System.out.println(maxTime);
        System.out.println(minTime);
    }
}
