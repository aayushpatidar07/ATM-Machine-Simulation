package atm;

/**
 * Constants class containing all ATM configuration values
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMConstants {
    
    // PIN Configuration
    public static final int PIN_LENGTH = 4;
    public static final int MAX_PIN_ATTEMPTS = 3;
    
    // Transaction Limits
    public static final double MIN_DEPOSIT_AMOUNT = 100.0;
    public static final double MAX_DEPOSIT_AMOUNT = 200000.0;
    public static final double MIN_WITHDRAWAL_AMOUNT = 100.0;
    public static final double MAX_WITHDRAWAL_AMOUNT = 50000.0;
    public static final double MIN_TRANSFER_AMOUNT = 100.0;
    public static final double MAX_TRANSFER_AMOUNT = 100000.0;
    
    // Account Configuration
    public static final double MIN_BALANCE = 500.0;
    public static final int ACCOUNT_NUMBER_LENGTH = 9;
    
    // Display Configuration
    public static final String CURRENCY_SYMBOL = "\u20b9";
    public static final String BANK_NAME = "STATE BANK ATM SYSTEM";
    public static final String BANK_TAGLINE = "Serving India Since 1806";
    
    // Transaction History
    public static final int DEFAULT_MINI_STATEMENT_COUNT = 5;
    public static final int MAX_TRANSACTION_HISTORY = 100;
    
    // Session Configuration
    public static final int SESSION_TIMEOUT_MINUTES = 5;
    public static final int INACTIVITY_WARNING_SECONDS = 30;
    
    // Input Validation
    public static final int MAX_INPUT_LENGTH = 50;
    public static final int MAX_NAME_LENGTH = 100;
    
    // Menu Options
    public static final int MENU_BALANCE = 1;
    public static final int MENU_DEPOSIT = 2;
    public static final int MENU_WITHDRAW = 3;
    public static final int MENU_EXIT = 4;
    public static final int MENU_MINI_STATEMENT = 5;
    public static final int MENU_TRANSFER = 6;
    
    // Service Charges
    public static final double ATM_TRANSACTION_CHARGE = 0.0;
    public static final double INTER_BANK_TRANSFER_CHARGE = 5.0;
    
    // Security
    public static final int PASSWORD_EXPIRY_DAYS = 90;
    public static final boolean ENABLE_BIOMETRIC = false;
    
    /**
     * Private constructor to prevent instantiation
     */
    private ATMConstants() {
        throw new AssertionError("Cannot instantiate constants class");
    }
}
