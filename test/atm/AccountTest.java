package atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for Account functionality
 * Tests account operations including deposit, withdrawal, PIN validation, and transaction history
 * 
 * @author ATM Machine Simulation Team
 * @version 1.0
 * @since 2026-01-23
 */
class AccountTest {
    
    private Account account;
    private static final String ACCOUNT_NUMBER = "987654321";
    private static final String ACCOUNT_HOLDER = "Test User";
    private static final double INITIAL_BALANCE = 10000.0;
    private static final String PIN = "1234";
    
    @BeforeEach
    void setUp() {
        account = new Account(ACCOUNT_NUMBER, ACCOUNT_HOLDER, INITIAL_BALANCE, PIN);
    }
    
    @Test
    @DisplayName("Test account creation with valid parameters")
    void testAccountCreation() {
        assertNotNull(account);
        assertEquals(ACCOUNT_NUMBER, account.getAccountNumber());
        assertEquals(ACCOUNT_HOLDER, account.getAccountHolderName());
        assertEquals(INITIAL_BALANCE, account.getBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test account creation with null account number throws exception")
    void testAccountCreationWithNullAccountNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Account(null, ACCOUNT_HOLDER, INITIAL_BALANCE, PIN);
        });
    }
    
    @Test
    @DisplayName("Test account creation with negative balance throws exception")
    void testAccountCreationWithNegativeBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Account(ACCOUNT_NUMBER, ACCOUNT_HOLDER, -100.0, PIN);
        });
    }
    
    @Test
    @DisplayName("Test account creation with invalid PIN throws exception")
    void testAccountCreationWithInvalidPin() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Account(ACCOUNT_NUMBER, ACCOUNT_HOLDER, INITIAL_BALANCE, "12");
        });
    }
    
    @Test
    @DisplayName("Test PIN validation with correct PIN")
    void testValidatePinSuccess() {
        assertTrue(account.validatePin("1234"));
    }
    
    @Test
    @DisplayName("Test PIN validation with incorrect PIN")
    void testValidatePinFailure() {
        assertFalse(account.validatePin("5678"));
    }
    
    @Test
    @DisplayName("Test deposit operation")
    void testDeposit() {
        double depositAmount = 5000.0;
        account.deposit(depositAmount);
        assertEquals(INITIAL_BALANCE + depositAmount, account.getBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test withdrawal with sufficient balance")
    void testWithdrawSuccess() {
        double withdrawAmount = 3000.0;
        assertTrue(account.withdraw(withdrawAmount));
        assertEquals(INITIAL_BALANCE - withdrawAmount, account.getBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test withdrawal with insufficient balance")
    void testWithdrawFailure() {
        double withdrawAmount = 15000.0;
        assertFalse(account.withdraw(withdrawAmount));
        assertEquals(INITIAL_BALANCE, account.getBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test masked account number format")
    void testMaskedAccountNumber() {
        String masked = account.getMaskedAccountNumber();
        assertTrue(masked.startsWith("XXXXX"));
        assertTrue(masked.endsWith("4321"));
    }
    
    @Test
    @DisplayName("Test transaction history after deposit")
    void testTransactionHistoryAfterDeposit() {
        account.deposit(1000.0);
        assertFalse(account.getTransactionHistory().isEmpty());
        assertTrue(account.getTransactionHistory().get(0).contains("DEPOSIT"));
    }
    
    @Test
    @DisplayName("Test transaction history after withdrawal")
    void testTransactionHistoryAfterWithdrawal() {
        account.withdraw(500.0);
        assertFalse(account.getTransactionHistory().isEmpty());
        assertTrue(account.getTransactionHistory().get(0).contains("WITHDRAWAL"));
    }
    
    @Test
    @DisplayName("Test get last N transactions")
    void testGetLastTransactions() {
        account.deposit(100.0);
        account.withdraw(50.0);
        account.deposit(200.0);
        
        assertEquals(2, account.getLastTransactions(2).size());
        assertEquals(3, account.getLastTransactions(5).size());
    }
    
    @Test
    @DisplayName("Test money transfer success")
    void testTransferSuccess() {
        String targetAccount = "123456789";
        double transferAmount = 2000.0;
        
        assertTrue(account.transfer(transferAmount, targetAccount));
        assertEquals(INITIAL_BALANCE - transferAmount, account.getBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test money transfer with insufficient balance")
    void testTransferFailure() {
        String targetAccount = "123456789";
        double transferAmount = 15000.0;
        
        assertFalse(account.transfer(transferAmount, targetAccount));
        assertEquals(INITIAL_BALANCE, account.getBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test receive transfer")
    void testReceiveTransfer() {
        String sourceAccount = "111222333";
        double transferAmount = 3000.0;
        
        account.receiveTransfer(transferAmount, sourceAccount);
        assertEquals(INITIAL_BALANCE + transferAmount, account.getBalance(), 0.01);
    }
    
    @Test
    @DisplayName("Test change PIN with correct old PIN")
    void testChangePinSuccess() {
        String newPin = "5678";
        assertTrue(account.changePin(PIN, newPin));
        assertTrue(account.validatePin(newPin));
        assertFalse(account.validatePin(PIN));
    }
    
    @Test
    @DisplayName("Test change PIN with incorrect old PIN")
    void testChangePinWithWrongOldPin() {
        String wrongOldPin = "9999";
        String newPin = "5678";
        assertFalse(account.changePin(wrongOldPin, newPin));
        assertTrue(account.validatePin(PIN)); // Original PIN should still work
    }
    
    @Test
    @DisplayName("Test change PIN with invalid new PIN format")
    void testChangePinWithInvalidNewPin() {
        String invalidNewPin = "12"; // Only 2 digits
        assertFalse(account.changePin(PIN, invalidNewPin));
        assertTrue(account.validatePin(PIN)); // Original PIN should still work
    }
    
    @Test
    @DisplayName("Test change PIN with same old and new PIN")
    void testChangePinWithSamePin() {
        assertFalse(account.changePin(PIN, PIN));
        assertTrue(account.validatePin(PIN));
    }
}
