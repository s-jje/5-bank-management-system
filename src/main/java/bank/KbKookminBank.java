package bank;

import bankaccount.BankAccount;
import bankaccount.KbKookminBankAccount;
import useraccount.UserAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KbKookminBank extends Bank {

    static KbKookminBank instance;

    private KbKookminBank() {
        super("KB Kookmin Bank");
    }

    public static KbKookminBank getInstance() {
        if (instance == null) {
            instance = new KbKookminBank();
        }
        return instance;
    }

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("신규계좌 등록을 원할시 1번, 기존 계좌 정보 변경을 원하신다면 2번을 눌러주십쇼");
        String num = scanner.nextLine();
        if (num.equals("1")) {
            createNewAccount(scanner);
        } else if (num.equals("2")) {
            System.out.println("은행 업무상 현재는 이름 및 비밀번호만 수정이 가능합니다");
            while (true) {
                System.out.println("먼저 변경하고자 하는 계좌 주의 ID을 입력해주십시오");
                String id = scanner.nextLine();
                System.out.println("계좌의 비밀번호를 입력해주십시오");
                String password = scanner.nextLine();
                if (isExistId(id)) {
                    BankAccount bankAccount = getIdAccountListMap().get(id).get(0);
                    if (password.equals(bankAccount.getPassword())) {
                        updateAccount(bankAccount);
                        break;
                    }
                }
            }
        }
    }

    // 계정생성 메서드
    private void createNewAccount(Scanner scanner) {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Please enter the ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter the Password: ");
        String pw = scanner.nextLine();

        System.out.println("임의의 변수를 통해 계좌번호를 생성하겠습니다");
        String accountNumber = makeAccountNumber();

        List<BankAccount> list = new ArrayList<>();
        list.add(new KbKookminBankAccount(name, id, pw, getName(), accountNumber, 0L));
        getIdAccountListMap().put(id, list);
        getUserAccountList().add(new UserAccount(name, id, pw));

        System.out.printf("Account registration successful!%n");
        System.out.println("your accountNumber is " + accountNumber);
    }

    private String makeAccountNumber() {
        String firstNumber = String.valueOf((int) (Math.random() * 1000));
        String middleNumber = String.valueOf((int) (Math.random() * 100));
        String lastNumber = String.valueOf((int) (Math.random() * 1000000));

        return firstNumber + "-" + middleNumber + "-" + lastNumber;
    }

    // 계정 정보 수정 메서드
    public void updateAccount(BankAccount bankAccount) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("원하시는 업무의 숫자를 눌러주십쇼");
            System.out.println("이름 변경은 1번입니다");
            System.out.println("비밀번호 변경은 2번입니다.");
            System.out.println("뒤로 돌아가기는 3번을 눌러주십쇼");

            String num = scanner.nextLine();
            if (num.equals("1")) {
                System.out.println("변경하고자하는 이름 입력받겠습니다");
                String afterName = scanner.nextLine();
                bankAccount.setName(afterName);
                System.out.println(bankAccount.getName() + "으로 변경되었습니다");
                System.out.println("이용해주셔서 감사합니다");
                break;

            } else if ((num.equals("2"))) {
                System.out.println("변경하고자하는 비밀번호 입력받겠습니다");
                String afterPassword = scanner.nextLine();
                System.out.println("재확인을 위해 다시 한번 입력받겠습니다");
                String checkPassword = scanner.nextLine();
                if (afterPassword.equals(checkPassword)) {
                    System.out.println("변경 비밀번호가 올바르게 입력되었습니다");
                    bankAccount.setPassword(afterPassword);
                    System.out.println("비밀번호 변경이 완료되었습니다.");
                    break;
                }
            } else if (num.equals("3")) {
                System.out.println("처음으로 돌아갑니다.");
                break;
            } else {
                System.out.println("잘못 입력하였습니다");
            }
        }
        System.out.println("이용해주셔서 감사합니다");
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