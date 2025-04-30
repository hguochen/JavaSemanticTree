package concepts.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock allows a thread to re-acquire the same lock it already holds. ReentrantLock is the go-to implementation for locks.
 *
 * Best practices
 * - always release the lock in a finally block
 * - don't mix Lock and synchronized on the same resource
 * - use tryLock with timeout to avoid deadlocks in complex systems
 */
public class ReentrantLockDemo {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock(); // acquire the lock
        try {
            count++;
        } finally {
            lock.unlock(); // always release the lock in a finally block
        }
    }

    public int getCount() {
        return count;
    }
}
