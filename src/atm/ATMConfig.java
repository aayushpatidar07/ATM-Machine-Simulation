package atm;

/**
 * Configuration class for ATM system settings
 * Stores runtime configuration and preferences
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATMConfig {
    
    // Singleton instance
    private static ATMConfig instance;
    
    // Configuration settings
    private boolean enableLogging;
    private boolean printReceipts;
    private boolean enableSoundEffects;
    private int sessionTimeout; // in seconds
    private String language;
    
    /**
     * Private constructor for singleton pattern
     * Initializes default configuration values
     */
    private ATMConfig() {
        // Default configuration values
        this.enableLogging = true;        // Enable transaction logging
        this.printReceipts = true;        // Auto-print receipts
        this.enableSoundEffects = false;  // Disable sounds by default
        this.sessionTimeout = 300;        // 5 minutes session timeout
        this.language = "English";        // Default language
    }
    
    /**
     * Gets singleton instance
     * @return ATMConfig instance
     */
    public static ATMConfig getInstance() {
        if (instance == null) {
            instance = new ATMConfig();
        }
        return instance;
    }
    
    // Getters and Setters
    
    public boolean isLoggingEnabled() {
        return enableLogging;
    }
    
    public void setEnableLogging(boolean enableLogging) {
        this.enableLogging = enableLogging;
    }
    
    public boolean isPrintReceiptsEnabled() {
        return printReceipts;
    }
    
    public void setPrintReceipts(boolean printReceipts) {
        this.printReceipts = printReceipts;
    }
    
    public boolean isSoundEffectsEnabled() {
        return enableSoundEffects;
    }
    
    public void setEnableSoundEffects(boolean enableSoundEffects) {
        this.enableSoundEffects = enableSoundEffects;
    }
    
    public int getSessionTimeout() {
        return sessionTimeout;
    }
    
    public void setSessionTimeout(int sessionTimeout) {
        if (sessionTimeout > 0) {
            this.sessionTimeout = sessionTimeout;
        }
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    /**
     * Loads configuration from file (placeholder)
     */
    public void loadConfig() {
        // Placeholder for loading config from file
        ATMLogger.info("Configuration loaded");
    }
    
    /**
     * Saves configuration to file (placeholder)
     */
    public void saveConfig() {
        // Placeholder for saving config to file
        ATMLogger.info("Configuration saved");
    }
    
    /**
     * Resets configuration to defaults
     */
    public void resetToDefaults() {
        this.enableLogging = true;
        this.printReceipts = true;
        this.enableSoundEffects = false;
        this.sessionTimeout = 300;
        this.language = "English";
        ATMLogger.info("Configuration reset to defaults");
    }
}
