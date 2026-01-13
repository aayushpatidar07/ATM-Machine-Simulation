package atm;

/**
 * Exception class for ATM-related errors
 * Provides specific exception handling for ATM operations
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMException extends Exception {
    
    private String errorCode;
    
    /**
     * Constructor with message
     * @param message Error message
     */
    public ATMException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and error code
     * @param message Error message
     * @param errorCode Error code
     */
    public ATMException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * Constructor with message and cause
     * @param message Error message
     * @param cause Throwable cause
     */
    public ATMException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Gets the error code
     * @return Error code
     */
    public String getErrorCode() {
        return errorCode;
    }
}
