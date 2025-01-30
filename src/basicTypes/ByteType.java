package basicTypes;

/**
 * Byte is the smallest integer type. This is a signed 8-bit type that ranges from -128 to 127
 *
 */
public class ByteType {
    public static void main(String[] args) {
        // declare and initialize a byte variable
        byte smallNumber = 100;

        // byte range
        byte minByte = -128;
        byte maxByte = 127;

        // addition operations on byte
        byte a = 50;
        byte b = 30;
        // cast to byte is required as addition promotes to int type
        byte sum = (byte) (a + b);

        System.out.println("small number:" + smallNumber);
        System.out.println("min byte: " + minByte);
        System.out.println("max byte: " + maxByte);
        System.out.println("sum of a + b: " + sum);
    }
}
