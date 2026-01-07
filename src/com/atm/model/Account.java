package com.atm.model;

/**
 * Account class represents a bank account with balance and account details
 */
public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;

    /**
     * Constructor to initialize account
     */
    public Account(String accountNumber, String accountHolderName, double balance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = pin;
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * Validates PIN
     */
    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    /**
     * Deposits amount to account
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Withdraws amount from account
     */
    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account [Number=" + accountNumber + ", Holder=" + accountHolderName + 
               ", Balance=$" + String.format("%.2f", balance) + "]";
    }
}
