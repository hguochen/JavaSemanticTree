package concepts.concurrency;

/**
 * Shows locking on specific shared resources
 */
public class ThreadSafeBankTransferDemo {
    private int balance;

    public ThreadSafeBankTransferDemo(int initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized void withdraw(int amount) {
        balance -= amount;
    }

    public synchronized int getBalance() {
        return balance;
    }

    public static void transfer(ThreadSafeBankTransferDemo from, ThreadSafeBankTransferDemo to, int amount) {
        // lock from account first, then lock to account
        // this prevents two threads from messing up balance while transferring
        synchronized (from) {
            synchronized (to) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}
