package libraries;

/**
 * https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
 *
 * StringBuilder
 *  - used to construct mutable strings
 *  - Similar to StringBuffer class but has no guarantee of synchronization. ie. designed to be used in a single thread environment
 *  - As long as you do not need synchronization across threads, you should use StringBuidler class
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abcd");

        // appends the string to the end of current string in StringBuilder class
        sb.append("efg");

        // returns the current capacity
        System.out.println(sb.capacity());

        // returns the char value in this sequence at the specified index
        System.out.println(sb.charAt(2)); // c

        // returns the character(unicode code point) at specified index
        System.out.println(sb.codePointAt(2)); // 99

        // returns the character(unicode code point) before the specified index
        System.out.println(sb.codePointBefore(2)); // 98

        // remove the characters in a substring of this sequence
        sb.delete(4, sb.length());
        System.out.println(sb); // abcd

        // remove the char at the specified position in this sequence
        sb.deleteCharAt(0);
        System.out.println(sb); // bcd

        // ensures that the capacity is at least equal to the specified minimum
        sb.ensureCapacity(50);
        System.out.println(sb.capacity()); // 50

        // characters are copied from this sequence into the destination char array dst
        char[] dst = new char[10];
        sb.getChars(1, sb.length(), dst, 0);
        System.out.println(sb);
        System.out.println(dst);

        // returns the index within this string of the first occurrence of the specified string
        System.out.println(sb.indexOf("c")); // 1

        // inserts the string representation of string argument into the sequence
        // the second argument supports most object and primitive types
        sb.insert(1, "gary");
        System.out.println(sb);

        // returns the index within this string of the rightmost occurrence of the specified substring
        System.out.println(sb.lastIndexOf("gary")); // 1

        // returns the length of the chars
        System.out.println(sb.length()); // 7

        // replaces the chars in a substring off this sequence with chars in the specified string
        sb.replace(1, 5, "roger");
        System.out.println(sb);

        // reverse the char sequence
        sb.reverse();
        System.out.println(sb);

        // set the char at the specified index to z
        char z = 'z';
        sb.setCharAt(1, z);
        System.out.println(sb);

        // sets the length of the char sequence
        // each new char that hasn't been initialized is null
        sb.setLength(100);
        System.out.println(sb);
        System.out.println(sb.length());

        // returns a new char sequence that is a subsequence of this sequence
        System.out.println(sb.subSequence(1, 7));

        // returns a new string that contains a subsequence of chars currently contained in this char sequence
        System.out.println(sb.substring(2));

        // returns a string representation of the data in sequence
        System.out.println(sb.toString());

        // attempts to reduce storage used for the char sequence
        sb.trimToSize();
        System.out.println(sb);
        System.out.println(sb.length());
    }
}
