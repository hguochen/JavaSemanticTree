package libraries.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern class can be used in 2 ways
 * - Pattern.matches() method to quickly check if a text matches a given regex
 * - Pattern.compile() to compile a Pattern instance which can be used multiple times to match a regex against multiple textst
 */
public class PatternDemo {
    public static void main(String[] args) {
        String text    =
                "This is the text to be searched " +
                        "for occurrences of the pattern.";
        String pattern = ".*is.*";
        boolean matches = Pattern.matches(pattern, text);
        System.out.println("matches = " + matches);

        String text2    =
                "This is the text to be searched " +
                        "for occurrences of the http:// pattern.";

        String patternString = ".*http://.*";
        // here we have 2nd param to define this comparison as case insensitive. by default, pattern comparisons are case sensitive
        Pattern pattern2 = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);

        // we can use Pattern instance to obtain a matcher instance. Matcher instance is used to find matches of the pattern in texts
        Matcher matcher = pattern2.matcher(text2);
        boolean matches2 = matcher.matches();
        System.out.println("matches = " + matches2);

        // pattern.split()
        String text3 = "A sep Text sep With sep Many sep Separators";
        String patternString3 = "sep";
        Pattern pattern3 = Pattern.compile(patternString3);
        String[] split = pattern3.split(text3);
        System.out.println("split length = " + split.length);
        for (String element : split) {
            System.out.println("element = " + element);
        }

        // Pattern.pattern()
        // returns the pattern string that the Pattern instance was compiled from
        String patternFound = pattern3.pattern();
        System.out.println(patternFound);
    }
}
