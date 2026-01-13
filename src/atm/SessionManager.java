package atm;

/**
 * Session manager for tracking ATM user sessions
 * Manages session lifecycle and timeout
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class SessionManager {
    
    private static SessionManager instance;
    
    private String sessionId;
    private String accountNumber;
    private long sessionStartTime;
    private long lastActivityTime;
    private boolean isActive;
    
    /**
     * Private constructor for singleton pattern
     */
    private SessionManager() {
        this.isActive = false;
    }
    
    /**
     * Gets singleton instance
     * @return SessionManager instance
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    /**
     * Starts a new session
     * @param accountNumber Account number
     * @return Session ID
     */
    public String startSession(String accountNumber) {
        this.accountNumber = accountNumber;
        this.sessionId = generateSessionId();
        this.sessionStartTime = System.currentTimeMillis();
        this.lastActivityTime = System.currentTimeMillis();
        this.isActive = true;
        
        ATMLogger.info("Session started: " + sessionId + " for account: " + 
                      ATMUtil.maskAccountNumber(accountNumber));
        
        return sessionId;
    }
    
    /**
     * Ends the current session
     */
    public void endSession() {
        if (isActive) {
            long duration = (System.currentTimeMillis() - sessionStartTime) / 1000;
            ATMLogger.info("Session ended: " + sessionId + " Duration: " + duration + " seconds");
            
            this.isActive = false;
            this.sessionId = null;
            this.accountNumber = null;
        }
    }
    
    /**
     * Updates last activity time
     */
    public void updateActivity() {
        this.lastActivityTime = System.currentTimeMillis();
    }
    
    /**
     * Checks if session has timed out
     * @return true if session has timed out
     */
    public boolean hasTimedOut() {
        if (!isActive) {
            return false;
        }
        
        long idleTime = (System.currentTimeMillis() - lastActivityTime) / 1000;
        int timeout = ATMConfig.getInstance().getSessionTimeout();
        
        return idleTime > timeout;
    }
    
    /**
     * Gets session duration in seconds
     * @return Session duration
     */
    public long getSessionDuration() {
        if (!isActive) {
            return 0;
        }
        return (System.currentTimeMillis() - sessionStartTime) / 1000;
    }
    
    /**
     * Generates a unique session ID
     * @return Session ID
     */
    private String generateSessionId() {
        return "SES" + System.currentTimeMillis();
    }
    
    // Getters
    
    public String getSessionId() {
        return sessionId;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public long getLastActivityTime() {
        return lastActivityTime;
    }
}
