package concepts.concurrency;

/**
 * What is ThreadLocal?
 * Java ThreadLocal class enables you to create variables that can only be read and written by the same thread.
 * Thus, even if 2 threads are executing the same code, and the code has a reference to the same ThreadLocal variable, the 2
 * threads cannot see each other's ThreadLocal variables.
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        // creating a ThreadLocal instance
        ThreadLocal threadLocal = new ThreadLocal();
        // set threadLocal value
        threadLocal.set("A thread local value");
        // get threadLocal value
        String threadLocalValue = (String) threadLocal.get();
        // remove threadLocal value
        threadLocal.remove();

        // generic ThreadLocal
        ThreadLocal<String> myThreadLocal = new ThreadLocal<>();
        // you do not have to typecast the value returned by get()
        myThreadLocal.set("Hello ThreadLocal");
        String threadLocalValue2 = myThreadLocal.get();
    }
}
