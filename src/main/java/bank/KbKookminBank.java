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

    // 계정생성 메서드
    @Override
    public void register() {
        createNewAccount();
    }

    // 계정생성 메서드
    private void createNewAccount() {
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Please enter the ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter the Password: ");
        String pw = scanner.nextLine();

        System.out.println("we make your accountNumber with random number program");
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
        while (true) {
            System.out.println("Please press the number of jobs you want");
            System.out.println("The name change is 1");
            System.out.println("The password change is 2");
            System.out.println("To go back, press 3");

            String num = scanner.nextLine();
            if (num.equals("1")) {
                System.out.println("receive a name to change it");
                String afterName = scanner.nextLine();
                bankAccount.setName(afterName);
                System.out.println("Changed to "+ bankAccount.getName());
                System.out.println("Thank you for using it");
                break;

            } else if ((num.equals("2"))) {
                System.out.println("receive a password to change it");
                String afterPassword = scanner.nextLine();
                System.out.println("take it to double-check");
                String checkPassword = scanner.nextLine();
                if (afterPassword.equals(checkPassword)) {
                    System.out.println("Change password entered correctly");
                    bankAccount.setPassword(afterPassword);
                    System.out.println("Password change completed.\n");
                    break;
                }
            } else if (num.equals("3")) {
                System.out.println("go back to the beginning");
                break;
            } else {
                System.out.println("entered it incorrectly");
            }
        }
        System.out.println("Thank you for using it");
    }

    // 계좌 내 이름 / 비밀번호 변경 메서드
    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Currently, only the name and password are allowed");
        while (true) {
            System.out.println("Please enter the account warning ID you want to change");
            String id = scanner.nextLine();
            System.out.println("Please enter the password for your account");
            String password = scanner.nextLine();
            if (isExistId(id)) {
                BankAccount bankAccount = getIdAccountListMap().get(id).get(0);
                if (password.equals(bankAccount.getPassword())) {
                    updateAccount(bankAccount);
                    break;
                }
            }
            else{
                System.out.println("Member who does not exist. Let's go back to the beginning.");
                break;
            }
        }
    }
    // 계좌 삭제 메서드
    @Override
    public void deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please type your id");
        String id = scanner.nextLine();
        System.out.println("please type your password");
        String pw = scanner.nextLine();


    }
    // 회원탈퇴 메서드
    @Override
    public void withdraw() {

    }
}
