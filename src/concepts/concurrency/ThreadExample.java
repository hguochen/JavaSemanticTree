package concepts.concurrency;

/**
 * 4 approaches to specify what code a java thread should execute
 *
 * Type 1: Subclass the thread class
 * Type 2: Implement the runnable interface
 *  There are 3 ways to implement the runnable interface
 *      1. create a java class to implement the runnable interface
 *      2. create an anonymous class that implements the runnable interface
 *      3. create a java lambda expression that implements the runnable interface
 *
 * subclass threads or runnable? which approach is preferred?
 * Although both approach works. Implementing runnable interface might be better because when we have the runnable implementation
 * executed by a thread pool, it is easy to queue up the runnable interface until a thread from the pool is idle. It's somewhat
 * harder to do this in a subclassed Thread class.
 *
 */
public class ThreadExample {
    // Approach 1. subclass the Thread class
    public static class MyThread extends Thread {
        // overriding the run method to specify the code to be run by this thread
        @Override
        public void run() {
            System.out.println("MyThread running");
            System.out.println("MyThread finished");
        }
    }
    // Approach 2. implement the runnable interface
    public static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("MyRunnable running");
            System.out.println("MyRunnable finished");
        }
    }
    public static void main(String[] args) {
        // you can get the object reference to current running thread with currentThread()
        String threadName2 = Thread.currentThread().getName();
        System.out.println(threadName2);
        // Approach 1. create a new thread and start the thread running in parallel with the main thread
        MyThread myThread = new MyThread();
        // since now the thread is not doing anything, it starts and stops immediately
        // start() call will NOT wait for the run() method is done. the run() method will execute as if executed by
        // a different CPU.
        myThread.start();
        // you can also create an anonymous subclass of Thread like this:
        Thread thread2 = new Thread() {
            public void run() {
                System.out.println("Anonymous thread running");
            }
        };
        thread2.start();
        // if you instead do thread2.run() instead of start(), it will also work. however the difference is, now the code
        // ran in the run method is executed by the main calling thread, instead of a new thread to run the code. which defeats
        // the purpose of having a new thread object in the first place.
//        thread2.run();

        // Approach 2. running the Runnable interface
        Thread thread = new Thread(new MyRunnable());
        thread.start();

        // Approach 3. implement the Runnable interface as an anonymous class
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String runningRunning = "Running running";
                System.out.println(runningRunning);
                System.out.println("Runnable finished");
            }
        };
        // we can give the thread a custom name by passing a string as threadName
        Thread anonThread = new Thread(runnable, "Anonymous runnable thread");
        anonThread.start();
        System.out.println(anonThread.getName());

        // Approach 4. implement the runnable interface as a lambda
        Runnable lambdaRunnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Lambda Runnable running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Lambda Runnable finished");
        };
        Thread lambdaThread = new Thread(lambdaRunnable);
        lambdaThread.start();
    }
}
