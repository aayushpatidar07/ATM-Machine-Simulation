package com.atm.main;

import com.atm.model.Account;
import com.atm.model.Transaction;
import com.atm.service.ATMService;
import java.util.List;
import java.util.Scanner;

/**
 * Main ATM Application class - Entry point of the program
 */
public class ATMApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static ATMService atmService;

    public static void main(String[] args) {
        // Initialize default account
        Account defaultAccount = new Account(
            "123456789",
            "John Doe",
            5000.00,
            "1234"
        );
        
        atmService = new ATMService(defaultAccount);

        // Display welcome screen
        displayWelcomeScreen();

        // Authenticate user
        if (!authenticateUser()) {
            System.out.println("\n[X] Authentication failed! Exiting...");
            scanner.close();
            return;
        }

        // Main ATM menu loop
        boolean exit = false;
        while (!exit) {
            displayMenu();
            
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            System.out.println();

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
                    viewTransactionHistory();
                    break;
                case 5:
                    exit = true;
                    exitATM();
                    break;
                default:
                    System.out.println("[X] Invalid choice! Please select options 1-5.");
                    System.out.println();
            }
        }

        scanner.close();
    }

    /**
     * Displays welcome screen
     */
    private static void displayWelcomeScreen() {
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("     WELCOME TO GLOBAL BANK ATM SYSTEM        ");
        System.out.println("                                               ");
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Authenticates user with PIN
     */
    private static boolean authenticateUser() {
        int attempts = 3;
        
        while (attempts > 0) {
            System.out.print("Enter your 4-digit PIN: ");
            String pin = scanner.next();
            
            if (atmService.authenticate(pin)) {
                System.out.println("\n[SUCCESS] Authentication successful!");
                System.out.println("Welcome, " + atmService.getAccountHolderName() + "!");
                System.out.println("Account: " + atmService.getMaskedAccountNumber());
                System.out.println();
                return true;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("[X] Invalid PIN! You have " + attempts + " attempt(s) remaining.");
                }
            }
        }
        
        return false;
    }

    /**
     * Displays main menu
     */
    private static void displayMenu() {
        System.out.println("===============================================");
        System.out.println("              ATM MAIN MENU                    ");
        System.out.println("===============================================");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
        System.out.println("===============================================");
    }

    /**
     * Checks and displays balance
     */
    private static void checkBalance() {
        System.out.println("===============================================");
        System.out.println("           BALANCE INQUIRY                     ");
        System.out.println("===============================================");
        System.out.printf("Current Balance: $%.2f%n", atmService.checkBalance());
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Handles deposit operation
     */
    private static void depositMoney() {
        System.out.println("===============================================");
        System.out.println("           DEPOSIT MONEY                       ");
        System.out.println("===============================================");
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();

        if (atmService.deposit(amount)) {
            System.out.println("\n[SUCCESS] Deposit successful!");
            System.out.printf("Amount deposited: $%.2f%n", amount);
            System.out.printf("New balance: $%.2f%n", atmService.checkBalance());
        } else {
            System.out.println("\n[X] Invalid amount! Please enter a positive value.");
        }
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Handles withdrawal operation
     */
    private static void withdrawMoney() {
        System.out.println("===============================================");
        System.out.println("          WITHDRAW MONEY                       ");
        System.out.println("===============================================");
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();

        if (atmService.withdraw(amount)) {
            System.out.println("\n[SUCCESS] Withdrawal successful!");
            System.out.printf("Amount withdrawn: $%.2f%n", amount);
            System.out.printf("Remaining balance: $%.2f%n", atmService.checkBalance());
        } else {
            System.out.println("\n[X] Transaction failed!");
            System.out.println("Reason: Invalid amount or insufficient balance.");
            System.out.printf("Your current balance: $%.2f%n", atmService.checkBalance());
        }
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Displays transaction history
     */
    private static void viewTransactionHistory() {
        System.out.println("===============================================");
        System.out.println("        TRANSACTION HISTORY (Last 5)           ");
        System.out.println("===============================================");
        
        List<Transaction> history = atmService.getTransactionHistory();
        
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println();
            for (Transaction transaction : history) {
                System.out.println(transaction);
            }
        }
        
        System.out.println("===============================================");
        System.out.println();
    }

    /**
     * Exits ATM system
     */
    private static void exitATM() {
        System.out.println("===============================================");
        System.out.println("                                               ");
        System.out.println("   Thank you for using Global Bank ATM!       ");
        System.out.println("   Have a great day!                           ");
        System.out.println("                                               ");
        System.out.println("===============================================");
    }
}
