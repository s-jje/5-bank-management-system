package bankaccount;

import bank.*;
import util.TimeFormatter;

import java.time.ZonedDateTime;
import java.util.*;

import static util.Time.getCurrentDateTime;

public class KbKookminBankAccount extends BankAccount {

    private String grade;

    public KbKookminBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override //입금
    public void deposit() {
        System.out.println("============================================================================================");
        System.out.println("해당 업무는 입금입니다");
        System.out.println("현재 입금하시려는 계좌는 " + getAccountNumber() + "이며 계좌 주 : " + getName() + " 입니다");
        System.out.println("입금하실 금액을 입력해주십시오");
        long amount = Long.parseLong(scanner.nextLine());
        System.out.println("입금하시는 금액이 " + amount + " 가 맞습니까?");
        System.out.println("맞다면 1, 틀리다면 2를 눌러주십쇼. 2를 누르면 처음으로 돌아갑니다");
        String check = scanner.nextLine();
        if (check.equals("1")) {
            ZonedDateTime zonedDateTime = getCurrentDateTime();
            setBalance(getBalance() + amount);
            System.out.println("입금 완료 되었습니다.");
            addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), true, amount, getBalance(), "KB Bank"));
            System.out.println("현재 계좌 내 금액은 " + getBalance() + "원 입니다");
        } else {
            System.out.println("처음부터 진행해주십시오");
            return;
        }
        System.out.println("============================================================================================");
    }

    @Override // 출금
    public void withdrawal() {
        System.out.println("============================================================================================");
        System.out.println("해당 업무는 출금입니다");
        System.out.println("현재 출금하시려는 계좌는 " + getAccountNumber() + "이며 계좌 주 : " + getName() + " 입니다");
        System.out.println("출금하실 금액을 입력해주십시오");
        long amount = Long.parseLong(scanner.nextLine());

        if (checkBalance(amount)) {
            setBalance(getBalance() - amount);
            System.out.println(amount + "금액이 출금되었습니다");
            ZonedDateTime zonedDateTime = getCurrentDateTime();
            addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, amount, getBalance(), "KB Bank"));

            System.out.println("현재 계좌 내의 남은 잔액은 " + getBalance() + "입니다");
            System.out.println("이용해주셔서 감사합니다");
        }
        System.out.println("============================================================================================");
    }

    @Override // 송금
    public void transfer() {
        System.out.println("============================================================================================");

        List<Bank> bankList = new ArrayList<>(Arrays.asList(
                TossBank.getInstance(), KbKookminBank.getInstance(),
                ShinhanBank.getInstance(), WooriBank.getInstance(),
                HanaBank.getInstance()
        ));
        printBankList();
        String num = scanner.nextLine();
        Bank bank = chooseBankInstance(bankList, num);
        System.out.println("현재 송금하려는 은행은 " + bank.getName() + "입니다");
        KbKookminBank instance = KbKookminBank.getInstance();
        if (bank.equals(instance)) {
            System.out.println("동일 은행끼리의 송금은 수수료가 없습니다");
            System.out.println("돈을 보내고자 하는 계좌를 입력해주십시오");
            BankAccount accountSend = validation(scanner, instance);
            System.out.println("돈을 받고자 하는 계좌를 입력해주십시오");
            BankAccount accountReceive = validation(scanner, bank);
            System.out.println("보내고자 하는 금액을 입력해주십시오");
            long amount = Long.parseLong(scanner.nextLine());

            if (checkBalance(amount)) {
                receive(accountSend, accountReceive, amount);
                ZonedDateTime zonedDateTime = getCurrentDateTime();
                addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, amount, getBalance(), "KB Bank"));

            }

        } else {
            System.out.println("타 은행 송금시 수수료 500원이 부과됩니다");
            System.out.println("보내고자 하는 계좌를 입력해주십시오");
            BankAccount accountSend = validation(scanner, instance);
            System.out.println("받고자 하는 계좌를 입력해주십시오");
            BankAccount accountReceive = validation(scanner, bank);
            System.out.println("보내고자 하는 금액을 입력해주십시오");
            long amount = Long.parseLong(scanner.nextLine());
            if (checkBalance(amount)) {
                receive(accountSend, accountReceive, amount);
                ZonedDateTime zonedDateTime = getCurrentDateTime();
                addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, amount, getBalance(), accountReceive.getBankName()));
            }
        }
        System.out.println("============================================================================================");
    }

    @Override
    public void receive(BankAccount accountSend, BankAccount accountReceive, long amount) {
        String accountNumber = accountReceive.getAccountNumber();
        System.out.println("보내고자 하는 금액 : " + amount + " 보내고자 하는 계좌 번호 : " + accountNumber);
        if (accountSend.getBankName().equals(accountReceive.getBankName())) {
            accountSend.setBalance(accountSend.getBalance() - amount);
            accountReceive.setBalance(accountReceive.getBalance() + amount);
        } else {
            accountSend.setBalance(accountSend.getBalance() - 500);
            accountSend.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), accountReceive.getAccountNumber(), false, 500, accountSend.getBalance(), "Other bank fee"));
            accountSend.setBalance(accountSend.getBalance() - amount);
            accountReceive.setBalance(accountReceive.getBalance() + amount);
        }
        StringBuilder description = new StringBuilder();
        description.append(accountSend.getBankName()).append(" ").append(accountSend.getAccountNumber()).append(" ").append(accountSend.getName());
        accountReceive.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), accountReceive.getAccountNumber(), true, amount, accountReceive.getBalance(), description.toString()));

        System.out.println("송금이 완료되었습니다");
    }

    @Override
    public void showBalance() {
        long balance = getBalance();
        System.out.printf("| %36s |%n", "₩" + balance);
    }

    public BankAccount validation(Scanner scanner, Bank bank) {
        System.out.println("계좌의 id를 입력해주십시오");
        String id = scanner.nextLine();
        System.out.println("계좌의 pw를 입력해주십시오");
        String pw = scanner.nextLine();
        return getBankAccount(id, pw, bank);
    }

    private BankAccount getBankAccount(String id, String pw, Bank bank) {
        Map<String, List<BankAccount>> idAccountListMap = bank.getIdAccountListMap();

        if (idAccountListMap.containsKey(id)) {
            BankAccount bankAccount = idAccountListMap.get(id).get(0);
            if (pw.equals(bankAccount.getPassword())) {
                return bankAccount;
            } else {
                System.out.println("잘못된 비밀번호 입니다.");
            }
        } else {
            System.out.println("해당 id는 없는 id 입니다");
        }
        throw new NoSuchElementException("잘못된 형식을 입력하셨습니다");
    }


    private void printBankList() {
        System.out.println("============================================================================================");
        System.out.println("송금하시려는 계좌의 은행을 골라주십시오");
        System.out.println("현재 Atm기는 국민은행이며 타행으로 송금시 수수료 500원이 차감이 됩니다.");
        System.out.printf("%n======================%n");
        System.out.println("|   Available banks   |");
        System.out.println("----------------------");
        System.out.println("| 1. Toss Bank       |");
        System.out.println("| 2. KB Kookmin Bank |");
        System.out.println("| 3. Shinhan Bank    |");
        System.out.println("| 4. Woori Bank      |");
        System.out.println("| 5. Hana Bank       |");
        System.out.println("| 6. Exit            |");
        System.out.println("=======================");
        System.out.print("Please select a bank [1 ~ 6]: ");
    }

    private Bank chooseBankInstance(List<Bank> bankList, String num) {
        if (num.equals("1")) {
            return bankList.get(0);
        } else if (num.equals("2")) {
            return bankList.get(1);
        } else if (num.equals("3")) {
            return bankList.get(2);
        } else if (num.equals("4")) {
            return bankList.get(3);
        } else if (num.equals("5")) {
            return bankList.get(4);
        } else if (num.equals("6")) {
            System.out.println("종료됩니다.");
            return null;
        }
        throw new RuntimeException("잘못된 숫자를 입력하였습니다");
    }

    private boolean checkBalance(long amount) {
        if (amount > getBalance()) {
            System.out.println("계좌 내의 잔액보다 많은 금액을 입력하셨습니다.");
            System.out.println("처음으로 돌아갑니다");
            return false;
        }
        return true;
    }
}
