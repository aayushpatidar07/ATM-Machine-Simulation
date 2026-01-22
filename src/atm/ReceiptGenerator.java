package atm;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Receipt generator for ATM transactions
 * Generates formatted receipts for various transaction types
 * @author ATM Machine Simulation
 * @version 2.0
 * @since 2026-01-22
 */
public class ReceiptGenerator {
    
    private static final int RECEIPT_WIDTH = 40;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final String RECEIPT_LINE = "=".repeat(RECEIPT_WIDTH);
    private static int receiptCounter = 1000;
    
    /**
     * Generates a transaction receipt
     * @param accountNumber Account number
     * @param accountHolder Account holder name
     * @param transactionType Type of transaction
     * @param amount Transaction amount
     * @param balance Balance after transaction
     * @return Formatted receipt string
     */
    public static String generateReceipt(String accountNumber, String accountHolder, 
                                        String transactionType, double amount, double balance) {
        StringBuilder receipt = new StringBuilder();
        String receiptNumber = generateReceiptNumber();
        
        receipt.append("\n");
        printReceiptLine(receipt);
        centerText(receipt, ATMConstants.BANK_NAME);
        centerText(receipt, ATMConstants.BANK_TAGLINE);
        centerText(receipt, "TRANSACTION RECEIPT");
        printReceiptLine(receipt);
        
        receipt.append(String.format("Receipt #: %s\n", receiptNumber));
        receipt.append(String.format("Date/Time: %s\n", LocalDateTime.now().format(formatter)));
        receipt.append(String.format("Account: %s\n", maskAccount(accountNumber)));
        receipt.append(String.format("Name: %s\n", accountHolder));
        printReceiptLine(receipt);
        
        receipt.append(String.format("Transaction: %s\n", transactionType));
        receipt.append(String.format("Amount: %s\n", ATMUtil.formatCurrency(amount)));
        receipt.append(String.format("Balance: %s\n", ATMUtil.formatCurrency(balance)));
        printReceiptLine(receipt);
        
        centerText(receipt, "Thank You!");
        centerText(receipt, "Please keep this receipt");
        printReceiptLine(receipt);
        
        return receipt.toString();
    }
    
    /**
     * Generates a balance inquiry receipt
     * @param accountNumber Account number
     * @param accountHolder Account holder name
     * @param balance Current balance
     * @return Formatted receipt string
     */
    public static String generateBalanceReceipt(String accountNumber, String accountHolder, double balance) {
        StringBuilder receipt = new StringBuilder();
        
        receipt.append("\n");
        printReceiptLine(receipt);
        centerText(receipt, ATMConstants.BANK_NAME);
        centerText(receipt, "BALANCE INQUIRY");
        printReceiptLine(receipt);
        
        receipt.append(String.format("Date/Time: %s\n", LocalDateTime.now().format(formatter)));
        receipt.append(String.format("Account: %s\n", maskAccount(accountNumber)));
        receipt.append(String.format("Name: %s\n", accountHolder));
        printReceiptLine(receipt);
        
        receipt.append(String.format("Available Balance:\n"));
        receipt.append(String.format("  %s\n", ATMUtil.formatCurrency(balance)));
        printReceiptLine(receipt);
        
        centerText(receipt, "Thank You!");
        printReceiptLine(receipt);
        
        return receipt.toString();
    }
    
    /**
     * Prints a line in the receipt
     * @param receipt StringBuilder to append to
     */
    private static void printReceiptLine(StringBuilder receipt) {
        receipt.append("=".repeat(RECEIPT_WIDTH)).append("\n");
    }
    
    /**
     * Centers text in the receipt
     * @param receipt StringBuilder to append to
     * @param text Text to center
     */
    private static void centerText(StringBuilder receipt, String text) {
        int padding = (RECEIPT_WIDTH - text.length()) / 2;
        receipt.append(" ".repeat(Math.max(0, padding)))
               .append(text)
               .append("\n");
    }
    
    /**
     * Masks account number
     * @param accountNumber Account number to mask
     * @return Masked account number
     */
    private static String maskAccount(String accountNumber) {
        return ATMUtil.maskAccountNumber(accountNumber);
    }
    
    /**
     * Generates a unique receipt number
     * @return Receipt number in format RCP-XXXX
     */
    private static String generateReceiptNumber() {
        return String.format("RCP-%04d", receiptCounter++);
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private ReceiptGenerator() {
        throw new AssertionError("Cannot instantiate receipt generator class");
    }
}
