package bank;

import account.Account;
import account.TossBankAccount;
import customer.Customer;

import java.util.Scanner;

public class TossBank extends Bank {

    static TossBank instance;

    public TossBank() {
        super("Toss Bank");
    }

    public static TossBank getInstance() {
        if (instance == null) {
            return new TossBank();
        }
        return instance;
    }

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Please enter the ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter the Password: ");
        String pw = scanner.nextLine();

        System.out.println();
        String accountNumber = "123-123456";

        getAccountList().add(new TossBankAccount(name, id, pw, getName(), accountNumber, 0L));
        getCustomerList().add(new Customer(name, id, pw, accountNumber));
        System.out.printf("Account registration successful!%n%n");
    }

    @Override
    public void updateAccount(Account account) {

    }
}
