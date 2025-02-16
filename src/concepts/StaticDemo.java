package concepts;

/**
 * We can initialize static variables by declaring a static block that gets executed exactly once, when the class
 * is first loaded.
 *
 * As soon as this class is loaded, all of the static statements are run.
 * - a is set to 3
 * - static block executes
 * - main method called
 */
public class StaticDemo {
    static int a = 3;
    static int b;

    // a normal static method that doesn't require object initialization
    static void meth(int x) {
        System.out.println("x = " + x);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    // executes when the class is first loaded
    static {
        System.out.println("Static block initialized");
        b = a * 4;
    }

    public static void main(String[] args) {
        meth(42);
    }
}
