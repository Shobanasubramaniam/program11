import java.util.HashMap;
import java.util.Scanner;

// BankAccount class
class BankAccount {
    private String accountHolderName;
    private double balance;
    private String accountNumber;

    // Constructor for BankAccount
    public BankAccount(String accountHolderName, String accountNumber, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
    }

    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds or invalid withdrawal amount.");
        }
    }

    // Method to transfer money to another account
    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            receiver.deposit(amount);
            System.out.println("Transferred $" + amount + " to " + receiver.getAccountHolderName());
        } else {
            System.out.println("Transfer failed due to insufficient balance or invalid amount.");
        }
    }
}

// Main class for Bank Account Management System
public class program11{
    private static HashMap<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nBank Account Management System:");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Transfer money");
            System.out.println("5. Check balance");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    transferMoney(scanner);
                    break;
                case 5:
                    checkBalance(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    // Method to create a new account
    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();

        BankAccount newAccount = new BankAccount(name, accountNumber, initialDeposit);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully for " + name + " with account number: " + accountNumber);
    }

    // Method to deposit money into an account
    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to withdraw money from an account
    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to transfer money between accounts
    private static void transferMoney(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String senderAccountNumber = scanner.nextLine();
        BankAccount sender = accounts.get(senderAccountNumber);

        if (sender != null) {
            System.out.print("Enter receiver's account number: ");
            String receiverAccountNumber = scanner.nextLine();
            BankAccount receiver = accounts.get(receiverAccountNumber);

            if (receiver != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                sender.transfer(receiver, amount);
            } else {
                System.out.println("Receiver's account not found.");
            }
        } else {
            System.out.println("Sender's account not found.");
        }
    }

    // Method to check balance of an account
    private static void checkBalance(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);

        if (account != null) {
            System.out.println("Account balance for " + account.getAccountHolderName() + ": $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
