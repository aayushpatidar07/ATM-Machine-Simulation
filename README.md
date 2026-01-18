# ATM Machine Simulation - Java Project

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com)

## Project Overview
A comprehensive console-based ATM Machine Simulation built using Core Java. This project demonstrates Object-Oriented Programming principles, proper package structure, clean code architecture, and best practices in software design.

## Features
- **PIN Authentication** - Secure 4-digit PIN system with attempt limiting
- **Check Balance** - View current account balance with formatted output
- **Deposit Money** - Add funds to account with validation
- **Withdraw Money** - Withdraw funds with balance and minimum balance checks
- **Money Transfer** - Transfer funds to other accounts
- **Transaction History** - Complete transaction logging and tracking
- **Mini Statement** - View recent transactions
- **Receipt Generation** - Formatted transaction receipts
- **Session Management** - Track user sessions and timeouts
- **Statistics Tracking** - Monitor ATM usage metrics
- **Comprehensive Logging** - All operations logged for audit trail
- **Input Validation** - Robust validation for all user inputs
- **Error Handling** - Custom exception handling for ATM operations

## Project Structure
```
ATM Machine/
├── src/
│   ├── atm/
│   │   ├── Account.java              (Account model with transaction history)
│   │   ├── ATM.java                  (Main ATM interface)
│   │   ├── ATMService.java           (Business logic service layer)
│   │   ├── ATMConstants.java         (System-wide constants)
│   │   ├── ATMConfig.java            (Configuration management)
│   │   ├── ATMException.java         (Custom exception handling)
│   │   ├── ATMLogger.java            (Logging utility)
│   │   ├── ATMStatistics.java        (Usage statistics tracker)
│   │   ├── ATMUtil.java              (Helper utilities)
│   │   ├── Messages.java             (User messages and notifications)
│   │   ├── ReceiptGenerator.java     (Transaction receipt generation)
│   │   ├── SessionManager.java       (Session lifecycle management)
│   │   ├── TransactionType.java      (Transaction type enumeration)
│   │   └── Validator.java            (Input validation)
│   └── com/
│       └── atm/
│           ├── main/
│           │   └── ATMApplication.java    (Alternative entry point)
│           ├── model/
│           │   ├── Account.java           (Account data model)
│           │   └── Transaction.java       (Transaction record)
│           ├── service/
│           │   └── ATMService.java        (Business logic)
│           └── util/
│               └── InputValidator.java    (Input validation)
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
- **Java SE 17+** - Core Java
- **Collections Framework** - ArrayList for transaction history
- **Date/Time API** - Modern Java time handling
- **Exception Handling** - Custom exception classes
- **Object-Oriented Programming** - Encapsulation, Inheritance, Polymorphism
- **Design Patterns** - Singleton, Factory patterns

## Key Concepts Demonstrated
- ✅ Clean Code Architecture
- ✅ SOLID Principles
- ✅ Proper Package Structure
- ✅ Comprehensive Error Handling
- ✅ Input Validation
- ✅ Logging and Auditing
- ✅ Session Management
- ✅ Security Best Practices

## Author
Created as an interview-ready Java project demonstrating clean code principles.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing
Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## Security
Please read [SECURITY.md](SECURITY.md) for information on reporting security vulnerabilities.

## Version
2.0.0
