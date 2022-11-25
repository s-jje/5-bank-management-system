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

    public long deposit ( long amount){
        return -1L;
    }

    public long withdrawal ( long amount){
        return -1L;
    }

<<<<<<< HEAD:src/main/java/Account.java
    public long checkBalance (String id, String password){

        return 0;
    }

    public List<TransactionalInfo> getAllTransactionalInfos () {
=======
    public long checkBalance(String id, String password) {
        return 0;
    }

    public void showAllTransactionData() {
>>>>>>> 32878b176c7d8921a951813de2d3a9a369f3107c:src/main/java/account/Account.java

    }
}