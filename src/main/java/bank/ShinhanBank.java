package bank;

import bankaccount.BankAccount;
import bankaccount.ShinhanBankAccount;
import useraccount.UserAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ShinhanBank extends Bank {

    static ShinhanBank instance;

    private ShinhanBank() {
        super("Shinhan Bank");
    }

    public static ShinhanBank getInstance() {
        if (instance == null) {
            instance = new ShinhanBank();
        }
        return instance;
    }

    @Override
    public void register() {
        System.out.printf("%n");
        System.out.print("이름을 입력해 주세요: ");
        String name = scanner.nextLine();
        System.out.print("사용하실 ID를 입력해 주세요: ");
        String id = scanner.nextLine();

        System.out.print("사용하실 PASSWORD를 입력해 주세요: ");
        String pw = scanner.nextLine();
        System.out.print("한 번 더 입력해 주세요: ");
        String pwCheck = scanner.nextLine();

        // 비밀번호 일치하는지 확인
        if (!pw.equals(pwCheck)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        // 계좌번호 정규표현식으로 입력
        String pattern = "110-\\d{3}-\\d{6}";
        System.out.println("사용하실 계좌번호를 입력해 주세요");
        System.out.print("형식) 110-xxx-xxxxxx : ");
        String accountNumber = scanner.next();
        if(!Pattern.matches(pattern,accountNumber)){
            System.out.println("잘못된 형식입니다.");
            return ;
        }

        // 계좌번호 랜덤 생성
//        String accountNumber = "110-" + (int) ((Math.random() * 999) + 1) + "-" + (int) ((Math.random() * 999999) + 1);
        List<BankAccount> list = new ArrayList<>();
        list.add(new ShinhanBankAccount(name, id, pw, getName(), accountNumber, 0L));
        getIdBankAccountListMap().put(id, list);
        getUserAccountList().add(new UserAccount(name, id, pw));
        // 계좌번호 출력
        System.out.printf("Account registration successful! Account Number is %s%n%n", accountNumber);
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
}
