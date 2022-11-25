package account;

import util.MoneyFormatter;
import util.Time;
import util.TimeFormatter;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private final String name;
    private final String id;
    private final String password;
    private final String bankName;
    private final String accountNumber;
    private long balance;
    private final List<TransactionData> transactionDataList;

    public Account(String name, String id, String password, String bankName, String accountNumber, long balance) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionDataList = new ArrayList<>();
    }

    public abstract void deposit();

    public abstract void withdrawal();

    public void showBalance() {
        System.out.printf("%nYour balance is ₩%s.%n%n", MoneyFormatter.formatToWon(balance));
    }

    public void showAllTransactionData() {
        System.out.printf("%n%-15s %-20s %-15s%40s%20s%n", bankName, accountNumber, name, " ", TimeFormatter.format(Time.getCurrentDateTime()));
        System.out.printf("================================================================================================================%n");
        System.out.printf("         Date        |      Description     |       Deposits       |     Withdrawals     |       Balance        %n");
        System.out.printf("----------------------------------------------------------------------------------------------------------------%n");

        if (transactionDataList.size() < 1) {
            System.out.printf("%44sThere is no transaction.%44s%n", " ", " ");
            System.out.printf("================================================================================================================%n");
            return;
        }

        for (TransactionData data : transactionDataList) {
            if (data.isDeposit()) {
                System.out.printf("%20s%3s%-20s%3s%20s%24s%20s%3s%n", data.getDate(), " ", data.getDestination(), " ", MoneyFormatter.formatToWon(data.getAmount()), " ", MoneyFormatter.formatToWon(data.getBalance()), " ");
            } else {
                System.out.printf("%20s%3s%-20s%25s%20s%2s%20s%3s%n", data.getDate(), " ", data.getDestination(), " ", MoneyFormatter.formatToWon(data.getAmount()), " ", MoneyFormatter.formatToWon(data.getBalance()), " ");
            }
        }
        System.out.printf("================================================================================================================%n%n");
    }

    public void addTransactionData(TransactionData data) {
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

    public List<TransactionData> getTransactionDataList() {
        return transactionDataList;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}