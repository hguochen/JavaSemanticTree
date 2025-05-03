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

        /**
         * Predicate interface contains a few methods that help you compose new Predicate instances from other Predicate
         * instances.
         */
        // and()
        Predicate<String> notEmpty = (text) -> text != null && text != "";
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX = (text) -> text.endsWith("x");
        Predicate<String> composed = notEmpty.and(startsWithA).and(endsWithX);
        String input = "A hardworking person must relax";
        boolean result = composed.test(input);
        System.out.println(result);

        // or()
        Predicate<String> composed2 = startsWithA.or(endsWithX);
        String input2 = "A hardworking person must relax sometimes";
        boolean result2 = composed.test(input2);
        System.out.println(result2);
    }
}
