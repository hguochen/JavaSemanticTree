package concepts;

/**
 * There are 2 major scopes in java:
 * - those defined by a class
 * - those defined by a method
 *
 * General rule:
 * - variables declared inside a scope are not visible to code that is defined outside that scope
 * - variables declared outside a scope are visible to code inside of its enclosing scope
 * - variables are created when their scope is entered, and destroyed when their scope is left
 * - although blockeds can be nested, you cannot declare a variable to have the same name as one in an outer scope
 */
public class ScopeDemo {
    public static void main(String[] args) {
        int x; // known to all code within main

        x = 10;
        if (x == 10) { // start new scope
            int y = 20; // known only to this block
            x = 15; // inner scope can redefine outer scope variables, and they persist outside of the inner scope
            System.out.println("x and y: " + x + " " + y);
        }
        // y = 100; // error! y not known here
        System.out.println("x is: " + x); // x is still known here
    }
}
