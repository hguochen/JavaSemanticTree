package oop.bank;

import java.util.ArrayList;

public abstract class BankAccount {
    protected static int counter = 1;
    protected int accountNumber;
    protected String name;
    protected int balance;
    protected ArrayList<String> transactionHistory;

    BankAccount(String name, int balance) {
        this.accountNumber = counter;
        counter += 1;
        this.name = name;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Account initiated.");
        this.transactionHistory.add("Account details: account_number: " + this.accountNumber + " name: " + this.name + " initial balance: " + this.balance);
    }

    protected String getName() {
        return this.name;
    }

    protected int getBalance() {
        return this.balance;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setBalance(int balance) {
        if (balance < 0) {
            throw new RuntimeException("You are not allowed to set negative balance");
        }
        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount < 0) {
            throw new RuntimeException("Invalid deposit amount");
        }
        this.balance += amount;
        this.transactionHistory.add("Deposited amount: " + amount + ". New Balance: " + this.balance);
    }

    public int withdraw(int amount) {
        if (amount < 0) {
            throw new RuntimeException("Invalid withdraw amount");
        } else if (amount > this.balance) {
            throw new RuntimeException("Overdraft is not allowed");
        }
        this.balance -= amount;
        this.transactionHistory.add("Withdrawn amount: " + amount + ". New Balance: " + this.balance);
        return amount;
    }

    public void printTransactionHistory() {
        for (String transaction : this.transactionHistory) {
            System.out.println(transaction);
        }
    }
}
