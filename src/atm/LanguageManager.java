package atm;

/**
 * LanguageManager class provides multi-language support for ATM application
 * Supports English and Hindi languages
 * 
 * @author ATM Machine Simulation Team
 * @version 1.0
 * @since 2026-01-23
 */
public class LanguageManager {
    
    public enum Language {
        ENGLISH,
        HINDI
    }
    
    private static Language currentLanguage = Language.ENGLISH;
    
    /**
     * Sets the current language
     * @param language Language to set
     */
    public static void setLanguage(Language language) {
        currentLanguage = language;
    }
    
    /**
     * Gets the current language
     * @return Current language
     */
    public static Language getCurrentLanguage() {
        return currentLanguage;
    }
    
    // Welcome Messages
    public static String getWelcomeTitle() {
        return currentLanguage == Language.HINDI 
            ? "     ★★★ स्वागत है एटीएम में ★★★     "
            : "     ★★★ WELCOME TO ATM ★★★     ";
    }
    
    public static String getWelcomeTagline() {
        return currentLanguage == Language.HINDI
            ? "     आपकी सेवा में सदैव तत्पर     "
            : "     Your Trusted Banking Partner     ";
    }
    
    // Menu Messages
    public static String getMainMenuTitle() {
        return currentLanguage == Language.HINDI
            ? "              मुख्य मेनू                    "
            : "              ATM MAIN MENU                    ";
    }
    
    public static String getMenuCheckBalance() {
        return currentLanguage == Language.HINDI
            ? "  1. बैलेंस जांचें"
            : "  1. Check Balance";
    }
    
    public static String getMenuDeposit() {
        return currentLanguage == Language.HINDI
            ? "  2. पैसे जमा करें"
            : "  2. Deposit Money";
    }
    
    public static String getMenuWithdraw() {
        return currentLanguage == Language.HINDI
            ? "  3. पैसे निकालें"
            : "  3. Withdraw Money";
    }
    
    public static String getMenuChangePin() {
        return currentLanguage == Language.HINDI
            ? "  4. पिन बदलें"
            : "  4. Change PIN";
    }
    
    public static String getMenuExportStatement() {
        return currentLanguage == Language.HINDI
            ? "  5. स्टेटमेंट निर्यात करें"
            : "  5. Export Statement";
    }
    
    public static String getMenuChangeLanguage() {
        return currentLanguage == Language.HINDI
            ? "  6. भाषा बदलें"
            : "  6. Change Language";
    }
    
    public static String getMenuExit() {
        return currentLanguage == Language.HINDI
            ? "  7. बाहर निकलें"
            : "  7. Exit";
    }
    
    public static String getEnterChoice() {
        return currentLanguage == Language.HINDI
            ? "अपनी पसंद दर्ज करें (1-7): "
            : "Enter your choice (1-7): ";
    }
    
    // Authentication Messages
    public static String getEnterPin() {
        return currentLanguage == Language.HINDI
            ? "कृपया अपना पिन दर्ज करें: "
            : "Please enter your PIN: ";
    }
    
    public static String getPinSuccess() {
        return currentLanguage == Language.HINDI
            ? "\n✓ सत्यापन सफल!"
            : "\n✓ Authentication Successful!";
    }
    
    public static String getPinIncorrect(int attemptsLeft) {
        return currentLanguage == Language.HINDI
            ? "[X] गलत पिन! बची हुई कोशिशें: " + attemptsLeft
            : "[X] Incorrect PIN! Attempts remaining: " + attemptsLeft;
    }
    
    public static String getWelcomeUser(String name) {
        return currentLanguage == Language.HINDI
            ? "स्वागत है, " + name + " जी!"
            : "Welcome, " + name + "!";
    }
    
    // Transaction Messages
    public static String getBalanceInquiry() {
        return currentLanguage == Language.HINDI
            ? "           बैलेंस पूछताछ                     "
            : "           BALANCE INQUIRY                     ";
    }
    
    public static String getCurrentBalance() {
        return currentLanguage == Language.HINDI
            ? "  वर्तमान बैलेंस: रु. "
            : "  Current Balance: Rs. ";
    }
    
    public static String getCashDeposit() {
        return currentLanguage == Language.HINDI
            ? "           नकद जमा                        "
            : "           CASH DEPOSIT                        ";
    }
    
    public static String getEnterDepositAmount() {
        return currentLanguage == Language.HINDI
            ? "  जमा करने की राशि दर्ज करें (रु.): "
            : "  Enter amount to deposit (Rs.): ";
    }
    
    public static String getDepositSuccess() {
        return currentLanguage == Language.HINDI
            ? "\n  [सफल] राशि सफलतापूर्वक जमा हो गई!"
            : "\n  [SUCCESS] Amount deposited successfully!";
    }
    
    public static String getDepositedAmount() {
        return currentLanguage == Language.HINDI
            ? "  जमा राशि: रु. "
            : "  Deposited Amount: Rs. ";
    }
    
