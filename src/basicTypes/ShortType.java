package basicTypes;

/**
 * Short data type is a signed 16-bit type. It's range from -32,768 to 32767.
 */
public class ShortType {
    public static void main(String[] args) {
        short lowerLimit = Short.MIN_VALUE; // -32768 or -2^15
        System.out.println("short data type lower limit is: " + lowerLimit);
        short upperLimit = Short.MAX_VALUE; // 32768 or 2^16
        System.out.println("short data type upper limit is: " + upperLimit);
    }
}
