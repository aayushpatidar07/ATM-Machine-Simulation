package atm;

/**
 * Security utilities for ATM operations
 * Handles security-related validations and checks
 * Provides PIN strength validation and fraud detection
 * @author ATM Machine Simulation
 * @version 1.2
 */
public class SecurityUtil {
    
    private static final int MAX_DAILY_WITHDRAWAL_LIMIT = 50000;
    private static final int MAX_DAILY_TRANSFER_LIMIT = 100000;
    private static final int SESSION_TIMEOUT_MINUTES = 5;
    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final int SUSPICIOUS_TRANSACTION_THRESHOLD = 5;
    
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
     * Simulates biometric authentication
     * @param fingerprintData Fingerprint data
     * @return true if authentication successful
     */
    public static boolean authenticateBiometric(String fingerprintData) {
        // Placeholder for biometric authentication
        // In production, integrate with actual biometric hardware/API
        return fingerprintData != null && fingerprintData.length() > 0;
    }
    
    /**
     * Validates device fingerprint for security
     * @param deviceId Device identifier
     * @param registeredDevices List of registered device IDs
     * @return true if device is recognized
     */
    public static boolean isRegisteredDevice(String deviceId, String[] registeredDevices) {
        if (deviceId == null || registeredDevices == null) {
            return false;
        }
        
        for (String device : registeredDevices) {
            if (deviceId.equals(device)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Detects suspicious transaction patterns
     * @param transactionCount Number of transactions in short period
     * @return true if activity is suspicious
     */
    public static boolean isSuspiciousActivity(int transactionCount) {
        return transactionCount > SUSPICIOUS_TRANSACTION_THRESHOLD;
    }
    
    /**
     * Validates account lock status based on failed attempts
     * @param failedAttempts Number of failed login attempts
     * @return true if account should be locked
     */
    public static boolean shouldLockAccount(int failedAttempts) {
        return failedAttempts >= MAX_LOGIN_ATTEMPTS;
    }
    
    /**
     * Generates one-time password (OTP) for two-factor authentication
     * @return 6-digit OTP
     */
    public static String generateOTP() {
        int otp = (int)(Math.random() * 900000) + 100000;
        return String.valueOf(otp);
    }
    
    /**
     * Validates OTP entered by user
     * @param enteredOTP OTP entered by user
     * @param generatedOTP OTP that was generated
     * @return true if OTP matches
     */
    public static boolean validateOTP(String enteredOTP, String generatedOTP) {
        return enteredOTP != null && enteredOTP.equals(generatedOTP);
    }
    
    /**
     * Detects unusual transaction amount
     * @param amount Transaction amount
     * @param averageAmount User's average transaction amount
     * @return true if amount is unusually high
     */
    public static boolean isUnusualTransactionAmount(double amount, double averageAmount) {
        // Flag if transaction is 5x the average or more
        return amount >= (averageAmount * 5) && averageAmount > 0;
    }
    
    /**
     * Validates transaction timing for fraud detection
     * @param hour Hour of day (0-23)
     * @return true if transaction timing is unusual (late night/early morning)
     */
    public static boolean isUnusualTransactionTime(int hour) {
        // Transactions between 2 AM and 5 AM are considered unusual
        return hour >= 2 && hour < 5;
    }
    
    /**
     * Checks if multiple rapid transactions indicate fraud
     * @param transactionIntervalSeconds Seconds between transactions
     * @return true if transactions are too rapid
     */
    public static boolean isRapidTransaction(long transactionIntervalSeconds) {
        // Flag if transactions are less than 10 seconds apart
        return transactionIntervalSeconds < 10;
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private SecurityUtil() {
        throw new AssertionError("Cannot instantiate security utility class");
    }
}
