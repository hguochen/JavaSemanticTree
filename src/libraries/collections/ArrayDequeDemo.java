package libraries.collections;

import java.util.ArrayDeque;

/**
 * ArrayDeque is short for 'Array Double-Ended Queue. It's a resizeable array based implementation of deque.
 * Provides efficient FIFO and LIFO operations
 *
 * Features
 * - Faster than linked list - avoids the overhead of linked nodes
 * - Supports both stack and queue operations - Can be used as FIFO(queue) or LIFO(stack)
 * - Dynamically resizing - automatically grows when needed
 * - No Capacity Restriction - Unlike ArrayBlockingQueue, it does not have a fixed capacity
 * - Null not allowed - cannot store null values in an ArrayDeque(prevents ambiguity in removals)
 * - Not thread safe - Needs external synchronization when used in multithreading
 *
 * When to use ArrayDeque
 * - Need a fast queue or stack implementation
 * - Avoid using linked list due to higher memory overhead
 * - Don't need random access like ArrayList
 * - Need efficient insertions and removals at both ends
 */
public class ArrayDequeDemo {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(10);
        deque.add(20);
        deque.add(40);

        // adds an element at the front
        deque.addFirst(1);
        // adds an element at the back
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(0);

        // removes and returns the first element
        System.out.println(deque.removeFirst());
        // removes and returns the last element
        System.out.println(deque.removeLast());

        // retrieves but does not remove the first element
        System.out.println(deque.getFirst());
        // retrieves but does not remove the last element
        System.out.println(deque.getLast());

        // check if collection is empty
        System.out.println(deque.isEmpty());
        // check size of collection
        System.out.println(deque.size());
        System.out.println(deque);

        // using ArrayDeque as Queue(FIFO)
        ArrayDeque<String> queue = new ArrayDeque<>();

        // Adding elements (enqueue)
        // adds an element at the tail
        queue.offer("Apple");
        queue.offer("Banana");
        queue.offer("Cherry");

        System.out.println(queue); // [Apple, Banana, Cherry]

        // Removing elements (dequeue)
        // poll retrieves and removes the head element
        System.out.println(queue.poll()); // Apple
        System.out.println(queue.poll()); // Banana
        // retrieves but does not remove the head element
        System.out.println(queue.peek()); // Cherry

        // using ArrayDeque as Stack(LIFO)
        ArrayDeque<String> stack = new ArrayDeque<>();

        // Pushing elements (LIFO)
        // adds an element at the head
        stack.push("Dog");
        stack.push("Cat");
        stack.push("Elephant");

        System.out.println(stack); // [Elephant, Cat, Dog]

        // Popping elements
        // removes and returns the first element
        System.out.println(stack.pop()); // Elephant
        System.out.println(stack.pop()); // Cat
    }
}
