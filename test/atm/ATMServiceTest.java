package atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for ATMService functionality
 * Tests ATM service operations including authentication, balance checks, deposits, and withdrawals
 * 
 * @author ATM Machine Simulation Team
 * @version 1.0
 * @since 2026-01-23
 */
class ATMServiceTest {
    
    private ATMService atmService;
    private Account testAccount;
    private static final String ACCOUNT_NUMBER = "987654321";
    private static final String ACCOUNT_HOLDER = "Test User";
    private static final double INITIAL_BALANCE = 50000.0;
    private static final String PIN = "1234";
    
    @BeforeEach
    void setUp() {
        testAccount = new Account(ACCOUNT_NUMBER, ACCOUNT_HOLDER, INITIAL_BALANCE, PIN);
        atmService = new ATMService(testAccount);
    }
    
    @Test
    @DisplayName("Test ATMService creation with null account throws exception")
    void testATMServiceCreationWithNullAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ATMService(null);
        });
    }
    
    @Test
    @DisplayName("Test authentication with correct PIN")
    void testAuthenticationSuccess() {
        assertTrue(atmService.authenticate(PIN));
        assertEquals(0, atmService.getFailedLoginAttempts());
    }
    
    @Test
    @DisplayName("Test authentication with incorrect PIN")
    void testAuthenticationFailure() {
        assertFalse(atmService.authenticate("9999"));
        assertEquals(1, atmService.getFailedLoginAttempts());
    }
    
    @Test
    @DisplayName("Test multiple failed authentication attempts")
    void testMultipleFailedAuthentications() {
        atmService.authenticate("1111");
        atmService.authenticate("2222");
        atmService.authenticate("3333");
        
        assertEquals(3, atmService.getFailedLoginAttempts());
        assertFalse(atmService.authenticate("1234")); // Account should be frozen
    }
    
    @Test
    @DisplayName("Test reset failed login attempts")
    void testResetFailedLoginAttempts() {
        atmService.authenticate("9999");
        assertEquals(1, atmService.getFailedLoginAttempts());
        
        atmService.resetFailedLoginAttempts();
        assertEquals(0, atmService.getFailedLoginAttempts());
    }
    
    @Test
    @DisplayName("Test check balance")
    void testCheckBalance() {
        assertEquals(INITIAL_BALANCE, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test deposit money with valid amount")
    void testDepositMoneySuccess() {
        double depositAmount = 5000.0;
        assertTrue(atmService.depositMoney(depositAmount));
        assertEquals(INITIAL_BALANCE + depositAmount, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test deposit money with negative amount")
    void testDepositMoneyWithNegativeAmount() {
        assertFalse(atmService.depositMoney(-100.0));
        assertEquals(INITIAL_BALANCE, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test deposit money with zero amount")
    void testDepositMoneyWithZeroAmount() {
        assertFalse(atmService.depositMoney(0.0));
        assertEquals(INITIAL_BALANCE, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test withdraw money with valid amount")
    void testWithdrawMoneySuccess() {
        double withdrawAmount = 3000.0;
        assertTrue(atmService.withdrawMoney(withdrawAmount));
        assertEquals(INITIAL_BALANCE - withdrawAmount, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test withdraw money with insufficient balance")
    void testWithdrawMoneyWithInsufficientBalance() {
        double withdrawAmount = 60000.0;
        assertFalse(atmService.withdrawMoney(withdrawAmount));
        assertEquals(INITIAL_BALANCE, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test withdraw money with negative amount")
    void testWithdrawMoneyWithNegativeAmount() {
        assertFalse(atmService.withdrawMoney(-500.0));
        assertEquals(INITIAL_BALANCE, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test get account holder name")
    void testGetAccountHolderName() {
        assertEquals(ACCOUNT_HOLDER, atmService.getAccountHolderName());
    }
    
    @Test
    @DisplayName("Test get masked account number")
    void testGetMaskedAccountNumber() {
        String masked = atmService.getMaskedAccountNumber();
        assertTrue(masked.startsWith("XXXXX"));
        assertTrue(masked.endsWith("4321"));
    }
    
    @Test
    @DisplayName("Test get mini statement")
    void testGetMiniStatement() {
        atmService.depositMoney(1000.0);
        atmService.withdrawMoney(500.0);
        atmService.depositMoney(2000.0);
        
        java.util.List<String> miniStatement = atmService.getMiniStatement(2);
        assertEquals(2, miniStatement.size());
    }
    
    @Test
    @DisplayName("Test get transaction history")
    void testGetTransactionHistory() {
        atmService.depositMoney(1000.0);
        atmService.withdrawMoney(500.0);
        
        java.util.List<String> history = atmService.getTransactionHistory();
        assertFalse(history.isEmpty());
        assertTrue(history.size() >= 2);
    }
    
    @Test
    @DisplayName("Test transfer money success")
    void testTransferMoneySuccess() {
        String targetAccount = "123456789";
        double transferAmount = 5000.0;
        
        assertTrue(atmService.transferMoney(transferAmount, targetAccount));
        assertEquals(INITIAL_BALANCE - transferAmount, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test transfer money with invalid amount")
    void testTransferMoneyWithInvalidAmount() {
        String targetAccount = "123456789";
        assertFalse(atmService.transferMoney(-100.0, targetAccount));
        assertEquals(INITIAL_BALANCE, atmService.checkBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test change PIN success")
    void testChangePinSuccess() {
        String newPin = "5678";
        assertTrue(atmService.changePin(PIN, newPin));
    }
    
    @Test
    @DisplayName("Test change PIN with wrong old PIN")
    void testChangePinWithWrongOldPin() {
        String wrongOldPin = "9999";
        String newPin = "5678";
        assertFalse(atmService.changePin(wrongOldPin, newPin));
    }
    
    @Test
    @DisplayName("Test account frozen status")
    void testAccountFrozenStatus() {
        assertFalse(atmService.isAccountFrozen());
        atmService.setAccountFrozen(true);
        assertTrue(atmService.isAccountFrozen());
    }
    
    @Test
    @DisplayName("Test remaining daily withdrawal limit")
    void testRemainingDailyWithdrawalLimit() {
        double initialLimit = atmService.getRemainingDailyWithdrawalLimit();
        assertTrue(initialLimit > 0);
        
        atmService.withdrawMoney(10000.0);
        assertTrue(atmService.getRemainingDailyWithdrawalLimit() < initialLimit);
    }
    
    @Test
    @DisplayName("Test daily transaction count")
    void testDailyTransactionCount() {
        int initialCount = atmService.getDailyTransactionCount();
        
        atmService.depositMoney(1000.0);
        assertEquals(initialCount + 1, atmService.getDailyTransactionCount());
        
        atmService.withdrawMoney(500.0);
        assertEquals(initialCount + 2, atmService.getDailyTransactionCount());
    }
}
