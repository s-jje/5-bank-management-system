package account;

import customer.Customer;
import util.MoneyFormatter;

import java.util.Scanner;

public class TossBankAccount extends Account {

    public TossBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit(Customer customer) {
        System.out.print("Please enter the amount you want to deposit: ");
        Scanner scanner = new Scanner(System.in);
        long amount = Long.parseLong(scanner.nextLine());

        if (amount > 0) {
            long balance = getBalance() + amount;
            setBalance(balance);
            addTransactionData(new TransactionData(customer.getAccountNumber(), true, amount, balance, "Toss Bank"));
            System.out.println("Deposit successful!");
            System.out.printf("Your balance is ₩%s%n", MoneyFormatter.formatToWon(balance));
        } else {
            System.out.println("You can deposit more than ₩0.");
        }
    }

    @Override
    public void withdrawal(Customer customer) {
        System.out.print("Please enter the amount you want to withdrawal: ");
        Scanner scanner = new Scanner(System.in);
        long amount = Long.parseLong(scanner.nextLine());

        if (amount > 0) {
            long balance = getBalance() - amount;

            if (balance >= 0) {
                setBalance(balance);
                addTransactionData(new TransactionData(customer.getAccountNumber(), false, amount, balance, "Toss Bank"));
                System.out.println("Withdrawal successful!");
                System.out.printf("Your balance is ₩%s%n", MoneyFormatter.formatToWon(balance));
            } else {
                System.out.printf("Withdrawal failed. You have ₩%s in your account.%n", balance);
            }
        } else {
            System.out.println("You can deposit more than ₩0.");
        }
    }
}
