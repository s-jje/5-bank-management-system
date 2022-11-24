package account;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String id;
    private final String password;
    private final String name;
    private final String bankName;
    private final String accountNumber;
    private long balance;
    private List<TransactionData> transactionDataList;

    public Account(String name, String id, String password, String bankName, String accountNumber, long balance) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionDataList = new ArrayList<>();
    }

    public long deposit(long amount) {
        return -1L;
    }

    public long withdrawal(long amount) {
        return -1L;
    }

    public long checkBalance(String id, String password) {

        return 0;
    }

    public List<TransactionData> showAllTransactionData() {

        return null;
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
}