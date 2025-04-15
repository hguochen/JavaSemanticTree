package libraries.collections;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * TreeMap is a sorted map implementation that stores key-value pairs in ascending order by default
 *
 * Features
 * - stores key-value pairs
 * - auto sorted by keys(natural order or custom comparator)
 * - Implements red-black tree(balanced BST with O(logn) operations on average)
 * - does not allow null keys
 * - maintains order, unlike HashMap
 */
public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> daysOfWeek = new TreeMap<>();
        // add key-value pairs
        daysOfWeek.put(1, "monday");
        daysOfWeek.put(2, "tuesday");
        daysOfWeek.put(3, "wednesday");
        daysOfWeek.put(4, "thursday");
        daysOfWeek.put(5, "friday");

        // get a set of the entries
        Set<Map.Entry<Integer, String>> set = daysOfWeek.entrySet();
        // display the set in sorted key order
        for (Map.Entry<Integer, String> me : set) {
            System.out.println(me.getKey());
            System.out.println(me.getValue());
        }
    }
}
