package libraries.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        // Pattern Example
        // a regex example that uses a regex to check if a text contains the substring http://
        String text = "This is the text to be searched " +
                "for occurrences of the http:// pattern.";
        String regex = ".*http://.*";
        // if the regex matches the text, it returns true. false otherwise
        boolean matches = Pattern.matches(regex, text);
        System.out.println("matches = " + matches);

        // Matcher Example
        String text2 = "This is the text which is to be searched " +
                "for occurrences of the word 'is.";
        String regex2 = "is";
        Pattern pattern = Pattern.compile(regex2);
        Matcher matcher = pattern.matcher(text2);

        int count = 0;
        while (matcher.find()) {
            count++;
            System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
        }

        // Java string also has a few regex methods
        // matches()
        String text3 = "one two three two one";
        boolean matches3 = text3.matches(".*two.*");
        System.out.println(matches3);

        // split()
        // splits the string into N substrings and returns a string array with these substrings
        String[] twos = text3.split("two");
        for (String str : twos) {
            System.out.println(str);
        }

        // replaceFirst()
        // returns a new string with the first match of the regex pass as first param with the string value as second param
        String s = text3.replaceFirst("two", "five");
        System.out.println(s);

        // replaceAll()
        // returns a new string with all the matches of the regex passed as first param with second param
        String t = text3.replaceAll("two", "five");
        System.out.println(t);
    }
}
