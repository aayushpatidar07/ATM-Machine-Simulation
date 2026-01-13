package atm;

/**
 * ATMService class provides business logic for ATM operations
 * Handles deposit, withdrawal, and balance inquiry
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMService {
    private Account account;

    /**
     * Constructor to initialize ATM service with an accounts
     * @param account Account object
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
        return account.withdraw(amount);
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
}
