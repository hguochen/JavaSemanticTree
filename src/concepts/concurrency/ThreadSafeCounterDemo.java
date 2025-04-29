package concepts.concurrency;

/**
 * Shows locking on single shared variables across threads
 */
public class ThreadSafeCounterDemo {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    // each thread adds 1000. total is exactly 2000
    public static void main(String[] args) throws InterruptedException {
        ThreadSafeCounterDemo counter = new ThreadSafeCounterDemo();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}

