package bank;

import account.Account;
import account.HanaBankAccount;
import account.TossBankAccount;
import customer.Customer;
import util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HanaBank extends Bank {

    static HanaBank instance;

    private HanaBank() {
        super("Hana Bank");
    }

    public static HanaBank getInstance() {
        if (instance == null) {
            instance = new HanaBank();
        }
        return instance;
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void register() {
        System.out.println("==============================");
        System.out.println("★신규 회원등록★");
        System.out.println("==============================");
        System.out.print("고객님의 성함을 입력해주세요 : ");
        String name = sc.nextLine();
        System.out.print("등록하실 ID를 입력해주세요 : ");
        String id = sc.nextLine();
        System.out.print("등록하실 PW를 입력해주세요 : ");
        String pw = sc.nextLine();
        long balance = 0;
        String ano = createAno();

        Customer customer = new Customer(name, id, pw, ano);
        Account account = new HanaBankAccount(name, id, pw, getName(), ano, balance);
        addAccount(account);
        addCustomer(customer);

        System.out.println("============[가입정보]===========");
        System.out.println("- 성함 : " + account.getName());
        System.out.println("- 아이디 : " + account.getId());
        System.out.println("- 계좌번호 : " + account.getAccountNumber());
        System.out.println(account.getName() + "님! 하나은행의 회원이 되신걸 환영합니다 :)");
        System.out.println("==============================");
    }


    @Override
    public void updateAccount(Account account) {


    }

    private String createAno() {
        int num1 = ((int) (Math.random() * 899)) + 100;
        int num2 = ((int) (Math.random() * 899999)) + 100000;
        int num3 = ((int) (Math.random() * 89999)) + 10000;
        String ano = num1 + "-" + num2 + "-" + num3;

        while (true) {
            if (getAccountList().equals(ano)) {
                num1 = ((int) (Math.random() * 899)) + 100;
                num2 = ((int) (Math.random() * 899999)) + 100000;
                num3 = ((int) (Math.random() * 89999)) + 10000;
                ano = num1 + "-" + num2 + "-" + num3;
                break;
            }
        }
        return ano;
    }


}
