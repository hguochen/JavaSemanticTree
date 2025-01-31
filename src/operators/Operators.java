package operators;

/**
 * Arithmetic operators (+, -, *, /, %)
 * Relational (Comparison) operators (==, !=, >, <, >=, <=)
 * Logical operators (&&, ||, !)
 * Bitwise operators (&, |, ^, ~, <<, >>, >>>)
 * Assignment operators (=, +=, -=, *=, /=, %=, &=, |=, ^=, <<=, >>=, >>>=)
 * Unary operators (+, -, ++, --)
 * Ternary operator (condition ? value1 : value2)
 */
public class Operators {
    public static void main(String[] args) {
        int a = 10, b = 5;

        // Arithmetic Operators
        System.out.println("Arithmetic Operators:");
        System.out.println("a + b = " + (a + b));  // Addition
        System.out.println("a - b = " + (a - b));  // Subtraction
        System.out.println("a * b = " + (a * b));  // Multiplication
        System.out.println("a / b = " + (a / b));  // Division
        System.out.println("a % b = " + (a % b));  // Modulus (Remainder)

        // Relational (Comparison) Operators
        System.out.println("\nRelational Operators:");
        System.out.println("a == b: " + (a == b));  // Equal to
        System.out.println("a != b: " + (a != b));  // Not equal to
        System.out.println("a > b: " + (a > b));    // Greater than
        System.out.println("a < b: " + (a < b));    // Less than
        System.out.println("a >= b: " + (a >= b));  // Greater than or equal to
        System.out.println("a <= b: " + (a <= b));  // Less than or equal to

        // Logical Operators
        boolean x = true, y = false;
        System.out.println("\nLogical Operators:");
        System.out.println("x && y: " + (x && y));  // Logical AND
        System.out.println("x || y: " + (x || y));  // Logical OR
        System.out.println("!x: " + (!x));          // Logical NOT

        // Bitwise Operators
        int p = 5, q = 3; // 5 = 0101, 3 = 0011 (Binary)
        System.out.println("\nBitwise Operators:");
        System.out.println("p & q = " + (p & q));   // Bitwise AND - 0001
        System.out.println("p | q = " + (p | q));   // Bitwise OR - 0111
        System.out.println("p ^ q = " + (p ^ q));   // Bitwise XOR - 0110
        System.out.println("~p = " + (~p));         // Bitwise Complement - 1010
        System.out.println("p << 1 = " + (p << 1)); // Left Shift - 1010
        System.out.println("p >> 1 = " + (p >> 1)); // Right Shift - 0010
//        The unsigned right shift operator (>>>) is a bitwise operator in Java that shifts bits to the right and fills
//        the leftmost (most significant) bits with zero. It works differently from the signed right shift (>>), which
//        preserves the sign bit for negative numbers.
        System.out.println("p >>> 1 = " + (p >>> 1)); // Unsigned Right Shift
        int num = -8; // Binary: 11111111 11111111 11111111 11111000 (32-bit signed)

        // Why Use >>>?
        //>>> is mainly used when treating values as unsigned numbers, especially for bitwise operations on binary data.
        //It prevents negative numbers from staying negative after shifting.
        System.out.println("Signed Right Shift (>>): " + (num >> 2));   // Keeps the sign
        System.out.println("Unsigned Right Shift (>>>): " + (num >>> 2)); // Fills with 0s - 0011 1111 1111 1111 1111 1111 1111 1110  (32-bit)


        // Assignment Operators
        int c = 10;
        System.out.println("\nAssignment Operators:");
        c += 5; // c = c + 5
        System.out.println("c += 5: " + c);
        c -= 3; // c = c - 3
        System.out.println("c -= 3: " + c);
        c *= 2; // c = c * 2
        System.out.println("c *= 2: " + c);
        c /= 4; // c = c / 4
        System.out.println("c /= 4: " + c);
        c %= 3; // c = c % 3
        System.out.println("c %= 3: " + c);

        // Unary Operators
        int d = 5;
        System.out.println("\nUnary Operators:");
        System.out.println("+d = " + (+d));  // Positive
        System.out.println("-d = " + (-d));  // Negative
        System.out.println("++d = " + (++d)); // Pre-increment
        System.out.println("d++ = " + (d++)); // Post-increment
        System.out.println("--d = " + (--d)); // Pre-decrement
        System.out.println("d-- = " + (d--)); // Post-decrement

        // Ternary Operator
        int min = (a < b) ? a : b;
        System.out.println("\nTernary Operator:");
        System.out.println("Smaller value: " + min);
    }
}
