import bank.Bank;
import customer.Customer;

import java.util.Scanner;

public class BankingSystem {

    public static String chooseBank() {
        System.out.println("[Available bank list]");
        System.out.println("1. Toss Bank");
        System.out.println("2. KB Kookmin Bank");
        System.out.println("3. Shinhan Bank");
        System.out.println("4. Woori Bank");
        System.out.println("5. Hana Bank");
        System.out.println("6. Exit");
        System.out.print("Please enter a number 1 ~ 6: ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String chooseMenu() {
        System.out.println("[Menu list]");
        System.out.println("1. Transaction");
        System.out.println("2. Check balance");
        System.out.println("3. Check all transactions");
        System.out.println("4. Create an account");
        System.out.println("5. Return to previous menu");
        System.out.println("6. Exit");
        System.out.print("Please enter a number 1 ~ 6: ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void transaction(Bank bank) {
        System.out.println("Transaction");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.print("Please enter a number 1 ~ 2: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println();

        if (input.equals("1")) {
            Customer customer = getCustomer(bank);
            bank.getAccount(customer.getId(), customer.getPassword()).deposit();
        } else if (input.equals("2")) {
            Customer customer = getCustomer(bank);
            bank.getAccount(customer.getId(), customer.getPassword()).withdrawal();
        } else {
            throw new RuntimeException("Invalid number.");
        }
    }

    public static void checkBalance(Bank bank) {
        Customer customer = getCustomer(bank);
        bank.getAccount(customer.getId(), customer.getPassword()).showBalance();
    }

    public static void checkAllTransactions(Bank bank) {
        Customer customer = getCustomer(bank);
        bank.getAccount(customer.getId(), customer.getPassword()).showAllTransactionData();
    }

    public static void createAccount(Bank bank) {
        bank.register();
    }

    public static void management(Bank bank) {

    }

    private static Customer getCustomer(Bank bank) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter your Password: ");
        String pw = scanner.nextLine();

        return bank.getCustomerList().getCustomer(id, pw);
    }
}
