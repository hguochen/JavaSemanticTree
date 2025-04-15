package libraries.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap is a subclass of HashMap that maintains the insertion order of entries
 *
 * Feature
 * - maintains insertion order
 * - allows one null key and multiple null values
 * - performance similar to hashmap
 * - not thread safe. use Collections.synchronizedMap() for thread safety
 *
 * When to use LinkedHashMap?
 * - you want to use predictable iteration order
 * - building a cache
 * - need quick lookups + order
 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> days = new LinkedHashMap<>();
        days.put(1, 1);
        days.put(3, 3);
        days.put(5, 5);
        days.put(7, 7);
        days.put(0, 0);

        System.out.println(days.get(3)); // 3
        // insertion order is preserved
        for (Map.Entry<Integer, Integer> day : days.entrySet()) {
            System.out.println(day.getKey() + ": " + day.getValue());
        }
    }
}
