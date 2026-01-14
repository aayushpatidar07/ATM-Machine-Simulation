package atm;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger utility for ATM operations
 * Logs all transactions and system events to a file
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMLogger {
    
    private static final String LOG_FILE = "atm_log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Logs a message to the log file
     * @param level Log level (INFO, ERROR, WARNING)
     * @param message Message to log
     */
    public static void log(String level, String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            
            String timestamp = LocalDateTime.now().format(formatter);
            String logEntry = String.format("[%s] [%s] %s", timestamp, level, message);
            pw.println(logEntry);
            pw.flush(); // Ensure immediate write to disk
            
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    
    /**
     * Logs an INFO level message
     * @param message Message to log
     */
    public static void info(String message) {
        log("INFO", message);
    }
    
    /**
     * Logs an ERROR level message
     * @param message Message to log
     */
    public static void error(String message) {
        log("ERROR", message);
    }
    
    /**
     * Logs a WARNING level message
     * @param message Message to log
     */
    public static void warning(String message) {
        log("WARNING", message);
    }
    
    /**
     * Logs a transaction
     * @param accountNumber Account number
     * @param transactionType Type of transaction
     * @param amount Transaction amount
     * @param success Whether transaction was successful
     */
    public static void logTransaction(String accountNumber, String transactionType, 
                                     double amount, boolean success) {
        String maskedAccount = "XXXXX" + accountNumber.substring(accountNumber.length() - 4);
        String status = success ? "SUCCESS" : "FAILED";
        String message = String.format("Transaction: %s | Account: %s | Amount: %.2f | Status: %s",
                                      transactionType, maskedAccount, amount, status);
        log("TRANSACTION", message);
    }
    
    /**
     * Logs authentication attempt
     * @param accountNumber Account number
     * @param success Whether authentication was successful
     */
    public static void logAuthentication(String accountNumber, boolean success) {
        String maskedAccount = "XXXXX" + accountNumber.substring(accountNumber.length() - 4);
        String status = success ? "SUCCESS" : "FAILED";
        String message = String.format("Authentication attempt | Account: %s | Status: %s",
                                      maskedAccount, status);
        log("AUTH", message);
    }
    
    /**
     * Logs system event
     * @param event Event description
     */
    public static void logEvent(String event) {
        log("EVENT", event);
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private ATMLogger() {
        throw new AssertionError("Cannot instantiate logger class");
    }
}
