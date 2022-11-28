package bankaccount;

import util.MoneyFormatter;
import util.ScannerUtil;
import util.Time;
import util.TimeFormatter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.Time.convertDateTimeToSecond;
import static util.Time.getCurrentDateTime;

public abstract class BankAccount implements Transaction {

    private String name;
    private final String id;
    private String password;
    private final String bankName;
    private final String accountNumber;
    private long balance;
    private final List<TransactionData> transactionDataList;

    private final double INTEREST_RATE_PER_SECOND;
    private final int TRANSFER_FEE;
    private long prevTime;

    Scanner scanner = ScannerUtil.getScanner();

    public BankAccount(String name, String id, String password, String bankName, String accountNumber, long balance, double interestRate, int transferFee) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionDataList = new ArrayList<>();
        this.INTEREST_RATE_PER_SECOND = interestRate;
        this.TRANSFER_FEE = transferFee;
    }

    @Override
    public void deposit() {
        System.out.print("Please enter the amount you want to deposit: ");
        long amount = Long.parseLong(scanner.nextLine());

        if (amount > 0) {
            ZonedDateTime zonedDateTime = getCurrentDateTime();
            long epochSecond = convertDateTimeToSecond(zonedDateTime);

            if (prevTime == 0) {
                prevTime = epochSecond;
            }

            long balance = getBalance() + amount;
            StringBuilder description = new StringBuilder();
            description.append(getBankName()).append(" ").append(getAccountNumber()).append(" ").append(getName()).append("(Me)");
            setBalance(balance);
            addTransactionData(new TransactionData(TimeFormatter.format(zonedDateTime), getAccountNumber(), true, amount, balance, description.toString()));
            System.out.printf("%nDeposit successful! The interest rate is %.1f%%%n", INTEREST_RATE_PER_SECOND * 100.0 * 60.0 * 60.0 * 24.0 * 365.0);
        } else {
            System.out.printf("%nYou can deposit more than ₩0.%n");
        }
    }

    @Override
    public void withdrawal() {
        if (getBalance() > 0) {
            System.out.print("Please enter the amount you want to withdrawal: ");
            long amount = Long.parseLong(scanner.nextLine());

            if (amount > 0) {
                long balance = getBalance() - amount;

                if (balance >= 0) {
                    StringBuilder description = new StringBuilder();
                    description.append(getBankName()).append(" ").append(getAccountNumber()).append(" ").append(getName()).append("(Me)");
                    setBalance(balance);
                    addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), getAccountNumber(), false, amount, balance, description.toString()));
                    System.out.printf("%nWithdrawal successful!%n");
                } else {
                    System.out.printf("%nWithdrawal failed.%n");
                }
            } else {
                System.out.printf("%nYou can deposit more than ₩0.%n");
            }
        } else {
            System.out.printf("%nYou have no balance to withdrawal.%n");
        }
    }

    @Override
    public void receive(BankAccount srcBankAccount, BankAccount dstBankAccount, long amount) {
        if (prevTime == 0) {
            prevTime = convertDateTimeToSecond(getCurrentDateTime());
        }
        long balance = getBalance() + amount;
        dstBankAccount.setBalance(balance);
        dstBankAccount.addTransactionData(new TransactionData(TimeFormatter.format(getCurrentDateTime()), dstBankAccount.getAccountNumber(), true, amount, balance, srcBankAccount.getBankName() + " " + srcBankAccount.getAccountNumber()));
    }

    public void showBalance() {
        ZonedDateTime zonedDateTime = getCurrentDateTime();
        long epochSecond = convertDateTimeToSecond(zonedDateTime);
        long interest = applyInterest(epochSecond);
        String accountNumber = getAccountNumber();

        if (interest > 0) {
            addTransactionData(new TransactionData("-", accountNumber, true, interest, getBalance(), "Toss Bank Interest"));
        }
        System.out.printf("| %-16s: %21s |%n", accountNumber, MoneyFormatter.formatToWon(getBalance()));
    }

    public void showAllTransactionData() {
        System.out.printf("%n%-16s%-20s%-16s%40s%40s%n", bankName, accountNumber, name, " ", TimeFormatter.format(Time.getCurrentDateTime()));
        System.out.printf("====================================================================================================================================%n");
        System.out.printf("         Date        |                Description               |       Deposits       |     Withdrawals     |       Balance        %n");
        System.out.printf("------------------------------------------------------------------------------------------------------------------------------------%n");

        if (transactionDataList.size() < 1) {
            System.out.printf("%51sThere is no transaction.%51s%n", " ", " ");
            System.out.printf("====================================================================================================================================%n");
            return;
        }

        for (TransactionData data : transactionDataList) {
            if (data.isDeposit()) {
                System.out.printf("%20s%3s%-41s%3s%20s%24s%20s%3s%n", data.getDate(), " ", data.getDescription(), " ", MoneyFormatter.formatToWon(data.getAmount()), " ", MoneyFormatter.formatToWon(data.getBalance()), " ");
            } else {
                System.out.printf("%20s%3s%-41s%25s%20s%2s%20s%3s%n", data.getDate(), " ", data.getDescription(), " ", MoneyFormatter.formatToWon(data.getAmount()), " ", MoneyFormatter.formatToWon(data.getBalance()), " ");
            }
        }
        System.out.printf("====================================================================================================================================%n");
    }

    protected abstract String formatAccountNumber(String accountNumber);

    private long applyInterest(long epochSecond) {
        long balance = getBalance();
        long interest = (long) (balance * (INTEREST_RATE_PER_SECOND * (epochSecond - prevTime)));
        setBalance(balance + interest);
        prevTime = epochSecond;
        return interest;
    }

    public void addTransactionData(TransactionData data){
        transactionDataList.add(data);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public long getBalance() {
        return balance;
    }

    public int getTransferFee() {
        return TRANSFER_FEE;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setBalance(long balance){
        this.balance = balance;
    }
}