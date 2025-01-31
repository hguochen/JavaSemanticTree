package concepts;

/**
 * Java uses encoding known as two's complement, which means that negative numbers are represented by inverting all of
 * the bits in a value, then adding 1 to the result.
 */
public class TwosComplement {
    public static void main(String[] args) {
        int fortyTwo = 42;
        int negativeFortyTwo = -42;

        System.out.println("42: " + Integer.toBinaryString(fortyTwo));
        System.out.println("-42: " + Integer.toBinaryString(negativeFortyTwo));
    }
}
