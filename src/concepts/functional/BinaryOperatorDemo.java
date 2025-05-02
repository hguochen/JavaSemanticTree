package concepts.functional;

import java.util.function.BinaryOperator;

/**
 * BinaryOperator interface is a functional interface that represents an operation which takes two params and returns a single value.
 * Both params and the return type must be of the same type.
 *
 * This operation is useful when implementing functions that sum, subtract, divide, multiply etc. Two elements of the same type,
 * and returns a third element of the same type
 */
class MyValue {
    private int value;
    public MyValue(int val) {
        this.value = val;
    }
    public void add(MyValue value) {
        this.value += value.getValue();
    }

    public int getValue() {
        return value;
    }
}
public class BinaryOperatorDemo {
    public static void main(String[] args) {
        BinaryOperator<MyValue> binaryOperator = (value1, value2) -> {value1.add(value2); return value1;};
        MyValue newValue = binaryOperator.apply(new MyValue(3), new MyValue(5));
        System.out.println(newValue.getValue()); // 8
    }
}
