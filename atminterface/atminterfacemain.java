import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private int accNumber;
    private int pin;
    private double balance;

    public Account(int accNumber, int pin, double balance) {
        this.accNumber = accNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accNumber;
    }

    public boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {         
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATM {
    private Map<Integer, Account> accounts;
    private Scanner scanner;

    public ATM() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void startATM() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your account number: ");
        int accNumber = scanner.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        if (authenticateAccount(accNumber, pin)) {
            performTransactions(accNumber);
        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }

    private boolean authenticateAccount(int accNumber, int pin) {
        Account account = accounts.get(accNumber); // Fixed: `accounts.get` to access the map, not `account.get`
        return account != null && account.validatePin(pin);
    }

    private void performTransactions(int accNumber) {
        Account account = accounts.get(accNumber); // Fixed: `accounts.get` to access the map, not `account.get`

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    return; // Exit the method
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}


