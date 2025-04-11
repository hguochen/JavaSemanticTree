package libraries.collections;

import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> week = new TreeSet<>();
        week.add("monday");
        week.add("tuesday");
        week.add("wednesday");
        week.add("thursday");
        // treeset guarantees the chronological sorted order
        System.out.println(week);

        TreeSet<Integer> days = new TreeSet<>();
        days.add(1);
        days.add(2);
        days.add(5);
        days.add(3);
        days.add(4);
        System.out.println(days); // 1,2,3,4,5
        System.out.println(days.subSet(2, 4)); // 2,3
    }
}
