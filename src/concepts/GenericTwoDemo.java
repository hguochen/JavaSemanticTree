package concepts;

// a generic class with two type parameters: T and V
class TwoGen<T, V> {
    T ob1;
    V ob2;

    TwoGen(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }

    void showTypes() {
        System.out.println(" Type of T is: " + ob1.getClass().getName());
        System.out.println(" Type of V is: " + ob2.getClass().getName());
    }

    T getOb1() {
        return ob1;
    }

    V getOb2() {
        return ob2;
    }
}
public class GenericTwoDemo {
    public static void main(String[] args) {
        TwoGen<Integer, String> tgObj =
                new TwoGen<Integer, String>(88, "Generics");
        // Show the types.
        tgObj.showTypes();
        // Obtain and show values.
        int v = tgObj.getOb1();
        System.out.println("value: " + v);
        String str = tgObj.getOb2();
        System.out.println("value: " + str);
    }
}
