package libraries.collections;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * PriorityQueue is an implementation of a min-heap or max-heap data structure.
 * Useful when you need a dynamically sorted collection where elements are always retrieved in a specific priority order
 *
 * Features
 * - Ordered by priority - Element with the highest priority is served first
 * - Not a sorted list - The internal implementation is a heap structure, so iteration does not guarantee sorted order
 * - Not thread safe - If multiple threads accesses it, it needs external synchronization
 * - Null not allowed - Adding null will throw NullPointerException
 *
 * When to use PriorityQueue
 * - when you need a dynamically ordered collection where priority matters
 *
 * Time Complexity:
 * - Insertion -> O(logn)
 * - Deletion -> O(logn)
 * - Retrieving -> O(1)
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        // by default, a min-heap priority queue is created
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // queue.add(null); // leads to NullPointerException
        // inserts an element into the queue
        queue.add(4);
        queue.add(2);
        queue.add(10);
        queue.add(25);

        // if you want to create a max-heap priority queue, use a custom comparator to reverse the natural ordering
        PriorityQueue<Integer> queue2 = new PriorityQueue<>(Comparator.reverseOrder());
        queue2.add(4);
        queue2.add(2);
        queue2.add(10);
        queue2.add(25);
        System.out.println(queue2);

        // inserts an element, but returns false instead of throwing exception
        queue2.offer(34);

        // removes and returns the highest priority element. returns null if empty
        System.out.println(queue2.poll());

        // return but does NOT remove the highest priority element. returns null if empty
        System.out.println(queue2.peek());

        // remove a specific element from the queue
        System.out.println(queue2.remove(4)); // true, if element is present
        System.out.println(queue2.remove(4)); // false, if element is not found

        // return the size of the queue
        System.out.println(queue2.size());

        // removes all elements
        queue2.clear();
        System.out.println(queue2);
    }
}
