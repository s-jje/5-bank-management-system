package bank;

import account.Account;
import account.ShinhanBankAccount;
import customer.Customer;

import java.util.Scanner;

public class ShinhanBank extends Bank {

    static ShinhanBank instance = new ShinhanBank();

    private ShinhanBank() {
        super("Shinhan Bank");
    }

    public static ShinhanBank getInstance() {
        return instance;
    }

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your Id: ");
        String id = scanner.nextLine();

        System.out.println("Enter your Password: ");
        String pw = scanner.nextLine();

        // 계좌번호 랜덤 생성
        String accountNumber = "110-" + (int) ((Math.random() * 999) + 1) + "-" + (int) ((Math.random() * 999999) + 1);

        getAccountList().add(new ShinhanBankAccount(name, id, pw, getName(), accountNumber, 0L));
        getCustomerList().add(new Customer(name, id, pw, accountNumber));

        // 계좌번호 출력
        System.out.printf("Account registration successful! Account Number is %s%n%n", accountNumber);
    }

    @Override
    public void updateAccount(Account account) {

    }
}
