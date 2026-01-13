package atm;

/**
 * Security utilities for ATM operations
 * Handles security-related validations and checks
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class SecurityUtil {
    
    private static final int MAX_DAILY_WITHDRAWAL_LIMIT = 50000;
    private static final int MAX_DAILY_TRANSFER_LIMIT = 100000;
    
    /**
     * Validates PIN strength
     * @param pin PIN to validate
     * @return true if PIN is strong enough
     */
    public static boolean isStrongPin(String pin) {
        if (pin == null || pin.length() != ATMConstants.PIN_LENGTH) {
            return false;
        }
        
        // Check for sequential numbers (1234, 4321)
        if (isSequential(pin)) {
            return false;
        }
        
        // Check for repeating digits (1111, 2222)
        if (hasAllSameDigits(pin)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if PIN contains sequential digits
     * @param pin PIN to check
     * @return true if sequential
     */
    private static boolean isSequential(String pin) {
        boolean ascending = true;
        boolean descending = true;
        
        for (int i = 0; i < pin.length() - 1; i++) {
            int current = Character.getNumericValue(pin.charAt(i));
            int next = Character.getNumericValue(pin.charAt(i + 1));
            
            if (next != current + 1) {
                ascending = false;
            }
            if (next != current - 1) {
                descending = false;
            }
        }
        
        return ascending || descending;
    }
    
    /**
     * Checks if all digits in PIN are the same
     * @param pin PIN to check
     * @return true if all digits are same
     */
    private static boolean hasAllSameDigits(String pin) {
        char first = pin.charAt(0);
        for (int i = 1; i < pin.length(); i++) {
            if (pin.charAt(i) != first) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Validates if withdrawal is within daily limit
     * @param amount Amount to withdraw
     * @param todaysTotalWithdrawal Total withdrawn today
     * @return true if within limit
     */
    public static boolean isWithinDailyWithdrawalLimit(double amount, double todaysTotalWithdrawal) {
        return (todaysTotalWithdrawal + amount) <= MAX_DAILY_WITHDRAWAL_LIMIT;
    }
    
    /**
     * Validates if transfer is within daily limit
     * @param amount Amount to transfer
     * @param todaysTotalTransfer Total transferred today
     * @return true if within limit
     */
    public static boolean isWithinDailyTransferLimit(double amount, double todaysTotalTransfer) {
        return (todaysTotalTransfer + amount) <= MAX_DAILY_TRANSFER_LIMIT;
    }
    
    /**
     * Encrypts PIN (placeholder - uses simple masking)
     * In production, use proper encryption
     * @param pin PIN to encrypt
     * @return Encrypted PIN
     */
    public static String encryptPin(String pin) {
        // Placeholder - in production, use proper encryption like BCrypt
        return "****";
    }
    
    /**
     * Generates random session token
     * @return Session token
     */
    public static String generateSessionToken() {
        return "TOKEN_" + System.currentTimeMillis() + "_" + 
               (int)(Math.random() * 10000);
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private SecurityUtil() {
        throw new AssertionError("Cannot instantiate security utility class");
    }
}
