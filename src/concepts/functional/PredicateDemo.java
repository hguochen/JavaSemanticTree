package concepts.functional;

import java.util.function.Predicate;

/**
 * java.util.function.Predicate is a simple function that takes a single value as param and returns true/false.
 * the Predicate functional interface looks like:
 * public interface Predicate<T> {
 *      boolean test(T t);
 * }
 */
public class PredicateDemo {
    public static void main(String[] args) {
        // we can implement the Predicate interface using a lambda expression
        Predicate isNull = (value) -> value != null;
        System.out.println(isNull.test(new Object())); // true
        System.out.println(isNull.test(null)); // false
    }
}
