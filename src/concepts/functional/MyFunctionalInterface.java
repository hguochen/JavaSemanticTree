package concepts.functional;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is a functional interface because it contains only 1 abstract method, even though it can contain static methods
 * and other implemented methods.
 */
@FunctionalInterface
public interface MyFunctionalInterface {
    public void execute();

    public default void print(String text) {
        System.out.println(text);
    }

    public static void print(String text, PrintWriter writer) throws IOException {
        writer.write(text);
    }
}
