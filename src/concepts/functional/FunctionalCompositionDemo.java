package concepts.functional;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Functional Composition is a technique to join multiple functions into a single function which uses these multiple functions
 * internally.
 */
public class FunctionalCompositionDemo {
    public static void main(String[] args) {
        Predicate<String> startsWithA = (text) -> text.startsWith("A");
        Predicate<String> endsWithX = (text) -> text.endsWith("x");

        // this  is a functional composition
        Predicate<String> startsWithAAndEndsWithX = (text) -> startsWithA.test(text) && endsWithX.test(text);

        String input = "A hardworking person must relax";
        boolean result = startsWithAAndEndsWithX.test(input);
        System.out.println(result);

        /**
         * java.util.function.Function also contains a few methods that can be used to compose new Function instances
         * from existing ones.
         */
        // compose()
        // compose() method composes a new Function instance from the Function instance it is called on
        Function<Integer, Integer> multiply = (value) -> value * 2;
        Function<Integer, Integer> add = (value) -> value + 3;

        // this will first call add(), then call multiply()
        Function<Integer, Integer> addThenMultiply = multiply.compose(add);
        Integer result1 = addThenMultiply.apply(3);
        System.out.println(result1); // 3 + 3 = 6 * 2 = 12

        // andThen()
        // opposite of the compose() method.
        // this will first call multiply(), then call add()
        Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add);
        Integer result2 = multiplyThenAdd.apply(3);
        System.out.println(result2); // 3 * 2 = 6 + 3 = 9;
    }
}
