package atm;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Account class represents a bank account with account holder details and balance
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;
    private List<String> transactionHistory;

    /**
     * Constructor to initialize account with default values
     */
    public Account(String accountNumber, String accountHolderName, double balance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    // Setter methods
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Validates the entered PIN
     * @param inputPin PIN entered by user
     * @return true if PIN matches, false otherwise
     */
    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    /**
     * Deposits amount to the account
     * @param amount Amount to deposit
     */
    public void deposit(double amount) {
        this.balance += amount;
        addTransaction("DEPOSIT", amount, this.balance);
    }

    /**
     * Withdraws amount from the account
     * @param amount Amount to withdraw
     * @return true if withdrawal successful, false if insufficient balance
     */
    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            addTransaction("WITHDRAWAL", amount, this.balance);
            return true;
        }
        return false;
    }

    /**
     * Returns masked account number for security
     * @return Masked account number
     */
    public String getMaskedAccountNumber() {
        return "XXXXX" + accountNumber.substring(accountNumber.length() - 4);
    }

    /**
     * Adds a transaction to history
     * @param type Transaction type
     * @param amount Transaction amount
     * @param balanceAfter Balance after transaction
     */
    private void addTransaction(String type, double amount, double balanceAfter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String transaction = String.format("%s | ₹%.2f | Balance: ₹%.2f | %s", 
                                         type, amount, balanceAfter, timestamp);
        transactionHistory.add(transaction);
    }

    /**
     * Returns transaction history
     * @return List of transaction strings
     */
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    /**
     * Returns last N transactions
     * @param count Number of transactions to return
     * @return List of last N transactions
     */
    public List<String> getLastTransactions(int count) {
        int size = transactionHistory.size();
        int fromIndex = Math.max(0, size - count);
        return new ArrayList<>(transactionHistory.subList(fromIndex, size));
    }
}
