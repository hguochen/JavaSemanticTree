package advancedTypes;

import java.util.ArrayList;

/**
 * Implementation of a stack data structure using ArrayList
 */
class Stack {
    ArrayList<Integer> stack;
    public Stack() {
        this.stack = new ArrayList<>();
    }

    public void push(int element) {
        this.stack.add(element);
    }

    public int pop() {
        if (this.stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return this.stack.remove(this.stack.size() - 1);
    }
}
public class StackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
