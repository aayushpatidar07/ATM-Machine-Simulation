import java.util.Scanner;

public class ATMachine {
    // Static variable to maintain balance across method calls
    private static double balance = 1000.00; // Initial balance
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("========================================");
        System.out.println("   Welcome to ATM Machine Simulation   ");
        System.out.println("========================================");

        // Main loop - runs continuously until user exits
        while (!exit) {
            displayMenu();
            
            System.out.print("Enter your choice: ");
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
                    System.out.println("[X] Invalid choice! Please select a valid option (1-4).");
                    System.out.println();
            }
        }

        scanner.close();
    }

    /**
     * Displays the ATM menu options
     */
    private static void displayMenu() {
        System.out.println("========================================");
        System.out.println("              ATM MENU                  ");
        System.out.println("========================================");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.println("========================================");
    }

    /**
     * Displays the current account balance
     */
    private static void checkBalance() {
        System.out.println("========================================");
        System.out.println("           BALANCE INQUIRY              ");
        System.out.println("========================================");
        System.out.printf("Your current balance is: $%.2f%n", balance);
        System.out.println("========================================");
        System.out.println();
    }

    /**
     * Handles deposit operation
     * Validates amount and adds to balance
     */
    private static void depositMoney() {
        System.out.println("========================================");
        System.out.println("           DEPOSIT MONEY                ");
        System.out.println("========================================");
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        // Validate deposit amount
        if (amount <= 0) {
            System.out.println("[X] Invalid amount! Deposit amount must be greater than zero.");
        } else {
            balance += amount;
            System.out.println("[SUCCESS] Deposit successful!");
            System.out.printf("$%.2f has been deposited to your account.%n", amount);
            System.out.printf("New balance: $%.2f%n", balance);
        }
        System.out.println("========================================");
        System.out.println();
    }

    /**
     * Handles withdrawal operation
     * Validates amount and deducts from balance
     */
    private static void withdrawMoney() {
        System.out.println("========================================");
        System.out.println("          WITHDRAW MONEY                ");
        System.out.println("========================================");
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        // Validate withdrawal amount
        if (amount <= 0) {
            System.out.println("[X] Invalid amount! Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("[X] Insufficient balance!");
            System.out.printf("Your current balance is: $%.2f%n", balance);
        } else {
            balance -= amount;
            System.out.println("[SUCCESS] Withdrawal successful!");
            System.out.printf("$%.2f has been withdrawn from your account.%n", amount);
            System.out.printf("Remaining balance: $%.2f%n", balance);
        }
        System.out.println("========================================");
        System.out.println();
    }

    /**
     * Displays exit message and terminates the program
     */
    private static void exitATM() {
        System.out.println("========================================");
        System.out.println("Thank you for using ATM Machine!");
        System.out.println("Have a great day!");
        System.out.println("========================================");
    }
}
