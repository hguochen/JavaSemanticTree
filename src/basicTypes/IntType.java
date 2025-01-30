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
    }
}