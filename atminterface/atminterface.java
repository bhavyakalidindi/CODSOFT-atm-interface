import java.util.Scanner;

public class atminterface {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.println("Welcome to the ATM system");
        System.out.print("Enter the number of accounts to add: ");
        int numAccounts = in.nextInt();
        for (int i = 0; i < numAccounts; i++) {
            System.out.println("\nCreating account #" + (i + 1));
            System.out.print("Enter account number: ");
            int accNumber = in.nextInt();

            System.out.print("Enter PIN: ");
            int pin = in.nextInt();

            System.out.print("Enter initial balance: ");
            double balance = in.nextDouble();

            Account account = new Account(accNumber, pin, balance);
            atm.addAccount(account);
            System.out.println("Account #" + (i + 1) + " created successfully.");
        }

        System.out.println("\nAll accounts have been set up.");
        
        atm.startATM();  
        in.close();  
    }
}
