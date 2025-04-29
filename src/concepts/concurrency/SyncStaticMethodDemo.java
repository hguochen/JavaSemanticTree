package concepts.concurrency;

/**
 * For sync static methods
 * - locks on the class itself, not on an object(instance)
 * - In Java, for every class, only 1 "class object" exists inside the JVM
 * - thus, only one thread can run a static sync method of that class at a time - no matter how many instances u have
 *
 * Summary: If 2 threads both want to run a static sync method of the same class, they must wait their turn. Only one can run
 * it at a time, because they are locking the entire class
 */
public class SyncStaticMethodDemo {
    private static int count = 0;

    public static synchronized void increment() {
        count++;
    }

    public static synchronized int getCount() {
        return count;
    }

    public static int getCount2() {
        synchronized (SyncInstanceMethodDemo.class) {
            return count;
        }
    }
}
