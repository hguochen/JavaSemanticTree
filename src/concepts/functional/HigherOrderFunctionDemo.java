package concepts.functional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A higher order function is defined as a function that either:
 * - takes a function as a param OR
 * - returns a function after its execution
 *
 */
public class HigherOrderFunctionDemo {
    public static void main(String[] args) {
        // example of HO function
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("abc");
        list.add("def");

        // Collection.sort takes two params. 1st param a List and 2nd param a lambda function.
        // the lambda function is what makes Collection.sort() a HO function.
        Collections.sort(list, (String a, String b) -> a.compareTo((b)));
        System.out.println(list);

        // first we have a lambda expression that creates a Comparator interface
        // then we call reversed() method on the comparator lambda, which returns a new comparator lambda
        // because reversed() method returns a lambda function, the reversed() method is considered HO function
        Comparator<String> comparator = (String a, String b) -> {
            return a.compareTo(b);
        };
        Comparator<String> comparatorReversed = comparator.reversed();
        Collections.sort(list, comparatorReversed);
        System.out.println(list);
    }
}
