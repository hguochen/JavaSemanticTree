package concepts.functional;

import java.util.function.UnaryOperator;

/**
 * UnaryOperator interface is a functional interface that represents an operation which takes a single param and
 * returns a param of the same type.
 *
 * It can be used to represent an operation that takes a specific object param, modifies the object and returns it
 * again. This is useful as part of a functional stream processing chain
 */
public class UnaryOperatorDemo {
    public static void main(String[] args) {
        UnaryOperator<Object> unaryOperator = (obj) -> {
            System.out.println(obj.getClass().getName()); return obj;};
    }
}
