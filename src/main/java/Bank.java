package main.java;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Bank {

    private List<Account> accountList;
    public Bank() {
        this.accountList = new ArrayList<>();
    }

    public void register() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        UUID uuid = UUID.randomUUID();
        System.out.println("uuid: " + uuid.toString());

        System.out.println("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Enter your accountNumber: ");
        String accountNumber = scanner.nextLine();

        DecimalFormat df = new DecimalFormat();  // 계좌번호를 정규 표현식으로 나타내기
        Number num = df.parse("###-#######-####");
        System.out.println(num.doubleValue());


        System.out.println("Enter your balance: ");
        long balance = Long.parseLong(scanner.nextLine());

        this.accountList.add(new Account(uuid, name, accountNumber, balance));
    }

    public void update() {

    }

    public void delete() {

    }

    public Account getAccount(int accountNumber) {

        return null;
    }

    public Account getAccount(UUID uuid, String name) {
        return null;
    }

    public List<Account> checkAllAccounts() {
        return null;
    }
}
