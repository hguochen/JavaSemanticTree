package libraries.collections;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A reverse comparator for String
 */
class ReverseComparator implements Comparator<String> {
    public int compare(String aStr, String bStr) {
        return bStr.compareTo(aStr);
    }
}
public class ComparatorDemo {
    public static void main(String[] args) {
        TreeSet<String> alphabets = new TreeSet<>(new ReverseComparator());
        alphabets.add("C");
        alphabets.add("A");
        alphabets.add("B");
        alphabets.add("E");
        alphabets.add("F");
        alphabets.add("D");
        for (String element: alphabets) {
            System.out.println(element);
        }
    }
}
