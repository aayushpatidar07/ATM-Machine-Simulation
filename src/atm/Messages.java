package atm;

/**
 * Error messages and user notifications for ATM operations
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class Messages {
    
    // Authentication Messages
    public static final String MSG_AUTH_REQUIRED = ">>> AUTHENTICATION REQUIRED <<<";
    public static final String MSG_ENTER_PIN = "Enter your 4-digit PIN: ";
    public static final String MSG_PIN_SUCCESS = "\n[SUCCESS] PIN verified successfully!";
    public static final String MSG_PIN_INCORRECT = "[X] Incorrect PIN! You have %d attempt(s) remaining.";
    public static final String MSG_CARD_BLOCKED = "\n[X] Maximum attempts exceeded! Card blocked for security.";
    public static final String MSG_CONTACT_BANK = "Please contact your bank.";
    
    // Welcome Messages
    public static final String MSG_WELCOME_HEADER = "===============================================";
    public static final String MSG_WELCOME_TITLE = "    WELCOME TO " + ATMConstants.BANK_NAME;
    public static final String MSG_WELCOME_TAGLINE = "          " + ATMConstants.BANK_TAGLINE;
    public static final String MSG_WELCOME_USER = "Welcome, %s!";
    public static final String MSG_ACCOUNT_NUMBER = "Account Number: %s";
    
    // Transaction Messages
    public static final String MSG_TRANSACTION_SUCCESS = "[SUCCESS] Transaction completed successfully!";
    public static final String MSG_DEPOSIT_SUCCESS = "[SUCCESS] " + ATMConstants.CURRENCY_SYMBOL + "%.2f deposited successfully!";
    public static final String MSG_WITHDRAWAL_SUCCESS = "[SUCCESS] Please collect " + ATMConstants.CURRENCY_SYMBOL + "%.2f";
    public static final String MSG_TRANSFER_SUCCESS = "[SUCCESS] " + ATMConstants.CURRENCY_SYMBOL + "%.2f transferred successfully!";
    
    // Error Messages
    public static final String MSG_INSUFFICIENT_BALANCE = "[X] Insufficient balance! Transaction cancelled.";
    public static final String MSG_INVALID_AMOUNT = "[X] Invalid amount! Please enter a valid amount.";
    public static final String MSG_AMOUNT_MULTIPLE_100 = "[X] Amount must be in multiples of " + ATMConstants.CURRENCY_SYMBOL + "100";
    public static final String MSG_AMOUNT_TOO_LOW = "[X] Amount too low! Minimum: " + ATMConstants.CURRENCY_SYMBOL + "%.2f";
    public static final String MSG_AMOUNT_TOO_HIGH = "[X] Amount too high! Maximum: " + ATMConstants.CURRENCY_SYMBOL + "%.2f";
    public static final String MSG_MIN_BALANCE_ERROR = "[X] Cannot withdraw! Minimum balance of " + ATMConstants.CURRENCY_SYMBOL + "%.2f must be maintained.";
    public static final String MSG_INVALID_ACCOUNT = "[X] Invalid account number! Please check and try again.";
    public static final String MSG_INVALID_PIN_FORMAT = "[X] Invalid PIN format! PIN must be 4 digits.";
    
    // Menu Messages
    public static final String MSG_MENU_HEADER = "\n============== MAIN MENU ==============";
    public static final String MSG_MENU_BALANCE = "1. Check Balance";
    public static final String MSG_MENU_DEPOSIT = "2. Deposit Money";
    public static final String MSG_MENU_WITHDRAW = "3. Withdraw Money";
    public static final String MSG_MENU_MINI_STATEMENT = "4. Mini Statement";
    public static final String MSG_MENU_TRANSFER = "5. Transfer Money";
    public static final String MSG_MENU_EXIT = "6. Exit";
    public static final String MSG_MENU_CHOICE = "Enter your choice (1-6): ";
    public static final String MSG_INVALID_CHOICE = "[X] Invalid choice! Please select a valid option.";
    
    // Balance Messages
    public static final String MSG_CURRENT_BALANCE = "Current Balance: " + ATMConstants.CURRENCY_SYMBOL + "%.2f";
    public static final String MSG_UPDATED_BALANCE = "Updated Balance: " + ATMConstants.CURRENCY_SYMBOL + "%.2f";
    
    // Input Prompts
    public static final String MSG_ENTER_DEPOSIT_AMOUNT = "Enter deposit amount: " + ATMConstants.CURRENCY_SYMBOL;
    public static final String MSG_ENTER_WITHDRAWAL_AMOUNT = "Enter withdrawal amount: " + ATMConstants.CURRENCY_SYMBOL;
    public static final String MSG_ENTER_TRANSFER_AMOUNT = "Enter transfer amount: " + ATMConstants.CURRENCY_SYMBOL;
    public static final String MSG_ENTER_ACCOUNT_NUMBER = "Enter target account number: ";
    
    // Exit Messages
    public static final String MSG_THANK_YOU = "\nThank you for using " + ATMConstants.BANK_NAME + "!";
    public static final String MSG_GOODBYE = "Have a great day!";
    public static final String MSG_TAKE_CARD = "Please take your card.";
    
    // Mini Statement
    public static final String MSG_MINI_STATEMENT_HEADER = "\n========== MINI STATEMENT ==========";
    public static final String MSG_NO_TRANSACTIONS = "No transactions found.";
    public static final String MSG_RECENT_TRANSACTIONS = "Recent Transactions:";
    
    /**
     * Private constructor to prevent instantiation
     */
    private Messages() {
        throw new AssertionError("Cannot instantiate messages class");
    }
}
