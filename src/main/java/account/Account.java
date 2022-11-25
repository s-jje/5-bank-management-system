package account;

import util.Formatter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String name;
    private final String id;
    private final String password;
    private final String name;
    private final String bankName;
    private final String accountNumber;
    private final long balance;
    private final List<TransactionData> transactionDataList;


    /**
     * 생성자
     *
     * @param id
     * @param password
     * @param name
     * @param bankName
     * @param accountNumber
     * @param balance
     */
//    public Account(String name, String id, String password, String accountNumber, long balance) {
//        this.name = name;
//    }

    public Account(String id, String password, String name, String bankName, String accountNumber, long balance) {

        this.id = id;
        this.password = password;

        this.name = name;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionDataList = new ArrayList<>();
    }

//    public long deposit ( long amount){
//    public long checkBalance(String id, String password) throws Exception {
////        Bank bank = new Bank();
////        Account account = bank.getAccount(id, password);
////        Long balance = validation_getBalance(id, password, account);
////        if (balance != null) return balance;
////        throw new RuntimeException("잘못된 id/ pw를 입력했습니다");
//        return -1L;
//    }

    private Long validation_getBalance(String id, String password, Account account) {
        if (account.getId().equals(id) && account.getPassword().equals(password)) {

            return account.balance;
        }
        return null;
    }

    public long deposit(long amount) {
        return -1L;
    }

    public long withdrawal ( long amount){
        return -1L;
    }

    public long checkBalance(String id, String password) {
        return 0;
    }

    public void showAllTransactionData() {
        System.out.printf("%-15s %-20s %-15s%40s%20s%n", this.bankName, this.accountNumber, this.name, " ", getCurrentTime());
        System.out.printf("================================================================================================================%n");
        System.out.printf("         Date        |      Description     |       Deposits       |     Withdrawals     |       Balance        %n");
        System.out.printf("----------------------------------------------------------------------------------------------------------------%n");

        if (this.transactionDataList.size() < 1) {
            System.out.printf("%44sThere is no transaction.%44s%n", " ", " ");
            System.out.printf("================================================================================================================%n");
            return;
        }

        for (TransactionData data : this.transactionDataList) {
            if (data.isDeposit()) {
                System.out.printf("%20s%3s%-20s%3s%20s%24s%20s%3s%n", data.getDate(), " ", data.getDestination(), " ", Formatter.formatToWon(data.getAmount()), " ", Formatter.formatToWon(data.getBalance()), " ");
            } else {
                System.out.printf("%20s%3s%-20s%25s%20s%2s%20s%3s%n", data.getDate(), " ", data.getDestination(), " ", Formatter.formatToWon(data.getAmount()), " ", Formatter.formatToWon(data.getBalance()), " ");
            }
        }
        System.out.printf("================================================================================================================%n");
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    /***
     * 계좌 내의 정보 출력을 위한 메서드
     */
    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
    private String getCurrentTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(ZonedDateTime.now());
    }
}