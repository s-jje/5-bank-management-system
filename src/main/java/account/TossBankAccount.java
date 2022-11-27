package account;

import bank.Bank;
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
                prevTime = epochSecond;
            }

            long balance = getBalance() + amount;
            setBalance(balance);

            addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), true, amount, balance, "Toss Bank"));
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

        long balance = getBalance();

        if (balance > 0) {
            System.out.print("Please enter the amount you want to transfer: ");
            long amount = Long.parseLong(scanner.nextLine());

            if (amount > 0) {
                StringBuilder dstDescription = new StringBuilder(dstAccount.getBankName() + " " + dstNum + " " + dstAccount.getName());
                System.out.printf("Would you like to transfer to %s? [yes/no].", dstDescription);
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                    balance -= amount;

                    if (balance >= 0) {
                        dstAccount.receive(this, dstAccount, amount);
                        setBalance(balance);

                        addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), srcNum, false, amount, balance, dstDescription.toString()));
                        System.out.printf("%nTransfer successful! All transfer fees are ₩0 forever at Toss Bank!%n%n");
                    } else {
                        System.out.printf("%nTransfer failed.%n%n");
                    }
                } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                    System.out.printf("%nTransfer process has been canceled.%n%n");
                } else {
                    throw new RuntimeException("Invalid input.");
                }
            } else {
                System.out.printf("%nYou can deposit more than ₩0.%n%n");
            }
        } else {
            System.out.printf("%nYou have no balance to transfer.%n%n");
        }
    }

    @Override
    public void receive(Account srcAccount, Account dstAccount, long amount) {
        long balance = getBalance() + amount;
        dstAccount.setBalance(balance);
        dstAccount.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), dstAccount.getAccountNumber(), true, amount, balance, srcAccount.getBankName() + " " + srcAccount.getAccountNumber()));
    }

    @Override
    public void showBalance() {
        ZonedDateTime zonedDateTime = getCurrentDateTime();
        long epochSecond = convertDateTimeToSecond(zonedDateTime);
        long interest = applyInterest(epochSecond);
        addTransactionData(new TransactionData("-", getAccountNumber(), true, interest, getBalance(), "Toss Bank Interest"));
        System.out.printf("%nYour balance is ₩%s.%n%n", MoneyFormatter.formatToWon(getBalance()));
    }

    private long applyInterest(long epochSecond) {
        long balance = getBalance();
        long interest = (long) (balance * (INTEREST_RATE_PER_SECOND * (epochSecond - prevTime)));
        setBalance(balance + interest);
        prevTime = epochSecond;
        return interest;
    }
}
