import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Account> accountList;
    private final String name;
    public Bank(String name) {
        this.name = name;
        this.accountList = new ArrayList<>();
    }

    public void register() throws ParseException {

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

        DecimalFormat df = new DecimalFormat();
        Number num = df.parse("000-000000");

        System.out.println("Enter your Balance: ");
        long balance = Long.parseLong(scanner.nextLine());

        this.accountList.add(new Account(name, id, password, bankName, accountNumber, balance));
        System.out.println("Your account has been created!");
        System.out.printf("ID: %s name: %s account number: %s%n", id, name, accountNumber);
    }

    public void updateAccount(Account account) {

    }

    public void deleteAccount(Account account) {
        this.accountList.remove(account);
    }

    public String getName() {
        return this.name;
    }

    public Account getAccount(String accountNumber) {

        return null;
    }

    public Account getAccount(String id, String password) {
        return null;
    }

    public List<Account> showAllAccounts() {
        return null;
    }
}