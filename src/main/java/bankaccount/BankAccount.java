package bankaccount;

import util.MoneyFormatter;
import util.ScannerUtil;
import util.Time;
import util.TimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 추상 클래스
public abstract class BankAccount {

    // 클래스를 이루는 변수들
    private String name;
    private final String id;
    private String password;
    private final String bankName;
    private final String accountNumber; // 계좌번호
    private long balance; // 예금
    private final List<TransactionData> transactionDataList; // 거래내역 저장을 위한 리스트

    Scanner scanner = ScannerUtil.getScanner();

    public BankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
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

    public abstract void transfer();

    public abstract void receive(BankAccount srcBankAccount, BankAccount dstBankAccount, long amount);

    public abstract void showBalance();

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

    public void addTransactionData (TransactionData data){
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