package account;

import bank.*;
import customer.Customer;
import util.MoneyFormatter;
import util.TimeFormatter;

import java.time.ZonedDateTime;
import java.util.*;

import static util.Time.convertDateTimeToSecond;
import static util.Time.getCurrentDateTime;

public class KbKookminBankAccount extends Account {
    private String grade;

    public KbKookminBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override //입금
    public void deposit() {
        Scanner scanner = new Scanner(System.in);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("============================================================================================");
        System.out.println("해당 업무는 출금입니다");
//        Account account = validationSame(scanner);
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
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("보내고자 하는 계좌를 입력해주십시오");
            Account accountSend = validationSame(scanner);
            System.out.println("받고자 하는 계좌를 입력해주십시오");
            Account accountReceive = validationSame(scanner);
            System.out.println("보내고자 하는 금액을 입력해주십시오");
            long amount = Long.parseLong(scanner.nextLine());

            if (checkBalance(amount)) {
                receiveSame(accountSend, accountReceive, amount);
                ZonedDateTime zonedDateTime = getCurrentDateTime();
                addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, amount, getBalance(), "KB Bank"));

            }

        } else {
            System.out.println("타 은행 송금시 수수료 500원이 부과됩니다");
            System.out.println("보내고자 하는 계좌를 입력해주십시오");
            Account accountSend = validationSame(scanner);
            System.out.println("받고자 하는 계좌를 입력해주십시오");
            Account accountReceive = validationDifferent(scanner, bank);
            System.out.println("보내고자 하는 금액을 입력해주십시오");
            long amount = Long.parseLong(scanner.nextLine());
            if (checkBalance(amount)) {
                receiveDifferent(accountSend, accountReceive, amount);
                ZonedDateTime zonedDateTime = getCurrentDateTime();
                addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, amount, getBalance(), accountReceive.getBankName()));

            }


        }
        System.out.println("============================================================================================");


    }

    @Override
    public void receive(String srcBank, String srcAccountNumber, String dstAccountNumber, long amount) {

    }

    private void receiveSame(Account accountSend, Account accountReceive, long amount) {
        String accountNumber = accountReceive.getAccountNumber();
        System.out.println("보내고자 하는 금액 : " + amount + " 보내고자 하는 계좌 번호 : " + accountNumber);
        accountSend.setBalance(accountSend.getBalance() - amount);
        accountReceive.setBalance(accountReceive.getBalance() + amount);
        ZonedDateTime zonedDateTime = getCurrentDateTime();
        addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), accountReceive.getAccountNumber(), true, amount, getBalance(), "KB Bank"));
        System.out.println("송금이 완료되었습니다");
    }

    private void receiveDifferent(Account accountSend, Account accountReceive, long amount) {
        String accountNumber = accountReceive.getAccountNumber();
        System.out.println("보내고자 하는 금액 : " + amount + " 보내고자 하는 계좌 번호 : " + accountNumber);
        accountSend.setBalance(accountSend.getBalance() - amount - 500);
        accountReceive.setBalance(accountReceive.getBalance() + amount);
        ZonedDateTime zonedDateTime = getCurrentDateTime();
        addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), accountReceive.getAccountNumber(), true, amount, getBalance(), accountReceive.getBankName()));

        System.out.println("송금이 완료되었습니다");
    }


    @Override
    public void showBalance() {
        long balance = getBalance();
        System.out.println("%nYour balance is ₩" + balance);

    }


    /***
     * check account through id and pw ( with scanner)
     * @param sc
     * @return
     */
    public Account validationSame(Scanner sc) {
        System.out.println("계좌의 id를 입력해주십시오");
        String id = sc.nextLine();
        System.out.println("계좌의 password를 입력해주십시오");
        String pw = sc.nextLine();
        List<Account> accountList = KbKookminBank.getInstance().getAccountList();
        return getAccount(id, pw, accountList);
    }

    public Account validationDifferent(Scanner scanner, Bank bank) {
        System.out.println("받고자하는 계좌의 id를 입력해주십시오");
        String id = scanner.nextLine();
        System.out.println("받고자하는 계좌의 pw를 입력해주십시오");
        String pw = scanner.nextLine();
        List<Account> accountList = bank.getAccountList();
        return getAccount(id, pw, accountList);
    }

    private Account getAccount(String id, String pw, List<Account> accountList) {
        for (Account account : accountList) {
            if (id.equals(account.getId())) {
                if (pw.equals(account.getPassword())) {
                    return account;
                } else {
                    System.out.println("잘못된 비밀번호 입니다.");
                }
                System.out.println("해당 id는 없는 id 입니다");
            }
        }
        throw new NoSuchElementException("잘못된 형식을 입력하셨습니다");
    }


    private void printBankList() {
        System.out.println("============================================================================================");
        System.out.println("송금하시려는 계좌의 은행을 골라주십시오");
        System.out.println("현재 Atm기는 국민은행이며 타행으로 송금시 수수료 500원이 차감이 됩니다.");
        System.out.println("=======================");
        System.out.println("| Available bank list |");
        System.out.println("-----------------------");
        System.out.println("| 1. Toss Bank        |");
        System.out.println("| 2. KB Kookmin Bank  |");
        System.out.println("| 3. Shinhan Bank     |");
        System.out.println("| 4. Woori Bank       |");
        System.out.println("| 5. Hana Bank        |");
        System.out.println("| 6. back             |");
        System.out.println("=======================");
        System.out.print("Please enter a number (1 ~ 6): ");
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

    /**
     * 계좌에 돈을 확인 후 true / false 반환
     *
     * @param
     * @param amount
     * @return
     */
    private boolean checkBalance(long amount) {
        if (amount > getBalance()) {
            System.out.println("계좌 내의 잔액보다 많은 금액을 입력하셨습니다.");
            System.out.println("처음으로 돌아갑니다");
            return false;
        }
        return true;

    }
}
