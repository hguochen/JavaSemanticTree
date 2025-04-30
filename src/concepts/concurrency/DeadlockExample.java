package concepts.concurrency;

public class DeadlockExample {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: holding lock1...");
                try { Thread.sleep(100); } catch (Exception e) {e.printStackTrace();}
                synchronized (lock2) {
                    System.out.println("Thread 1: acquired lock2!");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: holding lock2...");
                try { Thread.sleep(100); } catch (Exception e) { e.printStackTrace();}
                synchronized (lock1) {
                    System.out.println("Thread 2: acquired lock1!");
                }
            }
        });

        // t1 locks lock1 waiting for lock2
        // t2 locks lock2 waiting for lock1
        // deadlock. neither thread can move forward
        t1.start();
        t2.start();
    }
}
