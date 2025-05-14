package advancedTypes.linkedLists;

class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> prev;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public Node<T> getNext() {
        return next;
    }
}

/**
 * MyDoubleLinkedList is an implementation of linked list, just like LinkedList collection in Java.
 */
public class MyDoubleLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private static String FORWARD = "FORWARD";
    private static String BACKWARD = "BACKWARD";

    /**
     * Constructor
     */
    public MyDoubleLinkedList(T data) {
        this.head = new Node<>(data);
        this.tail = this.head;
        this.size = 1;
    }

    /**
     * Insert data at the beginning
     * Time: O(1)
     * Space: O(1)
     * @param data
     */
    public void addFirst(T data) {
        Node<T> temp = this.head;
        Node<T> newNode = new Node<>(data);
        newNode.setNext(temp);
        temp.setPrev(newNode);
        this.head = newNode;
        this.size += 1;

    }

    /**
     * Insert data at the end
     * Time: O(1)
     * Space: O(1)
     * @param data
     */
    public void addLast(T data) {
        Node<T> temp = this.tail;
        Node<T> newNode = new Node<>(data);
        newNode.setPrev(temp);
        temp.setNext(newNode);
        this.tail = newNode;
        this.size += 1;
        return;
    }

    /**
     * Remove and return the first element
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public T removeFirst() {
        if (this.head == null) return null;
        Node<T> nodeToRemove = this.head;
        this.head = nodeToRemove.getNext();
        if (this.head == null) this.tail = null;
        this.head.setPrev(null);
        if (this.head == null) this.tail = null;
        else this.head.setPrev(null);
        T data = nodeToRemove.getData();
        nodeToRemove.setNext(null);
        nodeToRemove = null;
        this.size -= 1;
        return data;
    }

    /**
     * Remove and return the last element
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public T removeLast() {
        if (this.tail == null) return null;
        Node<T> nodeToRemove = this.tail;
        this.tail = nodeToRemove.getPrev();
        if (this.tail == null) this.head = null;
        else this.tail.setNext(null);
        T data = nodeToRemove.getData();
        nodeToRemove.setPrev(null);
        nodeToRemove = null;
        this.size -= 1;
        return data;
    }

    /**
     * Remove the first node matching data
     * Time: O(n)
     * Space: O(1)
     * @param data
     * @return
     */
    public T remove(T data) {
        if (this.head.getData().equals(data)) return this.removeFirst();
        if (this.tail.getData().equals(data)) return this.removeLast();
        Node<T> trav = this.head.getNext();
        while (trav != null) {
            if (trav.getData().equals(data)) {
                // remove current node
                Node<T> prev = trav.getPrev();
                Node<T> next = trav.getNext();
                prev.setNext(next);
                next.setPrev(prev);
                trav.setNext(null);
                trav.setPrev(null);
                T result = trav.getData();
                trav = null;
                this.size -= 1;
                return result;
            }
            trav = trav.getNext();
        }
        return null;
    }

    /**
     * Checks if the list contains a value
     * Time: O(n)
     * Space: O(1)
     * @param data
     * @return
     */
    public boolean contains(T data) {
        if (this.head == null) return false;
        Node<T> trav = this.head;
        while (trav != null) {
            if (data.equals(trav.getData())) return true;
            trav = trav.getNext();
        }
        return false;
    }

    /**
     * Reverse the list in-place
     * Time: O(n)
     * Space: O(1)
     */
    public void reverse() {
        if (this.head == null) return;
        Node<T> prev = null;
        Node<T> curr = this.head;
        while (curr != null) {
            Node<T> next = curr.getNext();
            curr.setNext(prev);
            curr.setPrev(next);
            prev = curr;
            curr = next;
        }
        this.tail = this.head;
        this.head = prev;
    }

    /**
     * Return the number of elements
     * Time: O(1)
     * Space: O(1)
     * @return
     */
    public int size() {
        return this.size;
    }

    /**
     * Print list from hea to tail
     */
    public void printForward() {
        System.out.println(this.buildSequence(FORWARD));
    }

    /**
     * Print list from tail to head
     */
    public void printBackward() {
        System.out.println(this.buildSequence(BACKWARD));
    }

    /**
     * Builds the print sequence
     * Time: O(n)
     * Space: O(n)
     * @param sequence
     * @return
     */
    private StringBuilder buildSequence(String sequence) {
        if (this.head == null || this.tail == null) return new StringBuilder("size: 0 []");
        StringBuilder str = new StringBuilder("size: ").append(this.size()).append(" [");
        Node<T> trav = (sequence.equals(FORWARD)) ? this.head : this.tail;
        while (trav != null) {
            str.append(trav.getData());
            Node<T> nextNode = (sequence.equals(FORWARD)) ? trav.getNext() : trav.getPrev();
            if (nextNode != null) {
                str.append(" -> ");
            }
            trav = nextNode;
        }
        str.append("]");
        return str;
    }
}
