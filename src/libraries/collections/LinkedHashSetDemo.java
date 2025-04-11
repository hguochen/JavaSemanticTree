package libraries.collections;

import java.util.LinkedHashSet;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        LinkedHashSet<String> week = new LinkedHashSet<>();
        week.add("monday");
        week.add("tuesday");
        week.add("wednesday");
        week.add("thursday");
        // LinkedHashSet guarantees the elements in insertion order
        System.out.println(week);
    }
}
