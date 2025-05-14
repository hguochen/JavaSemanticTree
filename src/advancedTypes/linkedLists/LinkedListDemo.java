package advancedTypes.linkedLists;

public class LinkedListDemo {
    public static void main(String[] args) {
        MyDoubleLinkedList<String> dll = new MyDoubleLinkedList<>("monday");
        dll.addLast("tuesday");
        dll.addLast("wednesday");
        dll.addLast("thursday");
        dll.addLast("friday");
        dll.addLast("saturday");
        dll.addLast("sunday");
        dll.printForward();
        dll.reverse();
        dll.printForward();
    }
}
