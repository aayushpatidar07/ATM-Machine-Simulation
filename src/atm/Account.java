package atm;

/**
 * Account class represents a bank account with account holder details and balance
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;

    /**
     * Constructor to initialize account with default values
     */
    public Account(String accountNumber, String accountHolderName, double balance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = pin;
    }

    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    // Setter methods
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Validates the entered PIN
     * @param inputPin PIN entered by user
     * @return true if PIN matches, false otherwise
     */
    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    /**
     * Deposits amount to the account
     * @param amount Amount to deposit
     */
    public void deposit(double amount) {
        this.balance += amount;
    }

    /**
     * Withdraws amount from the account
     * @param amount Amount to withdraw
     * @return true if withdrawal successful, false if insufficient balance
     */
    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Returns masked account number for security
     * @return Masked account number
     */
    public String getMaskedAccountNumber() {
        return "XXXXX" + accountNumber.substring(accountNumber.length() - 4);
    }
}
