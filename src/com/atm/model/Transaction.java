package com.atm.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Transaction class to record all ATM transactions
 */
public class Transaction {
    private String type;
    private double amount;
    private double balanceAfter;
    private LocalDateTime timestamp;

    /**
     * Constructor
     */
    public Transaction(String type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns formatted transaction details
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-12s | $%-10.2f | Balance: $%-10.2f | %s", 
                           type, amount, balanceAfter, timestamp.format(formatter));
    }
}
