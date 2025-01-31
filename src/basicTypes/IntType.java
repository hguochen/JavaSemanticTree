package basicTypes;

/**
 * One of the eight basic data type in Java. 'int' type are signed, positive and
 * negative values. Java does not support unsigned, positive only integers.
 */
class IntType {
    public static void main(String[] args) {
        int lowerLimit = Integer.MIN_VALUE; // -2147483648 or -2^31
        System.out.println("lower int limit is: " + lowerLimit);
        int upperLimit = Integer.MAX_VALUE; // 2147483647 or 2^32
        System.out.println("upper int limit is: " + upperLimit);

        // What is an Integer literal
        // integer literal is a fixed numerical value assigned to one of int, long, byte or short variables
        // it represents whole numbers and can be expressed in different number systems

        // decimal, base 10
        // default number format
        // uses digits 0-9
        int decimalNumber = 100;

        // binary, base 2
        // prefixed with 0b or 0B
        // uses digits 0 and/or 1
        int binaryNumber = 0b1101; // 13 in decimal

        // octal, base 8
        // prefixed with 0
        // uses digits 0-7
        int octalNumber = 012; // 10 in decimal

        // hexadecimal, base 16
        // prefixed with 0x or 0X
        // uses digits 0-9 and letters A-F(or a-f)
        int hexNumber = 0x1A; // 26 in decimal

        // Integer literal suffixes
        // you can specify long using L or l suffix
        long bigNumber = 10000L; // suffix 'L' makes it long

        // we can add underscores in numeral literals to improve readability
        int readableNumber = 1_000_000;

        System.out.println(decimalNumber);
        System.out.println(binaryNumber);
        System.out.println(octalNumber);
        System.out.println(hexNumber);
        System.out.println(bigNumber);
        System.out.println(readableNumber);
    }
}