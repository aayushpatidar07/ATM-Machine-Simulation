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

    /**
     * Constructor to initialize ATM service with an account
     * @param account Account object associated with this ATM session
     * @throws IllegalArgumentException if account is null
     */
    public ATMService(Account account) {
        this.account = account;
    }

    /**
     * Authenticates user with PIN
     * @param pin PIN entered by user
     * @return true if authentication successful, false otherwise
     */
    public boolean authenticate(String pin) {
        return account.validatePin(pin);
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
        if (amount <= 0) {
            return false;
        }
        account.deposit(amount);
        return true;
    }

    /**
     * Withdraws money from the account
     * @param amount Amount to withdraw
     * @return true if withdrawal successful, false if invalid amount or insufficient balance
     */
    public boolean withdrawMoney(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (dailyWithdrawnAmount + amount > DAILY_WITHDRAWAL_LIMIT) {
            return false; // Daily limit exceeded
        }
        boolean success = account.withdraw(amount);
        if (success) {
            dailyWithdrawnAmount += amount;
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
}
