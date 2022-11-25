package util;

import bank.*;
import customer.Customer;

import java.util.Scanner;

public class BankingSystem {

    public static String chooseBank() {
        System.out.println("=======================");
        System.out.println("| Available bank list |");
        System.out.println("-----------------------");
        System.out.println("| 1. Toss Bank        |");
        System.out.println("| 2. KB Kookmin Bank  |");
        System.out.println("| 3. Shinhan Bank     |");
        System.out.println("| 4. Woori Bank       |");
        System.out.println("| 5. Hana Bank        |");
        System.out.println("| 6. Exit             |");
        System.out.println("=======================");
        System.out.print("Please enter a number (1 ~ 6): ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String chooseMenu() {
        System.out.println("=============================");
        System.out.println("|         Menu list         |");
        System.out.println("-----------------------------");
        System.out.println("| 1. Transaction            |");
        System.out.println("| 2. Check balance          |");
        System.out.println("| 3. Check all transactions |");
        System.out.println("| 4. Create an account      |");
        System.out.println("| 5. Return to previous     |");
        System.out.println("| 6. Exit                   |");
        System.out.println("=============================");
        System.out.print("Please enter a number (1 ~ 6): ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void transaction(Bank bank) {
        System.out.println("=================");
        System.out.println("|  Transaction  |");
        System.out.println("-----------------");
        System.out.println("| 1. Deposit    |");
        System.out.println("| 2. Withdrawal |");
        System.out.println("| 3. Transfer   |");
        System.out.println("=================");
        System.out.print("Please enter a number (1 ~ 3): ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println();

        if (input.equals("1")) {
            Customer customer = getCustomer(bank);
            bank.getAccount(customer.getId(), customer.getPassword()).deposit();
        } else if (input.equals("2")) {
            Customer customer = getCustomer(bank);
            bank.getAccount(customer.getId(), customer.getPassword()).withdrawal();
        } else if (input.equals("3")) {
            Customer customer = getCustomer(bank);
            bank.getAccount(customer.getId(), customer.getPassword()).transfer();
        } else {
            throw new RuntimeException("Invalid number.");
        }
    }

    public static Bank setDstBank(String bankNumber) {
        if (bankNumber.equals("1")) {
            return TossBank.getInstance();
        } else if (bankNumber.equals("2")) {
            return KbKookminBank.getInstance();
        } else if (bankNumber.equals("3")) {
            return ShinhanBank.getInstance();
        } else if (bankNumber.equals("4")) {
            return WooriBank.getInstance();
        } else if (bankNumber.equals("5")) {
            return HanaBank.getInstance();
        }
        throw new RuntimeException("Invalid Number.");
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
