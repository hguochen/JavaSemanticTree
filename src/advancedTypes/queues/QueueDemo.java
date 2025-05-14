package advancedTypes.queues;

public class QueueDemo {
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>("monday");
        System.out.println(queue.peek());
        queue.enqueue("tuesday");
        queue.enqueue("wednesday");
        queue.enqueue("thursday");
        queue.enqueue("friday");
        queue.enqueue("saturday");
        queue.enqueue("sunday");
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
    }
}
