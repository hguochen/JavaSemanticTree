package concepts.functional;

import java.util.function.Function;

/**
 * A functional interface can be implemented by a lambda expression.
 * <p>
 * Here we also describe some of Java's built-in function interfaces
 */
public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        // the lambda expression implements a single method from java interface, since the interface can only contain
        // single unimplemented method.
        MyFunctionalInterface lambda = () -> {
            System.out.println("Executing...");
        };

        // here 's how java.util.function.Function interface looks like
        //        public interface Function<T, R> {
        //            public <R> apply(T parameter);
        //        }

        // we can use our FunctionalImplDemo custom functional interface impl as such
        Function<Long, Long> adder = new FunctionalImplDemo();
        Long result = adder.apply((long) 4);
        System.out.println("result = " + result);
    }
}
