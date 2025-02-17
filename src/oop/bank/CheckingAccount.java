package oop.bank;

public class CheckingAccount extends BankAccount {
    private int overdraftLimit;

    CheckingAccount(String name, int balance, int overdraftLimit) {
        super(name, balance);
        this.overdraftLimit = overdraftLimit;
        this.transactionHistory.add("overdraft limit: " + this.overdraftLimit);
    }

    public void setOverdraftLimit(int overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public int getOverdraftLimit() {
        return this.overdraftLimit;
    }

    @Override
    public int withdraw(int amount) {
        if (amount < 0) {
            throw new RuntimeException("Invalid withdraw amount");
        } else if (amount > this.balance + this.overdraftLimit) {
            throw new RuntimeException("Overdraft limit exceeded. Transaction failed.");
        }
        this.balance -= amount;
        this.transactionHistory.add("Withdrawn amount: " + amount + ". New Balance: " + this.balance);
        return amount;
    }
}
