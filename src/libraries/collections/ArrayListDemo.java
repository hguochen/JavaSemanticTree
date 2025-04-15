package libraries.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Spliterator;

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

        // use ListIterator if you need to modify the elements in the collection
        ListIterator<Integer> liter = days.listIterator();
        while(liter.hasNext()) {
            liter.set(liter.next() + 1);
        }

        System.out.println(days);

        // alternatively, if you don't intend to modify the contents of the collection, you can use a for each loop
        for(int day : days) {
            System.out.println("day is: " + day);
        }

        Spliterator<Integer> siter = days.spliterator();
        // estimates the number of elements left to iterate and returns the result.
        System.out.println(siter.estimateSize());

        // executes action on the next element in the iteration. return true if there's next element, false if no elements remain
        // note: you are not allowed to modify the elements using a spliterator
        while (siter.tryAdvance((n) -> System.out.println("in tryAdvance: " + n)));

        siter.forEachRemaining((n) -> System.out.println("forEachRemaining:" + n));

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

        // copy elements from one list into another list. overwrites elements in the destination list
        // with elements from the source list at corresponding positions
        // destination list must be at least the same size as the source list
        // copies elements by index, replacing elements in dest.
        ArrayList<Integer> cp = new ArrayList<>();
        cp.add(10);
        cp.add(20);
        cp.add(30);
        Collections.copy(cp, days);
        System.out.println(cp);

        ArrayList<Integer> trades = new ArrayList<>();
        trades.add(39);
        trades.add(38);
        trades.add(82);

        // compares elements in cp to elements in trades. returns true if the two collections contain no
        // common elements. otherwise, returns false
        System.out.println(Collections.disjoint(cp, trades));

        // Arrays.parallelPrefix is a method used to perform a cumulative operation(like sum, product, max, etc) in parallel on an array.
        Arrays.parallelPrefix(cp.toArray(), (a, b) -> (int)a + (int)b);
    }
}
