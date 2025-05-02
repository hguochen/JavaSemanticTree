package libraries.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Matcher class is used to search a text for multiple occurrences of a regex
 */
public class MatcherDemo {
    public static void main(String[] args) {
        String text    =
                "This is the text to be searched " +
                        "for occurrences of the http:// pattern.";
        String patternString = ".*http://.*";
        Pattern pattern = Pattern.compile(patternString);
        // creating a matcher
        // the matcher variable contains a Matcher instance which can be used to match the regex
        Matcher matcher = pattern.matcher(text);
        // matches the regex against the whole text passed to the Pattern.matcher() method, when the Matcher was created
        // if the regex matches the whole text, then matches() returns true, false otherwise.
        boolean matches = matcher.matches();
        System.out.println(matches);

        // lookingAt()
        // lookingAt() works like the matches() method but only matches the regex against the beginning of the text.
        // in other words, if the regex matches the beginning of a text but not the whole text, lookingAt() will return true, matches() will return return false
        String text2    =
                "This is the text to be searched " +
                        "for occurrences of the http:// pattern.";

        String patternString2 = "This is the";

        Pattern pattern2 = Pattern.compile(patternString2, Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(text2);

        System.out.println("lookingAt = " + matcher2.lookingAt());
        System.out.println("matches   = " + matcher2.matches());

        // find() + start() + end()
        // find() searches for occurrences of the regex in the text. if multiple matches can be found, repeated calls to find() will move to the next match
        // start() + end() will give the indexes into the text where the found match starts and ends
        // note: end() returns the index of the char just after the end of the matching section.
        String text3 = "This is the text which is to be searched " +
                "for occurrences of the word 'is'.";
        String patternString3 = "is";
        Pattern pattern3 = Pattern.compile(patternString3);
        Matcher matcher3 = pattern3.matcher(text3);
        int count = 0;
        while (matcher3.find()) {
            count++;
            System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
        }

        // reset()
        // resets the matching state internally in the matcher, in case we have started matching occurrences in a string via find() method.
        // by calling reset() the matching will start from the beginning of the text again
        matcher3.reset();
        // we can also do matcher.reset(newText) to use the same matcher to find new text instead of the original text used.

        // group()
        // Imagine you are searching through a text for URL's, and you would like to extract the found URL's out of the text.
        // Of course you could do this with the start() and end() methods, but it is easier to do so with the group functions.
        // access a group using the group(int groupNo) method. A regular expression can have more than one group.
        // Each group is thus marked with a separate set of parentheses. To get access to the text that matched the subpart of
        // the expression in a specific group, pass the number of the group to the group(int groupNo) method.
        // The group with number 0 is always the whole regular expression. To get access to a group marked by parentheses you should start with group numbers 1.
        String text4    =
                "John writes about this, and John writes about that," +
                        " and John writes about everything. ";
        String patternString4 = "(John)";
        Pattern pattern4 = Pattern.compile(patternString4);
        Matcher matcher4 = pattern4.matcher(text4);
        while (matcher4.find()) {
            System.out.println("found: " + matcher4.group(1));
        }

        // a regex can have multiple groups
        String text5    =
                "John writes about this, and John Doe writes about that," +
                        " and John Wayne writes about everything.";
        // (John) -> matches 'John' with a space behind
        // (.+?) -> matches any char one or more times. ? means "match as small a number of chars as possible"
        String patternString5 = "(John) (.+?) ";
        Pattern pattern5 = Pattern.compile(patternString5);
        Matcher matcher5 = pattern5.matcher(text5);

        while (matcher5.find()) {
            System.out.println("found: " + matcher5.group(1) + " " + matcher5.group(2));
        }

        // groups inside groups
        // when groups are nested inside each other, they are numbered based on when the left parenthesis of the group is met.
        // group 1 is the big group, group 2 is the group with the expression 'John' inside. group 3 is the group with the
        // expression .+? inside.
        String text6    =
                "John writes about this, and John Doe writes about that," +
                        " and John Wayne writes about everything.";
        String patternString6 = "((John) (.+?))";
        Pattern pattern6 = Pattern.compile(patternString6);
        Matcher matcher6 = pattern6.matcher(text6);
        while (matcher6.find()) {
            System.out.println("found: <"  + matcher6.group(1) +
                    "> <"       + matcher6.group(2) +
                    "> <"       + matcher6.group(3) + ">");
        }

        // replaceAll() & replaceFirst()
        // The Matcher replaceAll() and replaceFirst() methods can be used to replace parts of the string the Matcher is searching through.
        // The replaceAll() method replaces all matches of the regular expression. The replaceFirst() only replaces the first match.
        String text7    =
                "John writes about this, and John Doe writes about that," +
                        " and John Wayne writes about everything."
                ;

        String patternString7 = "((John) (.+?)) ";

        Pattern pattern7 = Pattern.compile(patternString7);
        Matcher matcher7 = pattern7.matcher(text7);

        String replaceAll = matcher7.replaceAll("Joe Blocks ");
        System.out.println("replaceAll   = " + replaceAll);

        String replaceFirst = matcher7.replaceFirst("Joe Blocks ");
        System.out.println("replaceFirst = " + replaceFirst);

        // appendReplacement() + appendTail()
        // used to replace string tokens in an input text, and append the result string to a StringBuffer
        // When you have found a match using the find() method, you can call the appendReplacement().
        // Doing so results in the characters from the input text being appended to the StringBuffer, and the matched
        // text being replaced. Only the characters starting from then end of the last match, and until just before the matched characters are copied.
        //
        //The appendReplacement() method keeps track of what has been copied into the StringBuffer, so you can continue
        // searching for matches using find() until no more matches are found in the input text.
        String text8 = "John writes about this, and John Doe writes about that," +
                " and John Wayne writes about everything.";
        String patternString8 = "((John) (.+?)) ";
        Pattern pattern8 = Pattern.compile(patternString8);
        Matcher matcher8 = pattern8.matcher(text8);
        StringBuffer stringBuffer = new StringBuffer();

        while (matcher8.find()) {
            matcher8.appendReplacement(stringBuffer, "Joe Blocks ");
            System.out.println(stringBuffer.toString());
        }
        matcher8.appendTail(stringBuffer);
        System.out.println(stringBuffer.toString());
    }
}
