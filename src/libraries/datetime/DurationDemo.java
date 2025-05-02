package libraries.datetime;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Similar to Period, the Duration class is used to deal with Time
 */
public class DurationDemo {
    public static void main(String[] args) {
        LocalTime initialTime = LocalTime.of(6,30, 0);
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));

        // We can get the Duration between two instants as either a Duration or a specific unit.
        //
        //First, we use the between() method of the Duration class to find the time difference between finalTime and initialTime and return the difference in seconds:
        long thirty = Duration.between(initialTime, finalTime).getSeconds();

        // we use the between() method of the ChronoUnit class to perform the same operation:
        long thirtyNew = ChronoUnit.SECONDS.between(initialTime, finalTime);
    }
}
