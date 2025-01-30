package basicTypes;

/**
 * Char is the default type used to store characters in java. Java uses unicode to represent characters.
 * Unicode is the unification of dozens of character sets, such as Latin, Greek, Arabic, Cyrillic, Hebrew and many more.
 *
 * - Char is a 16 bits type.
 * - Range of char is from 0 to 65536.
 * - There are no negative chars
 * - the most common set of chars known as ASCII still ranges from 0 to 127
 * - the extended 8-bit character set, ISO-Latin-1, ranges from 0 to 255
 * - the reason char supports unicode(which has much wider range) is because of global portability that Java language requires
 *
 */
public class CharType {
    public static void main(String[] args) {
        char letter = 'A';   // Assigning a single character
        char digit = '5';    // Assigning a numeric character
        char symbol = '@';   // Assigning a special character
        char space = ' ';    // Assigning a space character
        char X = 88; // code for X

        System.out.println(letter);
        System.out.println(digit);
        System.out.println(symbol);
        System.out.println(space);
        System.out.println(X);

        // you can perform arithmetic operations on chars
        char Y = 89;
        System.out.println("char Y: " + Y);
        Y++;
        System.out.println("char Y is now: " + Y);
    }
}