    public static String getCashWithdrawal() {
        return currentLanguage == Language.HINDI
            ? "           नकद निकासी                     "
            : "           CASH WITHDRAWAL                     ";
    }
    
    public static String getEnterWithdrawAmount() {
        return currentLanguage == Language.HINDI
            ? "  निकालने की राशि दर्ज करें (रु.): "
            : "  Enter amount to withdraw (Rs.): ";
    }
    
    public static String getWithdrawSuccess() {
        return currentLanguage == Language.HINDI
            ? "\n  [सफल] कृपया अपना नकद लें!"
            : "\n  [SUCCESS] Please collect your cash!";
    }
    
    public static String getWithdrawnAmount() {
        return currentLanguage == Language.HINDI
            ? "  निकाली गई राशि: रु. "
            : "  Withdrawn Amount: Rs. ";
    }
    
    public static String getRemainingBalance() {
        return currentLanguage == Language.HINDI
            ? "  शेष बैलेंस: रु. "
            : "  Remaining Balance: Rs. ";
    }
    
    public static String getUpdatedBalance() {
        return currentLanguage == Language.HINDI
            ? "  अपडेट बैलेंस: रु. "
            : "  Updated Balance: Rs. ";
    }
    
    public static String getTransactionFailed() {
        return currentLanguage == Language.HINDI
            ? "\n  [X] लेन-देन विफल!"
            : "\n  [X] Transaction failed!";
    }
    
    public static String getInvalidAmount() {
        return currentLanguage == Language.HINDI
            ? "  [X] अमान्य राशि! राशि शून्य से अधिक होनी चाहिए।"
            : "  [X] Invalid amount! Amount must be greater than zero.";
    }
    
    public static String getInsufficientBalance() {
        return currentLanguage == Language.HINDI
            ? "  कारण: आपके खाते में अपर्याप्त बैलेंस।"
            : "  Reason: Insufficient balance in your account.";
    }
    
    // PIN Change Messages
    public static String getChangePinTitle() {
        return currentLanguage == Language.HINDI
            ? "           पिन बदलें                          "
            : "           CHANGE PIN                          ";
    }
    
    public static String getEnterCurrentPin() {
        return currentLanguage == Language.HINDI
            ? "  वर्तमान पिन दर्ज करें: "
            : "  Enter current PIN: ";
    }
    
    public static String getEnterNewPin() {
        return currentLanguage == Language.HINDI
            ? "  नया पिन दर्ज करें (4 अंक): "
            : "  Enter new PIN (4 digits): ";
    }
    
    public static String getConfirmNewPin() {
        return currentLanguage == Language.HINDI
            ? "  नए पिन की पुष्टि करें: "
            : "  Confirm new PIN: ";
    }
    
    public static String getPinMismatch() {
        return currentLanguage == Language.HINDI
            ? "\n  [X] पिन मेल नहीं खाते! कृपया पुनः प्रयास करें।"
            : "\n  [X] PINs do not match! Please try again.";
    }
    
    public static String getPinChangeSuccess() {
        return currentLanguage == Language.HINDI
            ? "\n  [सफल] पिन सफलतापूर्वक बदल गया!"
            : "\n  [SUCCESS] PIN changed successfully!";
    }
    
    public static String getPinChangeFailed() {
        return currentLanguage == Language.HINDI
            ? "\n  [X] पिन बदलना विफल!"
            : "\n  [X] PIN change failed!";
    }
    
    // Exit Messages
    public static String getThankYou() {
        return currentLanguage == Language.HINDI
            ? "    एटीएम सेवा का उपयोग करने के लिए धन्यवाद!       "
            : "    Thank you for using State Bank ATM!       ";
    }
    
    public static String getCollectCard() {
        return currentLanguage == Language.HINDI
            ? "    कृपया अपना कार्ड लें।                  "
            : "    Please collect your card.                  ";
    }
    
    public static String getHaveNiceDay() {
        return currentLanguage == Language.HINDI
            ? "    आपका दिन शुभ हो!                          "
            : "    Have a great day!                          ";
    }
    
    // Language Selection
    public static String getSelectLanguage() {
        return "  Select Language / भाषा चुनें:";
    }
    
    public static String getLanguageEnglish() {
        return "  1. English";
    }
    
    public static String getLanguageHindi() {
        return "  2. हिंदी";
    }
    
    public static String getLanguageChanged() {
        return currentLanguage == Language.HINDI
            ? "\n  भाषा सफलतापूर्वक बदली गई!"
            : "\n  Language changed successfully!";
    }
    
    // Error Messages
    public static String getInvalidChoice() {
        return currentLanguage == Language.HINDI
            ? "[X] अमान्य विकल्प! कृपया विकल्प 1-7 चुनें।"
            : "[X] Invalid choice! Please select option 1-7.";
    }
}
