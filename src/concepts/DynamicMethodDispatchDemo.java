package concepts;

class A {
    void callme() {
        System.out.println("A's call method");
    }
}

class B extends A {
    void callme() {
        System.out.println("B's call method");
    }
}

class C extends A {
    @Override
    void callme() {
        System.out.println("C's call method");
    }
}

/**
 * A superclass reference variable can refer to a subclass object.
 * Java uses this fact to resolve calls to overridden methods at run time.
 *
 * When an overridden method is called through a superclass reference, Java determines which version of that method
 * to execute based upon the type of the object being referred to at the time the call occurs.
 * Thus, this determination is made at run time.
 *
 * In other words, it is the type of the object being referred to(not the type of the reference variable) that determines whichversion of an overridden method
 * will be executed.
 *
 * The purpose of overridden methods is to allow Java to support run-time polymorphism.
 * This is in line with Java's implementation of the "one interface, multiple methods" aspects of polymorphism.
 */
public class DynamicMethodDispatchDemo {
    public static void main(String[] args) {
        A a = new A(); // object type A
        B b = new B(); // object type B
        C c = new C(); // object type C

        A r; // a reference of type A

        r = a;
        r.callme(); // call A's version of callme

        r = b;
        r.callme(); // call B's version of callme

        r = c;
        r.callme(); // call C's version of callme
    }
}
