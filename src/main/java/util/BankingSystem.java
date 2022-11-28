package util;

import bank.*;
import bankaccount.BankAccount;
import useraccount.UserAccount;

import java.util.List;
import java.util.Scanner;

public class BankingSystem {

    public static String chooseBank() {
        System.out.printf("%n=======================%n");
        System.out.println("|   Available banks    |");
        System.out.println("-----------------------");
        System.out.println("| 1. Toss Bank        |");
        System.out.println("| 2. KB Kookmin Bank  |");
        System.out.println("| 3. Shinhan Bank     |");
        System.out.println("| 4. Woori Bank       |");
        System.out.println("| 5. Hana Bank        |");
        System.out.println("| 6. Exit             |");
        System.out.println("=======================");
        System.out.print("Please select a bank [1 ~ 6]: ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String chooseMenu() {
        System.out.printf("%n=============================%n");
        System.out.println("|            Menu            |");
        System.out.println("-----------------------------");
        System.out.println("| 1. Transaction            |");
        System.out.println("| 2. Check balance          |");
        System.out.println("| 3. Check all transactions |");
        System.out.println("| 4. Create an account      |");
        System.out.println("| 5. Setting                |");
        System.out.println("| 6. Return to previous     |");
        System.out.println("| 7. Exit                   |");
        System.out.println("=============================");
        System.out.print("Please select a menu [1 ~ 7]: ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static BankAccount chooseOneAccount(Bank bank) {
        UserAccount userAccount = getValidUserAccount(bank);
        List<BankAccount> bankAccountList = bank.getIdAccountListMap().get(userAccount.getId());

        if (bankAccountList.size() == 1) {
            return bankAccountList.get(0);
        }

        System.out.printf("%n=======================%n");
        System.out.println("|       Accounts       |");
        System.out.println("-----------------------");

        for (int i = 0; i < bankAccountList.size(); i++) {
            String accountNumber = bankAccountList.get(i).getAccountNumber();
            System.out.printf("| %d. %-16s |%n", i + 1, accountNumber);
        }
        System.out.println("=======================");
        System.out.printf("Please select a account [1 ~ %d]: ", bankAccountList.size());

        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());

        if (1 <= input && input <= bankAccountList.size()) {
            return bankAccountList.get(input - 1);
        }
        throw new RuntimeException("Invalid Number.");
    }

    public static void transaction(Bank bank) {
        BankAccount bankAccount = chooseOneAccount(bank);

        System.out.printf("%n==================%n");
        System.out.println("|  Transactions  |");
        System.out.println("------------------");
        System.out.println("| 1. Deposit     |");
        System.out.println("| 2. Withdrawal  |");
        System.out.println("| 3. Transfer    |");
        System.out.println("==================");
        System.out.print("Please select a transaction [1 ~ 3]: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1")) {
            bankAccount.deposit();
        } else if (input.equals("2")) {
            bankAccount.withdrawal();
        } else if (input.equals("3")) {
            bankAccount.transfer();
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
        UserAccount userAccount = getValidUserAccount(bank);
        List<BankAccount> bankAccounts = bank.getIdAccountListMap().get(userAccount.getId());
        System.out.printf("%n=======================================%n");
        System.out.println("|               Accounts               |");
        System.out.println("---------------------------------------");
        for (BankAccount bankAccount : bankAccounts) {
            bankAccount.showBalance();
        }
        System.out.println("=======================================");
    }

    public static void checkAllTransactions(Bank bank) {
        chooseOneAccount(bank).showAllTransactionData();
    }

    public static void createAccount(Bank bank) {
        bank.register();
    }

    public static void setting(Bank bank) {
        System.out.printf("%n==========================%n");
        System.out.println("|        Settings        |");
        System.out.println("--------------------------");
        System.out.println("| 1. Change account info |");
        System.out.println("| 2. Delete bank account |");
        System.out.println("| 3. Withdraw account    |");
        System.out.println("==========================");
        System.out.print("Please select a setting [1 ~ 3]: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1")) {
            bank.update();
        } else if (input.equals("2")) {
            bank.deleteAccount();
        } else if (input.equals("3")) {
            bank.withdraw();
        } else {
            throw new RuntimeException("Invalid Number.");
        }
    }

    private static UserAccount getValidUserAccount(Bank bank) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter your Password: ");
        String pw = scanner.nextLine();

        return bank.getUserAccount(id, pw);
    }
}
