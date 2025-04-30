package concepts.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * this is a typical sync block implementation that guarantees only 1 thread can execute increment() at a time.
 */
class Counter {
    private int count = 0;

    public int increment() {
        synchronized (this) {
            return ++count;
        }
    }
}

/**
 * Here we achieve the same effort as Counter class using Lock instead of a sync block.
 *
 * interface Lock {
 *     void lock();                         // blocks until lock is acquired
 *     void lockInterruptibly() throws InterruptedException;
 *     boolean tryLock();                   // tries to acquire without blocking
 *     boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
 *     void unlock();                       // releases the lock
 *     Condition newCondition();            // returns a condition for wait/notify
 * }
 */
class CounterLock {
    private Lock lock = new Lock() {
        @Override
        public void lock() {

        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {

        }

        @Override
        public Condition newCondition() {
            return null;
        }
    };
    private int count = 0;

    public int increment() {
        // lock() method locsk instance so that all threads calling lock() are blocked untiled unblock() is executed
        lock.lock();
        int newCount = ++count;
        lock.unlock();
        return newCount;
    }
}

public class LockDemo {

}
