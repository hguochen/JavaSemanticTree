package concepts.concurrency;

public class ThreadSignallingDemo {
    public static void main(String[] args) {
        Object signalObject = new Object();

        // waiting Thread gets the sync block access, it calls wait() and releases the sync block access
        Thread waitingThread = new Thread(() -> {
            synchronized (signalObject) {
                try {
                    signalObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // assume notifying Thread already has the sync block access, it calls notify() to inform other waiting thread
        // to try to regain access, then immediately releases sync block access
        Thread notifyingThread = new Thread(() -> {
            synchronized (signalObject) {
                signalObject.notify();
            }
        });

        waitingThread.start();
        notifyingThread.start();
    }
}
