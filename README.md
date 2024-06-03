# Basic Banking System

This is a simple command-line banking system implemented in Java. It allows users to create bank accounts, perform basic banking operations like deposits, withdrawals, and fund transfers, and view account balances and transaction history.

## Features

- Create new bank accounts with an account number, account holder name, and initial balance
- Deposit money into an account
- Withdraw money from an account
- Transfer funds between accounts
- Display account balance
- Display transaction history
- Persist bank accounts and transaction history to a file
- Load bank accounts and transaction history from a file on startup

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your system

### Running the Application

1. Clone the repository or download the source code files.
2. Open a terminal or command prompt and navigate to the directory containing the source code.
3. Compile the Java file using the following command:
   ```
   javac BankingSystem.java
   ```
4. Run the application using the following command:
   ```
   java BankingSystem
   ```

## Usage

Upon running the application, you will be presented with a menu of options:

1. Create Account
2. Deposit
3. Withdraw
4. Transfer Funds
5. Display Account Balance
6. Display Transaction History
7. Exit

Enter the corresponding number to select an option and follow the prompts to provide the necessary information.

## Data Persistence

The application uses Java's serialization mechanism to persist bank accounts and their transaction history to a file named `bank_accounts.ser`. When the application starts, it automatically loads the accounts from this file, and when the application exits, it saves the accounts back to the file.

If the `bank_accounts.ser` file doesn't exist, the application will start with an empty set of accounts.

## Code Structure

The code consists of two main classes:

- `BankAccount`: Represents a bank account with properties like account number, account holder name, balance, and transaction history. It provides methods for common banking operations like deposit, withdrawal, and balance inquiry.

- `BankingSystem`: Manages multiple bank accounts using a `HashMap`. It provides methods to create new accounts, retrieve existing accounts, and perform transactions between accounts. It also includes a command-line interface for user interaction and methods for loading and saving account data to a file.

## Contributing

Contributions to this project are welcome. If you find any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
