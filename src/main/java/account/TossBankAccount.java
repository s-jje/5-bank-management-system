package account;

import bank.Bank;
import bank.TossBank;
import util.BankingSystem;
import util.MoneyFormatter;
import util.TimeFormatter;

import java.time.ZonedDateTime;
import java.util.Scanner;

import static util.Time.convertDateTimeToSecond;
import static util.Time.getCurrentDateTime;

public class TossBankAccount extends Account {

    private final double INTEREST_RATE = 5.5;
    private long prevTime;

    public TossBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the amount you want to deposit: ");
        long amount = Long.parseLong(scanner.nextLine());

        if (amount > 0) {
            long balance = getBalance() + amount;
            setBalance(balance);

            addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), true, amount, balance, "Toss Bank"));
            System.out.printf("%nDeposit successful!%n%n");
        } else {
            System.out.printf("%nYou can deposit more than ₩0.%n%n");
        }
    }

    @Override
    public void withdrawal() {
        if (getBalance() > 0) {
            System.out.print("Please enter the amount you want to withdrawal: ");
            Scanner scanner = new Scanner(System.in);
            long amount = Long.parseLong(scanner.nextLine());

            if (amount > 0) {
                long balance = getBalance() - amount;

                if (balance >= 0) {
                    setBalance(balance);

                    addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), false, amount, balance, "Toss Bank"));
                    System.out.printf("%nWithdrawal successful!%n%n");
                } else {
                    System.out.printf("%nWithdrawal failed.%n%n");
                }
            } else {
                System.out.printf("%nYou can deposit more than ₩0.%n%n");
            }
        } else {
            System.out.printf("%nYou have no balance to withdrawal.%n%n");
        }
    }

    public void transfer() {
        String bankNumber = BankingSystem.chooseBank();
        if (bankNumber.equals("6")) {
            return;
        }

        Bank dstBank = BankingSystem.setDstBank(bankNumber);
        System.out.print("Please enter the account number: ");
        Scanner scanner = new Scanner(System.in);

        String accountNumber = scanner.nextLine();
        Account dstAccount = dstBank.getAccount(accountNumber);

        if (getBalance() > 0) {
            System.out.print("Please enter the amount you want to transfer: ");
            long amount = Long.parseLong(scanner.nextLine());

            if (amount > 0) {
                long balance = getBalance() - amount;

                if (balance >= 0) {
                    dstAccount.receive(accountNumber, amount);
                    setBalance(balance);

                    String dst = dstAccount.getBankName() + " " + dstAccount.getAccountNumber();
                    addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), false, amount, balance, dst));
                    System.out.printf("%nTransfer successful!%n%n");
                } else {
                    System.out.printf("%nTransfer failed.%n%n");
                }
            } else {
                System.out.printf("%nYou can deposit more than ₩0.%n%n");
            }
        } else {
            System.out.printf("%nYou have no balance to transfer.%n%n");
        }
    }

    @Override
    public void receive(String accountNumber, long amount) {
        Bank bank = TossBank.getInstance();
        bank.getAccount(accountNumber).setBalance(getBalance() + amount);
    }

    @Override
    public void showBalance() {
        ZonedDateTime zonedDateTime = getCurrentDateTime();
        long epochSecond = convertDateTimeToSecond(zonedDateTime);
        long interest = applyInterest(epochSecond);
        if (interest > 0) {
            addTransactionData(new TransactionData("-", getAccountNumber(), true, interest, getBalance(), "Toss Bank Interest"));
        }
        System.out.printf("%nYour balance is ₩%s.%n%n", MoneyFormatter.formatToWon(getBalance()));
    }

    private void setPrevTime(long epochSecond) {
        this.prevTime = epochSecond;
    }

    private long applyInterest(long epochSecond) {
        if (prevTime == 0) {
            setPrevTime(epochSecond);
        }
        long interest = (long) (INTEREST_RATE * (epochSecond - prevTime));
        setBalance(getBalance() + interest);
        setPrevTime(epochSecond);
        return interest;
    }
}
