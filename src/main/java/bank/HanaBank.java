package bank;

import bankaccount.BankAccount;
import bankaccount.HanaBankAccount;
import bankaccount.KbKookminBankAccount;
import useraccount.UserAccount;

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

        UserAccount userAccount = new UserAccount(name, id, pw);
        BankAccount bankAccount = new HanaBankAccount(name, id, pw, getName(), ano, balance);

        List<BankAccount> list = new ArrayList<>();
        list.add(new KbKookminBankAccount(name, id, pw, getName(), ano, 0L));
        getIdAccountListMap().put(id, list);
        getUserAccountList().add(userAccount);

        System.out.println("============[가입정보]===========");
        System.out.println("- 성함 : " + bankAccount.getName());
        System.out.println("- 아이디 : " + bankAccount.getId());
        System.out.println("- 계좌번호 : " + bankAccount.getAccountNumber());
        System.out.println(bankAccount.getName() + "님! 하나은행의 회원이 되신걸 환영합니다 :)");
        System.out.println("==============================");
    }

    @Override
    public void update() {

    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void withdraw() {

    }

    private String createAno() {

        List<BankAccount> bankAccountList = getBankAccountList();
        int size = bankAccountList.size();

        while (true) {
            int num1 = ((int) (Math.random() * 899)) + 100;
            int num2 = ((int) (Math.random() * 899999)) + 100000;
            int num3 = ((int) (Math.random() * 89999)) + 10000;
            String ano = num1 + "-" + num2 + "-" + num3;

            int i;
            for (i = 0; i < size; i++) {
                if (ano.equals(bankAccountList.get(i).getAccountNumber())) {
                    break;
                }
            }

            if (i == size) {
                return ano;
            }
        }
    }
}