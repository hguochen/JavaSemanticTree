package libraries.collections;

import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        // add new element to the end of the ll
        ll.add("A");
        ll.add("B");
        ll.add("C");
        System.out.println(ll);

        // add element to the end of linked list
        ll.addLast("Z");
        System.out.println(ll);

        // add element to the start of linked list
        ll.addFirst("X");
        System.out.println(ll);

        // remove element from the linked list
        ll.remove("X");
        ll.remove(3);
        System.out.println(ll);

        // remove first element
        ll.removeFirst();
        System.out.println(ll);
        // remove last element
        ll.removeLast();
        System.out.println(ll);
    }
}
