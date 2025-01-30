package basicTypes;

/**
 * Long is a signed 64-bit type.
 */
public class LongType {
    public static void main(String[] args) {
        long minValue = Long.MIN_VALUE; // -9223372036854775808 or -2^64
        long maxValue = Long.MAX_VALUE; // 9223372036854775807 or 2^64

        System.out.println("long min value is: " + minValue);
        System.out.println("long max value is: "+ maxValue);
    }
}
