import java.io.*;
import java.util.*;

class BankAccount implements Serializable {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    // Getters and setters
    // ...
}

class BankingSystem {
    private Map<String, BankAccount> accounts;
    private static final String DATA_FILE = "bank_accounts.ser";

    public BankingSystem() {
        accounts = new HashMap<>();
        loadData();
    }

    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
        accounts.put(accountNumber, account);
        System.out.println("Account created successfully.");
    }

    public void deposit(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = accounts.get(fromAccountNumber);
        BankAccount toAccount = accounts.get(toAccountNumber);
        if (fromAccount != null && toAccount != null) {
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient balance for transfer.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    public void displayAccountBalance(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Balance: " + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void displayTransactionHistory(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.printTransactionHistory();
        } else {
            System.out.println("Account not found.");
        }
    }

    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            accounts = (Map<String, BankAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Funds");
            System.out.println("5. Display Account Balance");
            System.out.println("6. Display Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter account holder name: ");
                    String accountHolderName = scanner.next();
                    System.out.print("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    createAccount(accountNumber, accountHolderName, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(accountNumber, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    withdraw(accountNumber, withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter source account number: ");
                    String sourceAccountNumber = scanner.next();
                    System.out.print("Enter destination account number: ");
                    String destinationAccountNumber = scanner.next();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    transferFunds(sourceAccountNumber, destinationAccountNumber, transferAmount);
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    displayAccountBalance(accountNumber);
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.next();
                    displayTransactionHistory(accountNumber);
                    break;
                case 7:
                    saveData();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        bankingSystem.showMenu();
    }
}
