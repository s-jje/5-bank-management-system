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

    private final double INTEREST_RATE_PER_SECOND = 3.1709792e-9;
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
            ZonedDateTime zonedDateTime = getCurrentDateTime();
            long epochSecond = convertDateTimeToSecond(zonedDateTime);

            if (prevTime == 0) {
                setPrevTime(epochSecond);
            }

            long balance = getBalance() + amount;
            setBalance(balance);
            addTransactionData(new TransactionData(getAccountNumber(), true, amount, balance, "Toss Bank"));
            System.out.printf("%nDeposit successful! Interest rate is %.1f%%.%n%n", INTEREST_RATE_PER_SECOND * 100.0 * 60.0 * 60.0 * 24.0 * 365.0);
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

        String srcNum = getAccountNumber();
        String dstNum = scanner.nextLine();
        Account dstAccount = dstBank.getAccount(dstNum);

        if (getBalance() > 0) {
            System.out.print("Please enter the amount you want to transfer: ");
            long amount = Long.parseLong(scanner.nextLine());

            if (amount > 0) {
                long balance = getBalance() - amount;

                if (balance >= 0) {
                    dstAccount.receive(getBankName(), srcNum, dstNum, amount);
                    setBalance(balance);

                    StringBuilder dstStr = new StringBuilder(dstAccount.getBankName() + " " + dstNum);
                    addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), srcNum, false, amount, balance, dstStr.toString()));
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
    public void receive(String srcBank, String srcAccount, String dst, long amount) {
        Bank bank = TossBank.getInstance();
        Account account = bank.getAccount(dst);

        long balance = getBalance() + amount;
        account.setBalance(balance);
        account.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), dst, true, amount, balance, srcBank + " " + srcAccount));
    }

    @Override
    public void showBalance() {
        ZonedDateTime zonedDateTime = getCurrentDateTime();
        long epochSecond = convertDateTimeToSecond(zonedDateTime);
        long interest = applyInterest(epochSecond);
        addTransactionData(new TransactionData("-", getAccountNumber(), true, interest, getBalance(), "Toss Bank Interest"));
        System.out.printf("%nYour balance is ₩%s.%n%n", MoneyFormatter.formatToWon(getBalance()));
    }

    private void setPrevTime(long epochSecond) {
        this.prevTime = epochSecond;
    }

    private long applyInterest(long epochSecond) {
        long balance = getBalance();
        long interest = (long) (balance * (INTEREST_RATE_PER_SECOND * (epochSecond - prevTime)));
        setBalance(balance + interest);
        setPrevTime(epochSecond);
        return interest;
    }
}
