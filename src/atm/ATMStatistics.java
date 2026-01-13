package atm;

import java.util.HashMap;
import java.util.Map;

/**
 * Statistics tracker for ATM operations
 * Tracks various metrics and statistics
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMStatistics {
    
    private static ATMStatistics instance;
    
    private int totalTransactions;
    private int successfulTransactions;
    private int failedTransactions;
    private double totalDeposited;
    private double totalWithdrawn;
    private double totalTransferred;
    private Map<String, Integer> transactionTypeCount;
    private int loginAttempts;
    private int failedLoginAttempts;
    
    /**
     * Private constructor for singleton pattern
     */
    private ATMStatistics() {
        this.totalTransactions = 0;
        this.successfulTransactions = 0;
        this.failedTransactions = 0;
        this.totalDeposited = 0.0;
        this.totalWithdrawn = 0.0;
        this.totalTransferred = 0.0;
        this.transactionTypeCount = new HashMap<>();
        this.loginAttempts = 0;
        this.failedLoginAttempts = 0;
    }
    
    /**
     * Gets singleton instance
     * @return ATMStatistics instance
     */
    public static ATMStatistics getInstance() {
        if (instance == null) {
            instance = new ATMStatistics();
        }
        return instance;
    }
    
    /**
     * Records a transaction
     * @param type Transaction type
     * @param amount Transaction amount
     * @param success Whether transaction was successful
     */
    public void recordTransaction(String type, double amount, boolean success) {
        totalTransactions++;
        
        if (success) {
            successfulTransactions++;
            
            switch (type.toUpperCase()) {
                case "DEPOSIT":
                    totalDeposited += amount;
                    break;
                case "WITHDRAWAL":
                    totalWithdrawn += amount;
                    break;
                case "TRANSFER":
                    totalTransferred += amount;
                    break;
            }
            
            transactionTypeCount.put(type, transactionTypeCount.getOrDefault(type, 0) + 1);
        } else {
            failedTransactions++;
        }
    }
    
    /**
     * Records a login attempt
     * @param success Whether login was successful
     */
    public void recordLoginAttempt(boolean success) {
        loginAttempts++;
        if (!success) {
            failedLoginAttempts++;
        }
    }
    
    /**
     * Gets success rate
     * @return Success rate percentage
     */
    public double getSuccessRate() {
        if (totalTransactions == 0) {
            return 0.0;
        }
        return (successfulTransactions * 100.0) / totalTransactions;
    }
    
    /**
     * Prints statistics report
     */
    public void printReport() {
        System.out.println("\n========== ATM STATISTICS REPORT ==========");
        System.out.println("Total Transactions: " + totalTransactions);
        System.out.println("Successful: " + successfulTransactions);
        System.out.println("Failed: " + failedTransactions);
        System.out.println("Success Rate: " + String.format("%.2f%%", getSuccessRate()));
        System.out.println();
        System.out.println("Financial Summary:");
        System.out.println("Total Deposited: " + ATMUtil.formatCurrency(totalDeposited));
        System.out.println("Total Withdrawn: " + ATMUtil.formatCurrency(totalWithdrawn));
        System.out.println("Total Transferred: " + ATMUtil.formatCurrency(totalTransferred));
        System.out.println();
        System.out.println("Login Statistics:");
        System.out.println("Total Login Attempts: " + loginAttempts);
        System.out.println("Failed Login Attempts: " + failedLoginAttempts);
        System.out.println("===========================================\n");
    }
    
    /**
     * Resets all statistics
     */
    public void reset() {
        totalTransactions = 0;
        successfulTransactions = 0;
        failedTransactions = 0;
        totalDeposited = 0.0;
        totalWithdrawn = 0.0;
        totalTransferred = 0.0;
        transactionTypeCount.clear();
        loginAttempts = 0;
        failedLoginAttempts = 0;
    }
    
    // Getters
    
    public int getTotalTransactions() {
        return totalTransactions;
    }
    
    public int getSuccessfulTransactions() {
        return successfulTransactions;
    }
    
    public int getFailedTransactions() {
        return failedTransactions;
    }
    
    public double getTotalDeposited() {
        return totalDeposited;
    }
    
    public double getTotalWithdrawn() {
        return totalWithdrawn;
    }
    
    public double getTotalTransferred() {
        return totalTransferred;
    }
}
