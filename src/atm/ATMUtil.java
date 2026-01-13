package atm;

/**
 * Utility class providing helper methods for ATM operations
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMUtil {
    
    /**
     * Formats amount in Indian Rupee format
     * @param amount Amount to format
     * @return Formatted string with currency symbol
     */
    public static String formatCurrency(double amount) {
        return String.format("%s%.2f", ATMConstants.CURRENCY_SYMBOL, amount);
    }
    
    /**
     * Masks account number for security
     * @param accountNumber Account number to mask
     * @return Masked account number
     */
    public static String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 4) {
            return "XXXXX";
        }
        return "XXXXX" + accountNumber.substring(accountNumber.length() - 4);
    }
    
    /**
     * Prints a horizontal line separator
     * @param length Length of the line
     */
    public static void printLine(int length) {
        System.out.println("=".repeat(length));
    }
    
    /**
     * Prints a horizontal line separator with default length
     */
    public static void printLine() {
        printLine(47);
    }
    
    /**
     * Clears console (simulated)
     */
    public static void clearScreen() {
        System.out.println("\n\n\n");
    }
    
    /**
     * Pauses execution for specified milliseconds
     * @param milliseconds Time to pause
     */
    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Validates if string is numeric
     * @param str String to validate
     * @return true if numeric, false otherwise
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Capitalizes first letter of each word
     * @param text Text to capitalize
     * @return Capitalized text
     */
    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        
        return result.toString().trim();
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private ATMUtil() {
        throw new AssertionError("Cannot instantiate utility class");
    }
}
