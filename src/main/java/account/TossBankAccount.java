package account;

import customer.Customer;
import util.MoneyFormatter;

import java.util.Scanner;

public class TossBankAccount extends Account {

    public TossBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit() {
        System.out.print("Please enter the amount you want to deposit: ");
        Scanner scanner = new Scanner(System.in);
        long amount = Long.parseLong(scanner.nextLine());

        if (amount > 0) {
            long balance = getBalance() + amount;
            setBalance(balance);
            addTransactionData(new TransactionData(getAccountNumber(), true, amount, balance, "Toss Bank"));
            System.out.printf("%nDeposit successful! Your balance is ₩%s.%n%n", MoneyFormatter.formatToWon(balance));
        } else {
            System.out.printf("%nYou can deposit more than ₩0.%n%n");
        }
    }

    @Override
    public void withdrawal() {
        if (getBalance() > 0) {
            System.out.print("Please enter the amount you want to withdrawal: ");
            Scanner scanner = new Scanner(System.in);
            long amount = Long.parseLong(scanner.nextLine());

            if (amount > 0) {
                long balance = getBalance() - amount;

                if (balance >= 0) {
                    setBalance(balance);
                    addTransactionData(new TransactionData(getAccountNumber(), false, amount, balance, "Toss Bank"));
                    System.out.printf("%nWithdrawal successful! Your balance is ₩%s.%n%n", MoneyFormatter.formatToWon(balance));
                } else {
                    System.out.printf("%nWithdrawal failed. You have ₩%s in your account.%n%n", balance);
                }
            } else {
                System.out.printf("%nYou can deposit more than ₩0.%n%n");
            }
        } else {
            System.out.printf("%nYou have no balance to withdrawal.%n%n");
        }
    }
}
