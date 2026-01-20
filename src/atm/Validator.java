package atm;

/**
 * Validator class for input validation in ATM operations
 * Provides comprehensive validation for PINs, amounts, and account numbers
 * @author ATM Machine Simulation
 * @version 1.1
 */
public class Validator {
    
    /**
     * Validates PIN format
     * @param pin PIN to validate
     * @return true if PIN is valid, false otherwise
     */
    public static boolean isValidPin(String pin) {
        // Check for null or empty input
        if (pin == null || pin.isEmpty()) {
            return false;
        }
        
        // Check for whitespace
        if (pin.trim().length() != pin.length()) {
            return false;
        }
        
        if (pin.length() != ATMConstants.PIN_LENGTH) {
            return false;
        }
        
        // Check if PIN contains only digits
        return pin.matches("\\d{" + ATMConstants.PIN_LENGTH + "}");
    }
    
    /**
     * Validates deposit amount
     * @param amount Amount to validate
     * @return true if amount is valid, false otherwise
     */
    public static boolean isValidDepositAmount(double amount) {
        return amount >= ATMConstants.MIN_DEPOSIT_AMOUNT && 
               amount <= ATMConstants.MAX_DEPOSIT_AMOUNT &&
               amount % 100 == 0; // Amount should be in multiples of 100
    }
    
    /**
     * Validates withdrawal amount
     * @param amount Amount to validate
     * @return true if amount is valid, false otherwise
     */
    public static boolean isValidWithdrawalAmount(double amount) {
        return amount >= ATMConstants.MIN_WITHDRAWAL_AMOUNT && 
               amount <= ATMConstants.MAX_WITHDRAWAL_AMOUNT &&
               amount % 100 == 0; // Amount should be in multiples of 100
    }
    
    /**
     * Validates transfer amount
     * @param amount Amount to validate
     * @return true if amount is valid, false otherwise
     */
    public static boolean isValidTransferAmount(double amount) {
        return amount >= ATMConstants.MIN_TRANSFER_AMOUNT && 
               amount <= ATMConstants.MAX_TRANSFER_AMOUNT &&
               amount % 100 == 0;
    }
    
    /**
     * Validates account number format
     * @param accountNumber Account number to validate
     * @return true if account number is valid, false otherwise
     */
    public static boolean isValidAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            return false;
        }
        
        return accountNumber.length() == ATMConstants.ACCOUNT_NUMBER_LENGTH &&
               accountNumber.matches("\\d{" + ATMConstants.ACCOUNT_NUMBER_LENGTH + "}");
    }
    
    /**
     * Validates minimum balance requirement
     * @param currentBalance Current account balance
     * @param withdrawalAmount Amount to withdraw
     * @return true if minimum balance will be maintained, false otherwise
     */
    public static boolean maintainsMinimumBalance(double currentBalance, double withdrawalAmount) {
        return (currentBalance - withdrawalAmount) >= ATMConstants.MIN_BALANCE;
    }
    
    /**
     * Validates menu choice
     * @param choice Menu choice
     * @param minChoice Minimum valid choice
     * @param maxChoice Maximum valid choice
     * @return true if choice is valid, false otherwise
     */
    public static boolean isValidMenuChoice(int choice, int minChoice, int maxChoice) {
        return choice >= minChoice && choice <= maxChoice;
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private Validator() {
        throw new AssertionError("Cannot instantiate validator class");
    }
}
