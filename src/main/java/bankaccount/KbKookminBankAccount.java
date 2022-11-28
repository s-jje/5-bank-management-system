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
        System.out.println("This business is a deposit");
        System.out.println("account number is " + getAccountNumber() + " account owner : " + getName() );
        System.out.print("Please enter the amount: ");
        long amount = Long.parseLong(scanner.nextLine());
        System.out.println("Is the amount you want to deposit" + amount);
        System.out.println("If it's correct, press 1. If it's not, press 2");
        String check = scanner.nextLine();
        if (check.equals("1")) {
            setBalance(getBalance() + amount);
            System.out.println("The deposit has been made.");
            addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), true, amount, getBalance(), "KB Bank"));
            System.out.println("The current amount in the account is " + getBalance() +"₩");
        } else {
            System.out.println("Please start from the beginning");
            return;
        }
        System.out.println("============================================================================================");
    }

    @Override // 출금
    public void withdrawal() {
        System.out.println("============================================================================================");
        System.out.println("The job is withdrawal");
        System.out.println("account number is " + getAccountNumber() + " account owner : " + getName() );
        System.out.println("Please enter the amount you wish to withdraw");
        long amount = Long.parseLong(scanner.nextLine());

        if (checkBalance(amount)) {
            setBalance(getBalance() - amount);
            System.out.println(amount + "₩ has been withdrawn.");
            ZonedDateTime zonedDateTime = getCurrentDateTime();
            addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, amount, getBalance(), "KB Bank"));

            System.out.println("The current amount in the account is " + getBalance() +"₩");
            System.out.println("Thank you for using it");
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
        System.out.println("The bank I'm trying to transfer money to is " + bank.getName());
        KbKookminBank instance = KbKookminBank.getInstance();
        if (bank.equals(instance)) {
            System.out.println("There is no fee between the same banks");
            System.out.println("Please enter your own account");
            BankAccount accountSend = validation(scanner, instance);
            System.out.println("Please enter account number to transfer:");
            BankAccount accountReceive = validation(scanner, bank);
            System.out.print("Please enter the amount: ");
            long amount = Long.parseLong(scanner.nextLine());

            if (checkBalance(amount)) {
                receive(accountSend, accountReceive, amount);
                ZonedDateTime zonedDateTime = getCurrentDateTime();
                addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), false, amount, getBalance(), "KB Bank"));

            }

        } else {
            System.out.println("You will be charged a fee for sending money to other banks");
            System.out.println("Please enter your own account");
            BankAccount accountSend = validation(scanner, instance);
            System.out.println("Please enter account number to transfer:");
            BankAccount accountReceive = validation(scanner, bank);
            System.out.print("Please enter the amount: ");
            long amount = Long.parseLong(scanner.nextLine());
            if (checkBalance(amount)) {

                accountSend.setBalance(accountSend.getBalance() - 500);
                accountSend.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), accountReceive.getAccountNumber(), false, 500, accountSend.getBalance(), "Other bank fee"));

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
        accountSend.setBalance(accountSend.getBalance() - amount);
        accountReceive.setBalance(accountReceive.getBalance() + amount);
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
        System.out.print("Please enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Please enter your Password: ");
        String pw = scanner.nextLine();
        return getBankAccount(id, pw, bank);
    }

    private BankAccount getBankAccount(String id, String pw, Bank bank) {
        Map<String, List<BankAccount>> idAccountListMap = bank.getIdBankAccountListMap();

        if (idAccountListMap.containsKey(id)) {
            BankAccount bankAccount = idAccountListMap.get(id).get(0);
            if (pw.equals(bankAccount.getPassword())) {
                return bankAccount;
            } else {
                System.out.println("this is incorrect password");
            }
        } else {
            System.out.println("This Id does not exist");
        }
        throw new NoSuchElementException("Invalid element");
    }

    private BankAccount getAccount(String accountNumber, List<BankAccount> accountList) {
        for (BankAccount account : accountList) {
            if (accountNumber.equals(account.getAccountNumber())) {
                return account;
            }
            System.out.println("This account number does not exist");
        }
        throw new NoSuchElementException("Invalid element");

    }

    ;

    private void printBankList() {
        System.out.println("============================================================================================");
        System.out.println("Please select the bank you want to transfer money");
        System.out.println("Currently, ATM is Kookmin Bank, and a fee is charged when transferring money to other banks");
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
            System.out.println("stop program.");
            return null;
        }
        throw new NoSuchElementException("Invalid element");

    }

    private boolean checkBalance(long amount) {
        if (amount > getBalance()) {
            System.out.println("It's more than the balance in the account");
            System.out.println("Let's go back to the beginning");
            return false;
        }
        return true;
    }
}
