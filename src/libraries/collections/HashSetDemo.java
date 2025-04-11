package libraries.collections;

import java.util.HashSet;

public class HashSetDemo {
    public static void main(String[] args) {
        HashSet<String> week = new HashSet<>();
        week.add("monday");
        week.add("tuesday");
        System.out.println(week.add("wednesday")); // true
        System.out.println(week.add("wednesday")); // false
        // hashset does not guarantee the order of elements
        System.out.println(week);
    }
}
