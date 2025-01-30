package basicTypes;

/**
 * One of the two kinds of floating point types.
 * Double uses 64 bits to store value.
 *
 * all math functions in java.lang.Math returns double values.
 */
public class DoubleType {
    public static void main(String[] args) {
        double minValue = Double.MIN_VALUE; // 4.9E-324
        double maxValue = Double.MAX_VALUE; // 1.7976931348623157E308

        System.out.println("double min value: " + minValue);
        System.out.println("double max value: " + maxValue);
    }
}
