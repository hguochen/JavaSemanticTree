package oop.bank;

public class SavingsAccount extends BankAccount {
    private double interestRate;

    SavingsAccount(String name, int balance) {
        super(name, balance);
        this.interestRate = 0.04;
        this.transactionHistory.add("Interest rate: " + this.interestRate);
    }

    public void setInterestRate(double rate) {
        this.interestRate = rate;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void projectedEarnings(int year) {
        if (year < 1) {
            System.out.println(this.balance);
        }
        double balance = this.balance;
        for (int i = 1; i <= year; i++) {
            balance = balance + (balance * this.interestRate);
            System.out.println("Balance after year " + year + " is: " + balance);
        }
    }
}
