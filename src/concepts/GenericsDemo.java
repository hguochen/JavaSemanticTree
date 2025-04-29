package concepts;

// T is a type param that will be replaced by a real type
// when an object of type Gen is created
class Gen<T> {
    // declare an object of type T
    T ob;

    // pass the constructor a reference of type T
    Gen(T o) {
        ob = o;
    }

    T getOb() {
        return ob;
    }

    // show type of T
    void showType() {
        System.out.println("Type of T is " + ob.getClass().getName());
    }
}

public class GenericsDemo {
    public static void main(String[] args) {
        Gen<Integer> iOb;

        // create a Gen<Integer> object and assign its reference to iOb.
        iOb = new Gen<Integer>(88);

        iOb.showType();

        int v = iOb.getOb();
        System.out.println("value: " + v);

        System.out.println();

        // create a gen object for strings
        Gen<String> strOb = new Gen<>("Generics Test");
        strOb.showType();

        // get the value of strOb.
        String str = strOb.getOb();
        System.out.println("value: " + str);
    }
}
