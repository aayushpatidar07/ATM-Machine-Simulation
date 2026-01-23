package atm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for LanguageManager functionality
 * Tests multi-language support features
 * 
 * @author ATM Machine Simulation Team
 * @version 1.0
 * @since 2026-01-23
 */
class LanguageManagerTest {
    
    @Test
    @DisplayName("Test default language is English")
    void testDefaultLanguage() {
        assertEquals(LanguageManager.Language.ENGLISH, LanguageManager.getCurrentLanguage());
    }
    
    @Test
    @DisplayName("Test set language to Hindi")
    void testSetLanguageToHindi() {
        LanguageManager.setLanguage(LanguageManager.Language.HINDI);
        assertEquals(LanguageManager.Language.HINDI, LanguageManager.getCurrentLanguage());
        
        // Reset to English for other tests
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
    }
    
    @Test
    @DisplayName("Test welcome title in English")
    void testWelcomeTitleEnglish() {
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
        String title = LanguageManager.getWelcomeTitle();
        assertTrue(title.contains("WELCOME"));
    }
    
    @Test
    @DisplayName("Test welcome title in Hindi")
    void testWelcomeTitleHindi() {
        LanguageManager.setLanguage(LanguageManager.Language.HINDI);
        String title = LanguageManager.getWelcomeTitle();
        assertTrue(title.contains("स्वागत"));
        
        // Reset to English
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
    }
    
    @Test
    @DisplayName("Test menu check balance in English")
    void testMenuCheckBalanceEnglish() {
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
        String menu = LanguageManager.getMenuCheckBalance();
        assertTrue(menu.contains("Check Balance"));
    }
    
    @Test
    @DisplayName("Test menu check balance in Hindi")
    void testMenuCheckBalanceHindi() {
        LanguageManager.setLanguage(LanguageManager.Language.HINDI);
        String menu = LanguageManager.getMenuCheckBalance();
        assertTrue(menu.contains("बैलेंस"));
        
        // Reset to English
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
    }
    
    @Test
    @DisplayName("Test PIN incorrect message with attempts")
    void testPinIncorrectMessage() {
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
        String message = LanguageManager.getPinIncorrect(2);
        assertTrue(message.contains("2"));
        assertTrue(message.contains("Incorrect"));
    }
    
    @Test
    @DisplayName("Test language change confirmation")
    void testLanguageChangeConfirmation() {
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
        String message = LanguageManager.getLanguageChanged();
        assertTrue(message.contains("Language changed"));
        
        LanguageManager.setLanguage(LanguageManager.Language.HINDI);
        message = LanguageManager.getLanguageChanged();
        assertTrue(message.contains("भाषा"));
        
        // Reset to English
        LanguageManager.setLanguage(LanguageManager.Language.ENGLISH);
    }
}
