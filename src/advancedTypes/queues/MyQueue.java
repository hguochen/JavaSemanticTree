package advancedTypes.queues;

class Node<T> {
    private T data;
    private Node<T> next;

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
/**
 * Implement a Queue that simulates First-in, First-Out (FIFO) behavior.
 */
public class MyQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public MyQueue(T data) {
        this.head = new Node<>(data);
        this.tail = this.head;
        this.size = 1;
    }

    @Override
    public String toString() {
        if (this.head == null) return "size: 0 []";
        StringBuilder str = new StringBuilder("size: ").append(this.size).append(" [");
        Node<T> curr = this.head;
        while (curr != null) {
            str.append(curr.getData());
            curr = curr.getNext();
            if (curr != null) {
                str.append(" <- ");
            }
        }
        str.append("]");
        return str.toString();
    }

    /**
     * Add item to the back of the queue
     * Time: O(1)
     * Space: O(1)
     *
     * @param item
     */
    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        this.tail.setNext(newNode);
        this.tail = newNode;
        this.size += 1;
    }

    /**
     * Remove and return the front item(or throw if empty)
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public T dequeue() {
        if (this.head == null) throw new RuntimeException("Queue is empty.");
        Node<T> nodeToRemove = this.head;
        this.head = this.head.getNext();
        T data = nodeToRemove.getData();
        nodeToRemove.setNext(null);
        nodeToRemove = null;
        this.size -= 1;
        return data;
    }

    /**
     * Return the front item without removing it
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public T peek() {
        if (this.head == null) throw new RuntimeException("Queue is empty.");
        return this.head.getData();
    }

    /**
     * Return true if the queue is empty
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public boolean isEmpty() {
        if (this.head == null) return true;
        return false;
    }

    /**
     * Return the number of items in the queue
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public int size() {
        return this.size;
    }
}
