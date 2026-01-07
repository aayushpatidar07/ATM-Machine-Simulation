package atm;

import java.util.Scanner;

/**
 * ATM Main Class - Entry point of ATM Machine Simulation
 * Provides console-based ATM interface with PIN authentication and banking operations
 * @author ATM Machine Simulation
 * @version 1.0
 */
public class ATM {
    private static Scanner scanner = new Scanner(System.in);
    private static ATMService atmService;
    private static final int MAX_PIN_ATTEMPTS = 3;

    public static void main(String[] args) {
        // Initialize default account
        Account account = new Account(
            "987654321",
            "Rajesh Kumar",
            50000.00,
            "1234"
        );
        
        atmService = new ATMService(account);

        // Display welcome screen
        displayWelcomeScreen();

        // Authenticate user with PIN
        if (!authenticateUser()) {
            System.out.println("\n[X] Maximum attempts exceeded! Card blocked for security.");
            System.out.println("Please contact your bank.");
            scanner.close();
            return;
        }

        // Main ATM menu loop
        runATM();
        
        scanner.close();
    }

    /**
     * Displays welcome screen with bank branding
     */
    private static void displayWelcomeScreen() {
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("    WELCOME TO STATE BANK ATM SYSTEM          ");
        System.out.println("          Serving India Since 1806             ");
        System.out.println("                                               ");
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Authenticates user with PIN (max 3 attempts)
     * @return true if authentication successful, false otherwise
     */
    private static boolean authenticateUser() {
        int attempts = MAX_PIN_ATTEMPTS;
        
        System.out.println(">>> AUTHENTICATION REQUIRED <<<");
        System.out.println();
        
        while (attempts > 0) {
            System.out.print("Enter your 4-digit PIN: ");
            String pin = scanner.next();
            
            if (atmService.authenticate(pin)) {
                System.out.println("\n[SUCCESS] PIN verified successfully!");
                System.out.println("Welcome, " + atmService.getAccountHolderName() + "!");
                System.out.println("Account Number: " + atmService.getMaskedAccountNumber());
                System.out.println();
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("[X] Incorrect PIN! You have " + attempts + " attempt(s) remaining.");
                    System.out.println();
                }
            }
        }
        
        return false;
    }

    /**
     * Main ATM operation loop - runs continuously until user exits
     */
    private static void runATM() {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            
            System.out.print("Enter your choice (1-4): ");
            int choice = scanner.nextInt();
            System.out.println();

            // Switch-case for menu handling
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    exit = true;
                    exitATM();
                    break;
                default:
                    System.out.println("[X] Invalid choice! Please select option 1-4.");
                    System.out.println();
            }
        }
    }

    /**
     * Displays ATM main menu
     */
    private static void displayMenu() {
        System.out.println("===============================================");
        System.out.println("              ATM MAIN MENU                    ");
        System.out.println("===============================================");
        System.out.println("  1. Check Balance");
        System.out.println("  2. Deposit Money");
        System.out.println("  3. Withdraw Money");
        System.out.println("  4. Exit");
        System.out.println("===============================================");
    }

    /**
     * Checks and displays current account balance
     */
    private static void checkBalance() {
        System.out.println("===============================================");
        System.out.println("           BALANCE INQUIRY                     ");
        System.out.println("===============================================");
        System.out.printf("  Current Balance: Rs. %.2f%n", atmService.checkBalance());
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Handles deposit money operation
     * Validates amount and updates balance
     */
    private static void depositMoney() {
        System.out.println("===============================================");
        System.out.println("           CASH DEPOSIT                        ");
        System.out.println("===============================================");
        System.out.print("  Enter amount to deposit (Rs.): ");
        double amount = scanner.nextDouble();

        if (atmService.depositMoney(amount)) {
            System.out.println("\n  [SUCCESS] Amount deposited successfully!");
            System.out.printf("  Deposited Amount: Rs. %.2f%n", amount);
            System.out.printf("  Updated Balance: Rs. %.2f%n", atmService.checkBalance());
        } else {
            System.out.println("\n  [X] Invalid amount! Amount must be greater than zero.");
        }
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Handles withdraw money operation
     * Validates amount and checks sufficient balance
     */
    private static void withdrawMoney() {
        System.out.println("===============================================");
        System.out.println("           CASH WITHDRAWAL                     ");
        System.out.println("===============================================");
        System.out.print("  Enter amount to withdraw (Rs.): ");
        double amount = scanner.nextDouble();

        if (atmService.withdrawMoney(amount)) {
            System.out.println("\n  [SUCCESS] Please collect your cash!");
            System.out.printf("  Withdrawn Amount: Rs. %.2f%n", amount);
            System.out.printf("  Remaining Balance: Rs. %.2f%n", atmService.checkBalance());
        } else {
            System.out.println("\n  [X] Transaction failed!");
            if (amount <= 0) {
                System.out.println("  Reason: Invalid amount entered.");
            } else {
                System.out.println("  Reason: Insufficient balance in your account.");
                System.out.printf("  Your current balance: Rs. %.2f%n", atmService.checkBalance());
            }
        }
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Displays exit message and terminates ATM session
     */
    private static void exitATM() {
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("    Thank you for using State Bank ATM!       ");
        System.out.println("    Please collect your card.                  ");
        System.out.println("    Have a great day!                          ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }
}
