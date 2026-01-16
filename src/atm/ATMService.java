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
}
