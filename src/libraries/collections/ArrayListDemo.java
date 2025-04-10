package libraries.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<Integer> days = new ArrayList<>();
        ArrayList<Integer> nums = new ArrayList<>();
        // returns true if obj was added to the collection. false if obj is already a member of the collection
        // and the collection does not allow duplicates
        days.add(1);

        days.addAll(new ArrayList<>(Arrays.asList(2,3,4)));
        System.out.println(days);

        // returns true if obj is an element of the invoking collection
        System.out.println(days.contains(3)); // true

        // returns true if collection is empty, false otherwise
        System.out.println(days.isEmpty()); // false

        // get an iterator
        Iterator<Integer> iter = days.iterator();
        while(iter.hasNext()) {
            int day = iter.next();
            System.out.println(day);
        }

        // removes 1 instance of obj from the invoking collection
        // returns true if element is removed, false otherwise
        System.out.println(days.remove(new Integer(4))); // true
        System.out.println(days.remove(new Integer(4))); // false

        // removes all elements from the invoking collection.
        // return true if collection is changed, false otherwise
        System.out.println(days.removeAll(Arrays.asList(2,3)));
        System.out.println(days);

        days.addAll(new ArrayList<>(Arrays.asList(2,3,4,5)));

        days.retainAll(new ArrayList<>(Arrays.asList(2,3,4)));
        System.out.println(days);
    }
}
