package concepts.concurrency;

/**
 * What is synchronized in Java?
 * - synchronized is a keyword used to control access to a block of code or method among multiple threads
 * - it prevents race conditions - only 1 thread at a time can execute a piece of code
 * - Synchronized = Exclusive Access = Thread Safe Execution
 *
 * Where can you use synchronized
 * - synchronized method
 * - synchronized blocked
 * you "lock" an object or a class so only one thread can run the synchronized code at a time
 *
 * When Java sees 'synchronized', it internally:
 * - acquires a monitor lock on the object
 * - allows only one thread with the lock to execute the code
 * - other threads block(wait) until the lock is released.
 * Note: locking costs performance -> do not overuse synchronized
 *
 * When to use synchronized?
 * - Shared data can be modified by multiple threads
 * - You need to make operations atomic(cannot be interrupted midway)
 *
 * What to avoid when using synchronized?
 * - long synchronized blocks
 * - lock objects you don't control(could cause deadlocks)
 * There are 3 ways to declare a synchronized routine.
 */
public class SynchronizedDemo {
    protected Object object = null;
    private static int count = 0;

    // Approach 1. declare synchronized method
    // synchronized on the method = locks the current object(this)
    // if thread 1 is running setObject(), thread 2 must wait until thread 1 finishes
    public synchronized void setObject(Object o) {
        this.object = o;
    }

    public synchronized Object getObject() {
        return this.object;
    }

    // Approach 2. declared synchronized block within method
    // here only a portion of the code is synchronized
    // we apply a lock on the (this) object manually
    // why use sync block?
    //  More fine-grained, better performance than synchronizing the whole method sometimes
    public void setObj(Object o) {
        // this here is the monitor object
        synchronized (this) {
            this.object = o;
        }
    }

    public Object getObj() {
        synchronized (this) {
            return this.object;
        }
    }

    // Approach 3. synchronized on a class static methods
    // lock the Class Object itself, not an instance.
    // useful for static variables shared across all instances
    public static synchronized void printObj() {
        count++;
    }
}
