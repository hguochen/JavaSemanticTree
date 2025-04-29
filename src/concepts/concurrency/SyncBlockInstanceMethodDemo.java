package concepts.concurrency;

import org.w3c.dom.html.HTMLDocument;

/**
 * For sync block instance methods
 * - as opposed to sync instance method, a sync block only locks part of the method
 * - only 1 thread can execute inside the java code block sync on the same monitor object.
 *
 * A sync block lets you protect just a small part of your method where things need to be thread-safe, without slowing down
 * the whole method
 */
public class SyncBlockInstanceMethodDemo {
    private HTMLDocument log;

    // below 2 methods are equivalent wrt synchronization
    public synchronized void log1(String msg1, String msg2){
        log.writeln(msg1);
        log.writeln(msg2);
    }


    public void log2(String msg1, String msg2){
        // the object taken in the parentheses by the sync construct is called a monitor object.
        // the code is to be sync on the monitor object
        synchronized(this){
            log.writeln(msg1);
            log.writeln(msg2);
        }
    }
}
