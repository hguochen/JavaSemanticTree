package oop.bank;

/**
 * A banking branch that allows customers to interact with the banking system.
 */
public class Branch {
    public static void main(String[] args) {
        // create a new customer checking account
        CheckingAccount accountMary = new CheckingAccount("Mary", 2000, 1000);
        accountMary.deposit(1200);
        accountMary.withdraw(250);
        accountMary.withdraw(2500);
        System.out.println("Overdraft limit is: " + accountMary.getOverdraftLimit());
        accountMary.printTransactionHistory();

        SavingsAccount accountDavid = new SavingsAccount("David", 3000);
        accountDavid.deposit(50000);
        accountDavid.withdraw(1500);
        accountDavid.printTransactionHistory();
        accountDavid.projectedEarnings(5);
    }
}
