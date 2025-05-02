package concepts.functional;

import java.util.function.Consumer;

/**
 * Consumer interface is a functional interface that represents a function that consumes a value without returning any value.
 *
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<Integer> consumer = (value) -> System.out.println(value);
        consumer.accept(5);
    }
}
