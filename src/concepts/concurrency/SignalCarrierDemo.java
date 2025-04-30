package concepts.concurrency;

import sun.misc.Signal;

/**
 * Here we have a custom implementation of SignalObject
 */
class SignalCarrier {
    boolean wasSignalled = false;

    public void doWait() throws InterruptedException {
        synchronized (this) {
            if (!wasSignalled) {
                System.out.println(Thread.currentThread().getName() + " calling wait().");
                this.wait();
                System.out.println(Thread.currentThread().getName() + " exited wait().");
            }
            // clear signal and continue running
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (this) {
            wasSignalled = true;
            System.out.println(Thread.currentThread().getName() + " calling notify()");
            this.notify();
            System.out.println(Thread.currentThread().getName() + " exited notify()");
        }
    }

    public void doNotifyAll() {
        synchronized (this) {
            wasSignalled = true;
            System.out.println(Thread.currentThread().getName() + " calling notifyAll()");
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + " exited notifyAll()()");
        }
    }
}

public class SignalCarrierDemo {
    public static void main(String[] args) {
        SignalCarrier signalCarrier = new SignalCarrier();
        boolean wasSignalled = false;

        Thread waiter = new Thread(() -> {
            try {
                signalCarrier.doWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread notifier = new Thread(() -> {
            signalCarrier.doNotify();
        });
        // Scenario 1: normal wait and notify exits correctly.
//        Thread-0 calling wait().
//        Thread-1 calling notify()
//        Thread-1 exited notify()
//        Thread-0 exited wait().

        // Scenario 2: missed signal
        // Thread-1 calling notify() -> this notify is lost because no thread is on wait state yet
        // Thread-1 exited notify() -> thread-1 exits and releases sync lock
        // Thread-0 calling wait() -> however, thread-0 did not catch the notify signal and therefore still waiting on a notify signal
        // as a result, Thread-0 ends up waiting forever and never waking up. Because the signal to wake up is missed
        // to avoid missed signal problem, we need a flag to store the signal sent state
        waiter.start();
        notifier.start();
    }
}
