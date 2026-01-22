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
     * @param accountNumber The account number
     * @param accountHolderName Name of the account holder
     * @param balance Initial balance
     * @param pin 4-digit PIN for authentication
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Account(String accountNumber, String accountHolderName, double balance, String pin) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (accountHolderName == null || accountHolderName.isEmpty()) {
            throw new IllegalArgumentException("Account holder name cannot be null or empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        if (pin == null || pin.length() != 4) {
            throw new IllegalArgumentException("PIN must be 4 digits");
        }
        
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

    /**
     * Transfers money to another account
     * @param amount Amount to transfer
     * @param targetAccountNumber Target account number
     * @return true if transfer successful, false otherwise
     */
    public boolean transfer(double amount, String targetAccountNumber) {
        if (amount <= this.balance) {
            this.balance -= amount;
            addTransaction("TRANSFER OUT to " + maskAccountNumber(targetAccountNumber), amount, this.balance);
            return true;
        }
        return false;
    }

    /**
     * Receives transferred money
     * @param amount Amount to receive
     * @param sourceAccountNumber Source account number
     */
    public void receiveTransfer(double amount, String sourceAccountNumber) {
        this.balance += amount;
        addTransaction("TRANSFER IN from " + maskAccountNumber(sourceAccountNumber), amount, this.balance);
    }

    /**
     * Masks account number for display
     * @param accountNum Account number to mask
     * @return Masked account number
     */
    private String maskAccountNumber(String accountNum) {
        if (accountNum == null || accountNum.length() < 4) {
            return "XXXXX";
        }
        return "XXXXX" + accountNum.substring(accountNum.length() - 4);
    }
}
