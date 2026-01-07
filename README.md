# ATM Machine Simulation - Java Project

## Project Overview
A comprehensive console-based ATM Machine Simulation built using Core Java. This project demonstrates Object-Oriented Programming principles, proper package structure, and clean code architecture.

## Features
- **Check Balance** - View current account balance
- **Deposit Money** - Add funds to account with validation
- **Withdraw Money** - Withdraw funds with balance checks
- **Transaction History** - View last 5 transactions
- **PIN Authentication** - Secure 4-digit PIN system
- **Exit** - Safely terminate the session

## Project Structure
```
ATM Machine/
├── src/
│   ├── com/
│   │   └── atm/
│   │       ├── main/
│   │       │   └── ATMApplication.java    (Main entry point)
│   │       ├── model/
│   │       │   ├── Account.java           (Account data model)
│   │       │   └── Transaction.java       (Transaction record)
│   │       ├── service/
│   │       │   └── ATMService.java        (Business logic)
│   │       └── util/
│   │           └── InputValidator.java    (Input validation)
├── bin/                                    (Compiled classes)
└── README.md                               (This file)
```

## How to Compile and Run

### Method 1: Using Command Line
```bash
# Navigate to project directory
cd "ATM Machine"

# Compile all Java files
javac -d bin src/com/atm/main/*.java src/com/atm/model/*.java src/com/atm/service/*.java src/com/atm/util/*.java

# Run the application
java -cp bin com.atm.main.ATMApplication
```

### Method 2: Quick Run Script
```bash
# Run the provided compile and run script
compile_and_run.bat
```

## Default Credentials
- **Account Number**: 123456789
- **PIN**: 1234
- **Initial Balance**: $5000.00

## Technologies Used
- Java SE (Core Java)
- Scanner for input handling
- ArrayList for transaction history
- Object-Oriented Programming concepts

## Author
Created as an interview-ready Java project demonstrating clean code principles.

## Version
1.0.0
