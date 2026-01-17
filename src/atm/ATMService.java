package atm;

/**
 * ATMService class provides business logic for ATM operations
 * Handles deposit, withdrawal, and balance inquiry
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMService {
    private Account account;
    private static final double DAILY_WITHDRAWAL_LIMIT = 50000.0;
    private double dailyWithdrawnAmount = 0.0;
    private boolean isAccountFrozen = false;
    private static final double OVERDRAFT_LIMIT = 5000.0;
    private java.util.List<String> transactionLog = new java.util.ArrayList<>();
    private java.time.LocalDateTime sessionStartTime;
    private static final int SESSION_TIMEOUT_MINUTES = 5;
    private int failedLoginAttempts = 0;
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private int dailyTransactionCount = 0;
    private static final int MAX_DAILY_TRANSACTIONS = 20;
    private static final double MINIMUM_BALANCE_REQUIRED = 500.0;
    private String accountType = "SAVINGS"; // SAVINGS or CURRENT
    private String cardStatus = "ACTIVE"; // ACTIVE, BLOCKED, EXPIRED

    /**
     * Constructor to initialize ATM service with an account
     * @param account Account object associated with this ATM session
     * @throws IllegalArgumentException if account is null
     */
    public ATMService(Account account) {
        this.account = account;
        this.sessionStartTime = java.time.LocalDateTime.now();
    }

    /**
     * Authenticates user with PIN
     * @param pin PIN entered by user
     * @return true if authentication successful, false otherwise
     */
    public boolean authenticate(String pin) {
        if (failedLoginAttempts >= MAX_FAILED_ATTEMPTS) {
            isAccountFrozen = true;
            return false;
        }
        boolean isValid = account.validatePin(pin);
        if (isValid) {
            failedLoginAttempts = 0;
        } else {
            failedLoginAttempts++;
        }
        return isValid;
    }

    /**
     * Gets the number of failed login attempts
     * @return Failed login attempts count
     */
    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    /**
     * Resets failed login attempts counter
     */
    public void resetFailedLoginAttempts() {
        this.failedLoginAttempts = 0;
    }

    /**
     * Returns current account balance
     * @return Current balance in INR
     */
    public double checkBalance() {
        return account.getBalance();
    }

    /**
     * Deposits money into the account
     * @param amount Amount to deposit
     * @return true if deposit successful, false if invalid amount
     */
    public boolean depositMoney(double amount) {
        if (amount <= 0 || dailyTransactionCount >= MAX_DAILY_TRANSACTIONS) {
            return false;
        }
        account.deposit(amount);
        dailyTransactionCount++;
        return true;
    }

    /**
     * Withdraws money from the account
     * @param amount Amount to withdraw
     * @return true if withdrawal successful, false if invalid amount or insufficient balance
     */
    public boolean withdrawMoney(double amount) {
        if (amount <= 0 || dailyTransactionCount >= MAX_DAILY_TRANSACTIONS) {
            return false;
        }
        if (dailyWithdrawnAmount + amount > DAILY_WITHDRAWAL_LIMIT) {
            return false; // Daily limit exceeded
        }
        boolean success = account.withdraw(amount);
        if (success) {
            dailyWithdrawnAmount += amount;
            dailyTransactionCount++;
        }
        return success;
    }

    /**
     * Returns account holder name
     * @return Account holder name
     */
    public String getAccountHolderName() {
        return account.getAccountHolderName();
    }

    /**
     * Returns masked account number
     * @return Masked account number
     */
    public String getMaskedAccountNumber() {
        return account.getMaskedAccountNumber();
    }

    /**
     * Returns mini statement (last N transactions)
     * @param count Number of transactions to retrieve
     * @return List of transaction strings
     */
    public java.util.List<String> getMiniStatement(int count) {
        return account.getLastTransactions(count);
    }

    /**
     * Returns full transaction history
     * @return List of all transaction strings
     */
    public java.util.List<String> getTransactionHistory() {
        return account.getTransactionHistory();
    }

    /**
     * Transfers money to another account
     * @param amount Amount to transfer
     * @param targetAccountNumber Target account number
     * @return true if transfer successful, false otherwise
     */
    public boolean transferMoney(double amount, String targetAccountNumber) {
        if (amount <= 0) {
            return false;
        }
        return account.transfer(amount, targetAccountNumber);
    }

    /**
     * Validates if the amount is within acceptable range
     * @param amount Amount to validate
     * @return true if amount is valid, false otherwise
     */
    private boolean isValidAmount(double amount) {
        return amount > 0 && amount <= 100000;
    }

    /**
     * Calculates transaction fee based on transaction type and amount
     * @param amount Transaction amount
     * @param transactionType Type of transaction (WITHDRAWAL, TRANSFER)
     * @return Calculated fee amount
     */
    public double calculateTransactionFee(double amount, String transactionType) {
        if (transactionType.equals("TRANSFER") && amount > 10000) {
            return amount * 0.01; // 1% fee for large transfers
        }
        return 0.0; // No fee for other transactions
    }

    /**
     * Converts INR amount to foreign currency
     * @param amount Amount in INR
     * @param currency Target currency code (USD, EUR, GBP)
     * @return Converted amount
     */
    public double convertCurrency(double amount, String currency) {
        switch (currency) {
            case "USD": return amount / 83.0;
            case "EUR": return amount / 90.0;
            case "GBP": return amount / 105.0;
            default: return amount;
        }
    }

    /**
     * Checks if the account is frozen
     * @return true if account is frozen, false otherwise
     */
    public boolean isAccountFrozen() {
        return isAccountFrozen;
    }

    /**
     * Freezes or unfreezes the account
     * @param freeze true to freeze, false to unfreeze
     */
    public void setAccountFrozen(boolean freeze) {
        this.isAccountFrozen = freeze;
    }

    /**
     * Generates a transaction receipt
     * @param transactionType Type of transaction
     * @param amount Transaction amount
     * @return Formatted receipt string
     */
    public String generateReceipt(String transactionType, double amount) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("=== TRANSACTION RECEIPT ===\n");
        receipt.append("Account: ").append(getMaskedAccountNumber()).append("\n");
        receipt.append("Type: ").append(transactionType).append("\n");
        receipt.append("Amount: ₹").append(String.format("%.2f", amount)).append("\n");
        receipt.append("Balance: ₹").append(String.format("%.2f", checkBalance())).append("\n");
        receipt.append("Date: ").append(java.time.LocalDateTime.now()).append("\n");
        receipt.append("==========================");
        return receipt.toString();
    }

    /**
     * Changes the account PIN
     * @param oldPin Current PIN
     * @param newPin New PIN to set
     * @return true if PIN change successful, false otherwise
     */
    public boolean changePin(String oldPin, String newPin) {
        if (authenticate(oldPin) && newPin != null && newPin.length() == 4) {
            return account.updatePin(newPin);
        }
        return false;
    }

    /**
     * Checks if withdrawal would exceed overdraft limit
     * @param amount Amount to withdraw
     * @return true if within overdraft limit, false otherwise
     */
    public boolean isWithinOverdraftLimit(double amount) {
        double balanceAfterWithdrawal = checkBalance() - amount;
        return balanceAfterWithdrawal >= -OVERDRAFT_LIMIT;
    }

    /**
     * Logs a transaction with timestamp
     * @param transactionType Type of transaction
     * @param amount Transaction amount
     * @param status Transaction status (SUCCESS/FAILED)
     */
    public void logTransaction(String transactionType, double amount, String status) {
        String logEntry = String.format("[%s] %s: ₹%.2f - %s", 
            java.time.LocalDateTime.now(), transactionType, amount, status);
        transactionLog.add(logEntry);
    }

    /**
     * Retrieves transaction log
     * @return List of logged transactions
     */
    public java.util.List<String> getTransactionLog() {
        return new java.util.ArrayList<>(transactionLog);
    }

    /**
     * Checks if the session has timed out
     * @return true if session has exceeded timeout limit, false otherwise
     */
    public boolean isSessionTimedOut() {
        java.time.Duration duration = java.time.Duration.between(sessionStartTime, java.time.LocalDateTime.now());
        return duration.toMinutes() >= SESSION_TIMEOUT_MINUTES;
    }

    /**
     * Resets session timeout by updating start time
     */
    public void resetSessionTimeout() {
        this.sessionStartTime = java.time.LocalDateTime.now();
    }

    /**
     * Checks if account has minimum required balance
     * @return true if balance meets minimum requirement, false otherwise
     */
    public boolean hasMinimumBalance() {
        return checkBalance() >= MINIMUM_BALANCE_REQUIRED;
    }

    /**
     * Validates withdrawal against minimum balance requirement
     * @param amount Amount to withdraw
     * @return true if withdrawal maintains minimum balance, false otherwise
     */
    public boolean canWithdrawWithMinBalance(double amount) {
        return (checkBalance() - amount) >= MINIMUM_BALANCE_REQUIRED;
    }

    /**
     * Gets the account type
     * @return Account type (SAVINGS or CURRENT)
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the account type
     * @param accountType Account type (SAVINGS or CURRENT)
     */
    public void setAccountType(String accountType) {
        if (accountType.equals("SAVINGS") || accountType.equals("CURRENT")) {
            this.accountType = accountType;
        }
    }

    /**
     * Calculates interest based on account type and balance
     * @return Interest amount
     */
    public double calculateInterest() {
        double balance = checkBalance();
        if (accountType.equals("SAVINGS")) {
            return balance * 0.04; // 4% annual interest for savings
        } else if (accountType.equals("CURRENT")) {
            return 0.0; // No interest for current account
        }
        return 0.0;
    }

    /**
     * Applies calculated interest to account
     * @return true if interest applied successfully
     */
    public boolean applyInterest() {
        double interest = calculateInterest();
        if (interest > 0) {
            account.deposit(interest);
            return true;
        }
        return false;
    }

    /**
     * Checks if card is active and can be used
     * @return true if card is active, false otherwise
     */
    public boolean isCardActive() {
        return cardStatus.equals("ACTIVE");
    }

    /**
     * Gets current card status
     * @return Card status (ACTIVE, BLOCKED, EXPIRED)
     */
    public String getCardStatus() {
        return cardStatus;
    }

    /**
     * Sets card status
     * @param status New card status (ACTIVE, BLOCKED, EXPIRED)
     */
    public void setCardStatus(String status) {
        if (status.equals("ACTIVE") || status.equals("BLOCKED") || status.equals("EXPIRED")) {
            this.cardStatus = status;
        }
    }
}
