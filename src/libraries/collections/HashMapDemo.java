package libraries.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HashMap uses a hash table to store the map.
 *
 * Features
 * - stores key-value pairs
 * - allows one null key and multiple null values
 * - unordered
 * - fast O(1) time complexity for basic operations
 * - not thread safe(use ConcurrentHashMap for multi threaded scenarios)
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Integer, String> daysOfWeek = new HashMap<>();
        // add key-value pairs
        daysOfWeek.put(1, "monday");
        daysOfWeek.put(2, "tuesday");
        daysOfWeek.put(3, "wednesday");
        daysOfWeek.put(4, "thursday");
        daysOfWeek.put(5, "friday");

        // accessing elements
        String monday = daysOfWeek.get(1);
        System.out.println(monday);

        // check if key exists
        System.out.println(daysOfWeek.containsKey(3)); // true
        System.out.println(daysOfWeek.containsKey(7)); //false

        // check if value exists
        System.out.println(daysOfWeek.containsValue("wednesday"));
        System.out.println(daysOfWeek.containsValue("sunday")); // false

        // remove elements
        daysOfWeek.remove(3);

        // get a set of the entries
        Set<Map.Entry<Integer, String>> set = daysOfWeek.entrySet();
        // display the set
        for (Map.Entry<Integer, String> me : set) {
            System.out.println(me.getKey());
            System.out.println(me.getValue());
        }
    }
}
