package atm;

/**
 * Currency formatter utility for ATM operations
 * Handles currency formatting and conversions
 * Supports Indian numbering system and word conversion
 * @author ATM Machine Simulation
 * @version 1.1
 */
public class CurrencyFormatter {
    
    private static final String[] UNITS = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
    };
    
    private static final String[] TEENS = {
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    
    private static final String[] TENS = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    
    /**
     * Formats amount with Indian comma notation
     * @param amount Amount to format
     * @return Formatted string (e.g., 1,50,000.00)
     */
    public static String formatIndianCurrency(double amount) {
        String amountStr = String.format("%.2f", amount);
        String[] parts = amountStr.split("\\.");
        String integerPart = parts[0];
        String decimalPart = parts.length > 1 ? parts[1] : "00";
        
        StringBuilder formatted = new StringBuilder();
        int len = integerPart.length();
        
        if (len > 3) {
            formatted.insert(0, integerPart.substring(len - 3));
            int remaining = len - 3;
            
            while (remaining > 0) {
                int start = Math.max(0, remaining - 2);
                formatted.insert(0, "," + integerPart.substring(start, remaining));
                remaining = start;
            }
        } else {
            formatted.append(integerPart);
        }
        
        return ATMConstants.CURRENCY_SYMBOL + formatted + "." + decimalPart;
    }
    
    /**
     * Converts amount to words (Indian format)
     * @param amount Amount to convert
     * @return Amount in words with proper formatting
     */
    public static String toWords(double amount) {
        if (amount == 0) {
            return "Zero Rupees Only";
        }
        
        if (amount < 0) {
            return "Invalid Amount";
        }
        
        long rupees = (long) amount;
        int paise = (int) Math.round((amount - rupees) * 100);
        
        StringBuilder result = new StringBuilder();
        
        // Convert rupees part
        if (rupees > 0) {
            result.append(convertToWords(rupees));
            result.append(" Rupee");
            if (rupees > 1) {
                result.append("s");
            }
        }
        
        // Convert paise part
        if (paise > 0) {
            if (rupees > 0) {
                result.append(" and ");
            }
            result.append(convertToWords(paise));
            result.append(" Paise");
        }
        
        result.append(" Only");
        return result.toString();
    }
    
    /**
     * Converts number to words
     * @param number Number to convert
     * @return Number in words
     */
    private static String convertToWords(long number) {
        if (number == 0) {
            return "";
        }
        
        if (number < 10) {
            return UNITS[(int) number];
        }
        
        if (number < 20) {
            return TEENS[(int) (number - 10)];
        }
        
        if (number < 100) {
            return TENS[(int) (number / 10)] + 
                   (number % 10 != 0 ? " " + UNITS[(int) (number % 10)] : "");
        }
        
        if (number < 1000) {
            return UNITS[(int) (number / 100)] + " Hundred" +
                   (number % 100 != 0 ? " " + convertToWords(number % 100) : "");
        }
        
        if (number < 100000) {
            return convertToWords(number / 1000) + " Thousand" +
                   (number % 1000 != 0 ? " " + convertToWords(number % 1000) : "");
        }
        
        if (number < 10000000) {
            return convertToWords(number / 100000) + " Lakh" +
                   (number % 100000 != 0 ? " " + convertToWords(number % 100000) : "");
        }
        
        return convertToWords(number / 10000000) + " Crore" +
               (number % 10000000 != 0 ? " " + convertToWords(number % 10000000) : "");
    }
    
    /**
     * Formats amount with Indian numbering system
     * @param amount Amount to format
     * @return Formatted string
     */
    public static String formatIndianStyle(double amount) {
        String[] parts = String.format("%.2f", amount).split("\\.");
        String intPart = parts[0];
        String decPart = parts[1];
        
        StringBuilder formatted = new StringBuilder();
        int len = intPart.length();
        
        for (int i = 0; i < len; i++) {
            formatted.append(intPart.charAt(i));
            int pos = len - i - 1;
            
            if (pos > 0 && pos % 2 == 0 && i < len - 1 && pos != 2) {
                formatted.append(",");
            } else if (pos == 3) {
                formatted.append(",");
            }
        }
        
        return ATMConstants.CURRENCY_SYMBOL + formatted.toString() + "." + decPart;
    }
    
    /**
     * Private constructor to prevent instantiation
     */
    private CurrencyFormatter() {
        throw new AssertionError("Cannot instantiate currency formatter class");
    }
}
