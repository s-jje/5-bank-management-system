import bank.*;
import customer.Customer;

import java.util.Scanner;

public class BankingSystem {

    public static Bank chooseBank() {
        System.out.println("Welcome to bank management system.");
        System.out.println("Choose a bank.");
        System.out.println("1. KB Kookmin Bank");
        System.out.println("2. Shinhan Bank");
        System.out.println("3. Woori Bank");
        System.out.println("4. Hana Bank");
        System.out.println("5. Toss Bank");
        System.out.println("6. Exit");
        System.out.println("Please enter a number 1 ~ 6.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1")) {
            return KbKookminBank.getInstance();
        } else if (input.equals("2")) {
            return ShinhanBank.getInstance();
        } else if (input.equals("3")) {
            return WooriBank.getInstance();
        } else if (input.equals("4")) {
            return HanaBank.getInstance();
        } else if (input.equals("5")) {
            return TossBank.getInstance();
        } else if (input.equals("6")) {
            throw new RuntimeException("Exit");
        } else {
            throw new RuntimeException("Invalid number.");
        }
    }

    public static String chooseMenu() {
        System.out.println("1. Transaction");
        System.out.println("2. Check balance");
        System.out.println("3. Check all transactions");
        System.out.println("4. Create an account");
        System.out.println("5. Exit");
        System.out.println("Please enter a number 1 ~ 5.");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void transaction(Bank bank) {
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("Please enter a number 1 ~ 2.");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1")) {
            Customer customer = getCustomer(bank);
            bank.getAccount(customer.getId(), customer.getPassword()).deposit(customer);
        } else if (input.equals("2")) {
            Customer customer = getCustomer(bank);
            bank.getAccount(customer.getId(), customer.getPassword()).withdrawal(customer);
        } else {
            throw new RuntimeException("Invalid number.");
        }
    }

    public static void checkBalance(Bank bank) {
        Customer customer = getCustomer(bank);
        bank.getAccount(customer.getId(), customer.getPassword()).showBalance(customer);
    }

    public static void checkAllTransactions(Bank bank) {
        Customer customer = getCustomer(bank);
        bank.getAccount(customer.getId(), customer.getPassword()).showAllTransactionData();
    }

    public static void createAccount(Bank bank) {
        bank.register();
    }

    public static void mangement(Bank bank) {

    }

    private static Customer getCustomer(Bank bank) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your ID: ");
        String id = scanner.nextLine();

        System.out.println("Enter your Password: ");
        String pw = scanner.nextLine();

        return bank.getCustomerList().getCustomer(id, pw);
    }
}
