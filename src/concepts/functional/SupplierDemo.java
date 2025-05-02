package concepts.functional;

import java.util.function.Supplier;

/**
 * Supplier interface is a functional interface that represents a function that supplies a value of some sorts. It can
 * be seen as a factory interface.
 */
public class SupplierDemo {
    public static void main(String[] args) {
        // returns a new Integer instance with a random value between 0 and 1000.
        Supplier<Integer> supplier = () -> new Integer((int) (Math.random() * 1000D));
        for (int i = 0; i < 5; i++) {
            int newValue = supplier.get();
            System.out.println(newValue);
        }
    }
}
