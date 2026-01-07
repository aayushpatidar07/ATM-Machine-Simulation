package com.atm.service;

import com.atm.model.Account;
import com.atm.model.Transaction;
import com.atm.util.InputValidator;
import java.util.ArrayList;
import java.util.List;

/**
 * ATMService class handles all ATM operations and business logic
 */
public class ATMService {
    private Account account;
    private List<Transaction> transactionHistory;
    private static final int MAX_HISTORY_SIZE = 5;

    /**
     * Constructor
     */
    public ATMService(Account account) {
        this.account = account;
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Authenticates user with PIN
     */
    public boolean authenticate(String pin) {
        return account.validatePin(pin);
    }

    /**
     * Returns current balance
     */
    public double checkBalance() {
        return account.getBalance();
    }

    /**
     * Deposits money to account
     */
    public boolean deposit(double amount) {
        if (!InputValidator.isValidAmount(amount)) {
            return false;
        }
        
        account.deposit(amount);
        addTransaction("DEPOSIT", amount, account.getBalance());
        return true;
    }

    /**
     * Withdraws money from account
     */
    public boolean withdraw(double amount) {
        if (!InputValidator.isValidAmount(amount)) {
            return false;
        }
        
        if (account.withdraw(amount)) {
            addTransaction("WITHDRAW", amount, account.getBalance());
            return true;
        }
        return false;
    }

    /**
     * Adds transaction to history (keeps last 5 transactions)
     */
    private void addTransaction(String type, double amount, double balanceAfter) {
        Transaction transaction = new Transaction(type, amount, balanceAfter);
        transactionHistory.add(0, transaction); // Add to beginning
        
        // Keep only last 5 transactions
        if (transactionHistory.size() > MAX_HISTORY_SIZE) {
            transactionHistory.remove(transactionHistory.size() - 1);
        }
    }

    /**
     * Returns transaction history
     */
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    /**
     * Returns account holder name
     */
    public String getAccountHolderName() {
        return account.getAccountHolderName();
    }

    /**
     * Returns account number (masked)
     */
    public String getMaskedAccountNumber() {
        String accNum = account.getAccountNumber();
        return "XXXXX" + accNum.substring(accNum.length() - 4);
    }
}
