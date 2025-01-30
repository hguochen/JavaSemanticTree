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
    }
}
