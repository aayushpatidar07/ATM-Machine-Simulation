package com.atm.util;

/**
 * Utility class for input validation
 */
public class InputValidator {
    
    /**
     * Validates if amount is positive
     */
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }

    /**
     * Validates PIN format (4 digits)
     */
    public static boolean isValidPin(String pin) {
        return pin != null && pin.matches("\\d{4}");
    }

    /**
     * Validates account number format
     */
    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.matches("\\d{9}");
    }

    /**
     * Validates menu choice
     */
    public static boolean isValidMenuChoice(int choice, int min, int max) {
        return choice >= min && choice <= max;
    }
}
