package basicTypes;

/**
 * Float is a single precision value that uses 32 bits of storage.
 * Single precision is faster on some processors and takes half as much as space as double precision.
 * But float values tend to become imprceise when values are either very large or very small
 *
 * Use float when you don't require a large degree of precision. eg 8 degrees and below
 */
public class FloatType {
    public static void main(String[] args) {
        float minValue = Float.MIN_VALUE; // 1.4E-45
        float maxValue = Float.MAX_VALUE; // 3.4028235E38

        System.out.println("float min value: " + minValue);
        System.out.println("float max value: " + maxValue);

        // what is a floating point literal?
        // a floating point literal is a numeric value that represents a decimal or fractional number
        // used to assign values to float or double variables. by default, floating point literals are of type 'double'

        // 1. decimal form
        // must include decimal point .
        // can include an exponent e or E for scientific notation
        double decimalNumber = 10.5;
        double largeDecimal = 3.1415926535;
        double scientificNotation = 1.2e3;
        double smallScientific = 5.67E-2;
        System.out.println(decimalNumber);
        System.out.println(largeDecimal);
        System.out.println(scientificNotation);
        System.out.println(smallScientific);

        // 2. hexadecimal form
        // prefixed with 0x or 0X
        // uses p or P to indicate exponent
        double hexFloat = 0x1.0p3; // 1 * 2^3 = 8.0
        double hexSmall = 0xA.1P2; // (10.0625 * 2^2) = 40.25
        System.out.println(hexFloat);
        System.out.println(hexSmall);

        // floating point literal suffixes
        // by default, floating point literals are of type double, we can specifiy float with f or F suffix
        float floatValue = 3.14F; // F suffix makes it float
        double doubleValue = 3.14; // default is double
        System.out.println(floatValue);
        System.out.println(doubleValue);

        // d or D suffix is optional for double
        double doubleValue2 = 2.718D;
        System.out.println(doubleValue2);

        // we can add underscores for readability
        double bigNumber = 3_456.789_012;
        System.out.println(bigNumber);
    }
}
