package concepts.concurrency;

import static java.lang.Thread.sleep;

public class ThreadDaemon {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Running");
            }
        };
        // scenario 1: now we have a second thread running in parallel with JVM main thread. even though the main thread
        // has stopped running. the second thread still keeps the JVM thread active, without being terminated.
        Thread thread = new Thread(runnable);
        // to toggle the scenario 1 behavior, we can set daemon to true. that way the second thread will stop as soon as JVM main thread
        // is stopped.
        thread.setDaemon(true);
        thread.start();
        sleep(3100);
    }
}
