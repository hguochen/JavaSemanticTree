package advancedTypes.stacks;

import java.util.ArrayList;

/**
 * Stacks follow the Last-in, First-Out(LIFO) principle.
 */
public class MyStacks<T> {
    private ArrayList<T> stack;

    public MyStacks() {
        this.stack = new ArrayList<>();
    }

    /**
     * Pushes an item onto the top of the stack
     * Time: O(1)
     * Space: O(1)
     * @param item
     */
    public void push(T item) {
        this.stack.add(item);
    }

    /**
     * Removes and returns the top item. throws Exception if empty
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public T pop() {
        if (this.stack.isEmpty()) throw new RuntimeException("Stack is empty");
        return this.stack.remove(this.stack.size()-1);
    }

    /**
     * Returns the top item without removing it.
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public T peek() {
        if (this.stack.isEmpty()) throw new RuntimeException("Stack is empty");
        return this.stack.get(this.stack.size()-1);
    }

    /**
     * Returns true if the stack is empty
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * Returns the number of elements in the stack
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * Removes all elements
     * Time: O(n)
     * Space: O(1)
     */
    public void clear() {
        this.stack.clear();
    }

    /**
     * Time: O(n)
     * Space: O(n)
     * @return
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        if (this.isEmpty()) return str.append("]").toString();
        for (T item : this.stack) {
            str.append(item.toString()).append(" ");
        }
        str.deleteCharAt(str.length() - 1);
        str.append("]");
        return str.toString();
    }


}
