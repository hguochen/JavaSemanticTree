package libraries.datetime;

import java.util.Date;

/**
 * Date class represents a specific instant in time, with millisecond precision.
 *
 * Limitations
 * - mutable. not thread safe
 * - many deprecated methods(getYear(), getMonth() etc)
 * - doesn't handle timezones well
 * - hard to do things like adding days, formatting etc
 *
 * NOTE: due to many issues listed above, Java 8 introduced java.time package, which is the preferred modern API
 *
 * When to use java.util.Date?
 * - interfacing with legacy libraries or older APIs
 * - converting between Date and LocalDateTime objects
 */
public class DateDemo {
    public static void main(String[] args) {
        // instantiate Date object
        Date date = new Date();

        // display time and date using toString()
        System.out.println(date);

        // display number of milliseconds since midnight, Jan 1, 1970 GMT
        long msec = date.getTime();
        System.out.println("Milliseconds since Jan. 1, 1970 GMT = " + msec);
    }
}
