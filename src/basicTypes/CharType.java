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

        // what is character literal?
        // character literal in java is a single unicode character enclosed in single quotes

        // escape sequences
        char newLine = '\n';
        char tab = '\t';
        char singleQuote = '\'';
        char doubleQuote = '\"';
        char backSlash = '\\';
        System.out.println(newLine);
        System.out.println(tab);
        System.out.println(singleQuote);
        System.out.println(doubleQuote);
        System.out.println(backSlash);

        // unicode character literals
        char greekOmega = '\u03A9'; // Ω
        char heart = '\u2665'; // ♥
        System.out.println(greekOmega);
        System.out.println(heart);

        // String literals
        // string literals are specified by enclosing a sequence of characters between a pair of double quotes
        String str1 = "hello world!";
        String str2 = "two\nlines";
        String str3 = "\"This in quotes\"";
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }
}
