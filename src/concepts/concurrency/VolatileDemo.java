package concepts.concurrency;

/**
 * Java volatile keyword is used to mark a Java variable as "being stored in main memory". ie. Every read of a volatile
 * variable will be read from the computer's main memory, and not from the CPU registers. and that every write to a volatile
 * variable will be written to main memory, and not just to the CPU registers.
 *
 */
public class VolatileDemo {
    // 1. volatile read happens before guarantee
    // Once you read a volatile variable, nothing that happens after can be moved before that read.
    volatile int flag = 0;

    // when the thread reads flag, it must read flag before it sets x = 1 and y = 2;
    // java cannot reorder x or y sets before reading flag
    // but, java could move something that happens before the flag read to after it if needed
    void myMethod() {
        int f = flag;    // volatile read
        int x = 1;       // normal write
        int y = 2;       // normal write
    }

    // 2. volatile write happens before guarantee
    // Once you write to a volatile variable, everything before it is locked in order.
    // Java cannot cheat and rearrange those earlier steps after the volatile.

    // java must do x = 1, y = 2 before setting flagWrite
    // java cannot reorder and do flag = 1 first then x = 1
    // but it could move some later things (after flag = 1) up before the flag = 1 statement
    int x = 1;        // normal write
    int y = 2;        // normal write
    volatile int flagWrite = 1;  // volatile write
}
