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
    private static final int MAX_PIN_ATTEMPTS = ATMConstants.MAX_PIN_ATTEMPTS;

    /**
     * Main entry point for ATM application
     * @param args Command line arguments (not used)
     */
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
        try {
            runATM();
        } finally {
            scanner.close();
            System.out.println("\nThank you for using our ATM service!");
        }
    }

    /**
     * Displays welcome screen with bank branding
     */
    private static void displayWelcomeScreen() {
        ATMUtil.printLine();
        System.out.println("                                               ");
        System.out.println(LanguageManager.getWelcomeTitle());
        System.out.println(LanguageManager.getWelcomeTagline());
        System.out.println("                                               ");
        ATMUtil.printLine();
        System.out.println();
    }

    /**
     * Authenticates user with PIN (max 3 attempts)
     * @return true if authentication successful, false otherwise
     */
    private static boolean authenticateUser() {
        int attempts = MAX_PIN_ATTEMPTS;
        
        System.out.println(Messages.MSG_AUTH_REQUIRED);
        System.out.println();
        
        while (attempts > 0) {
            System.out.print(Messages.MSG_ENTER_PIN);
            String pin = scanner.next();
            
            if (atmService.authenticate(pin)) {
                System.out.println(Messages.MSG_PIN_SUCCESS);
                System.out.println(String.format(Messages.MSG_WELCOME_USER, atmService.getAccountHolderName()));
                System.out.println(String.format(Messages.MSG_ACCOUNT_NUMBER, atmService.getMaskedAccountNumber()));
                System.out.println();
                ATMLogger.logAuthentication("User", true);
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println(String.format(Messages.MSG_PIN_INCORRECT, attempts));
                    System.out.println();
                }
                ATMLogger.logAuthentication("User", false);
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
            
            System.out.print(LanguageManager.getEnterChoice());
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
                    changePin();
                    break;
                case 5:
                    exportStatement();
                    break;
                case 6:
                    changeLanguage();
                    break;
                case 7:
                    exit = true;
                    exitATM();
                    break;
                default:
                    System.out.println(LanguageManager.getInvalidChoice());
                    System.out.println();
            }
        }
    }

    /**
     * Displays ATM main menu
     */
    private static void displayMenu() {
        System.out.println("===============================================");
        System.out.println(LanguageManager.getMainMenuTitle());
        System.out.println("===============================================");
        System.out.println(LanguageManager.getMenuCheckBalance());
        System.out.println(LanguageManager.getMenuDeposit());
        System.out.println(LanguageManager.getMenuWithdraw());
        System.out.println(LanguageManager.getMenuChangePin());
        System.out.println(LanguageManager.getMenuExportStatement());
        System.out.println(LanguageManager.getMenuChangeLanguage());
        System.out.println(LanguageManager.getMenuExit());
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
     * Handles change PIN operation
     * Validates old PIN and sets new PIN
     */
    private static void changePin() {
        System.out.println("===============================================");
        System.out.println("           CHANGE PIN                          ");
        System.out.println("===============================================");
        System.out.print("  Enter current PIN: ");
        String oldPin = scanner.next();
        System.out.print("  Enter new PIN (4 digits): ");
        String newPin = scanner.next();
        System.out.print("  Confirm new PIN: ");
        String confirmPin = scanner.next();

        if (!newPin.equals(confirmPin)) {
            System.out.println("\n  [X] PINs do not match! Please try again.");
        } else if (atmService.changePin(oldPin, newPin)) {
            System.out.println("\n  [SUCCESS] PIN changed successfully!");
            System.out.println("  Please remember your new PIN.");
        } else {
            System.out.println("\n  [X] PIN change failed!");
            System.out.println("  Reason: Invalid old PIN or new PIN format.");
            System.out.println("  Note: New PIN must be 4 digits and different from old PIN.");
        }
        System.out.println("===============================================");
        System.out.println();
    }Handles account statement export operation
     * Allows user to choose format and export transaction history
     */
    private static void exportStatement() {
        System.out.println("===============================================");
        System.out.println("           EXPORT STATEMENT                    ");
        System.out.println("===============================================");
        System.out.println("  Select Export Format:");
        System.out.println("  1. CSV (Comma Separated Values)");
        System.out.println("  2. TXT (Plain Text)");
        System.out.println("  3. HTML (Web Page)");
        System.out.println("  4. Cancel");
        System.out.print("\n  Enter your choice (1-4): ");
        
        int formatChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        if (formatChoice == 4) {
            System.out.println("\n  Export cancelled.");
            System.out.println("===============================================");
            System.out.println();
            return;
        }
        
        System.out.print("  Enter output path (or press Enter for current directory): ");
        String outputPath = scanner.nextLine();
        if (outputPath.trim().isEmpty()) {
            outputPath = ".";
        }
        
        boolean success = false;
        java.util.List<String> transactions = atmService.getTransactionHistory();
        
        switch (formatChoice) {
            case 1:
                success = StatementExporter.exportToCSV(
                    atmService.getMaskedAccountNumber(),
                    atmService.getAccountHolderName(),
                    transactions,
                    outputPath
                );
                break;
            case 2:
                success = StatementExporter.exportToTXT(
                    atmService.getMaskedAccountNumber(),
                    atmService.getAccountHolderName(),
                    atmService.checkBalance(),
                    transactions,
                    outputPath
                );
                break;
            case 3:
                success = StatementExporter.exportToHTML(
                    atmService.getMaskedAccountNumber(),
                    atmService.getAccountHolderName(),
                    atmService.checkBalance(),
                    transactions,
                    outputPath
                );
                break;
            default:
                System.out.println("\n  [X] Invalid format choice!");
        }
        
        if (success) {
            System.out.println("  Statement has been exported successfully!");
        }
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * 

    /**
     * Displays exit message and terminates ATM session
     */
    private static void exitATM() {
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println(LanguageManager.getThankYou());
        System.out.println(LanguageManager.getCollectCard());
        System.out.println(LanguageManager.getHaveNiceDay());
        System.out.println("                                               ");
        System.out.println("===============================================");
    }
}
