package bank;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import account.Account;
import java.util.NoSuchElementException;

public class Bank {

    private final List<Account> accountList;
    private final String name;
    public Bank(String name) {
        this.name = name;
        this.accountList = new ArrayList<>();
    }



    public void register(Account account) {

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
        for (Account account : this.accountList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new NoSuchElementException("해당 계좌번호는 없는 번호 입니다");
    }

    public Account getAccount(String id, String password) {
        for (Account account : this.accountList) {
            if (account.getPassword().equals(password) && account.getId().equals(id)) {
                return account;
            }
        }
        throw new NoSuchElementException("해당 id와 password는 존재하지 않습니다");
    }

    public void showAllAccounts() {
        for (Account account : this.accountList) {
            System.out.println(account.toString());
        }
    }

    public void shotMoney(Account giveAccount, Account sendAccount, long amount) {


    }
}
