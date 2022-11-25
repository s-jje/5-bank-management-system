package bank;

import account.Account;

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
        String password = scanner.nextLine();

        System.out.println("Enter your BankName: ");
        String bankName = scanner.nextLine();

        System.out.println("Enter your AccountNumber: ");
        System.out.println("###-###### 이 형식으로 적어주세요");
        String accountNumber = scanner.nextLine();

//        DecimalFormat df = new DecimalFormat();
//        Number num = df.parse("000-000000");
//
//        System.out.println("Enter your Balance: ");
//        long balance = Long.parseLong(scanner.nextLine());
//
//        this.accountList.add(new Account(name, id, password, bankName, accountNumber, balance));
//        System.out.println("Your account has been created!");
//        System.out.printf("ID: %s name: %s account number: %s%n", id, name, accountNumber);
    }

    @Override
    public void updateAccount(Account account) {

    }
}
