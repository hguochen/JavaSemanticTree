package concepts.concurrency;

/**
 * For synchronized instance methods
 * - lock is on the specific object
 * - only 1 thread at a time can run a sync method on the same obj
 * - but different objs can run their sync methods at the same time, because their each have their own locks
 *
 * Summary: A sync method locsk the obj it belongs to, not all object of that class.
 */
public class SyncInstanceMethodDemo {
    private int count = 0;
    public synchronized void add(int value) {
        this.count += value;
    }

    public static void main(String[] args) {
        // if 2 threads call a.add() at the same time -> they will take turns because a is locked
        // but if 1 thread calls a.add() and another calls. b.add() -> they can run at the same time because a and b are
        // different objects
        SyncInstanceMethodDemo a = new SyncInstanceMethodDemo();
        SyncInstanceMethodDemo b = new SyncInstanceMethodDemo();
    }
}
