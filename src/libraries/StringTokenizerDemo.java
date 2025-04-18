package libraries;

import java.util.StringTokenizer;

public class StringTokenizerDemo {
    public static void main(String[] args) {
        String text = "the cow jumps over the lazy frog";
        StringTokenizer st = new StringTokenizer(text, " ");
        while(st.hasMoreTokens()) {
            String word = st.nextToken();
            System.out.println(st.countTokens());
            System.out.println(word);
        }
    }
}
