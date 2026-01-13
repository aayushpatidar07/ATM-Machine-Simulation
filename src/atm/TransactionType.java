package atm;

/**
 * Enum representing different types of ATM transactions
 * @author ATM Machine Simulation
 * @version 1.0
 */
public enum TransactionType {
    /**
     * Cash deposit transaction
     */
    DEPOSIT("Deposit"),
    
    /**
     * Cash withdrawal transaction
     */
    WITHDRAWAL("Withdrawal"),
    
    /**
     * Balance inquiry transaction
     */
    BALANCE_INQUIRY("Balance Inquiry"),
    
    /**
     * Fund transfer transaction
     */
    TRANSFER("Transfer"),
    
    /**
     * Mini statement request
     */
    MINI_STATEMENT("Mini Statement");

    private final String displayName;

    /**
     * Constructor for TransactionType
     * @param displayName Human-readable name of the transaction type
     */
    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the transaction type
     * @return Display name
     */
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
