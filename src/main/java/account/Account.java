package account;

import account.TransactionData;
import bank.Bank;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String name;
    private final String id;
    private final String password;
    private final String bankName;
    private final String accountNumber;
    private long balance;
    private List<TransactionData> transactionDataList;


    /**
     * 생성자
     *
     * @param name
     * @param id
     * @param password
     * @param accountNumber
     * @param balance
     */
//    public Account(String name, String id, String password, String accountNumber, long balance) {
//        this.name = name;
//    }

    public Account(String name, String id, String password, String bankName, String accountNumber, long balance) {
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
     * 계좌내의 TransactionalInfo 객체를 불러와서 toString 메서드 실행
     * @return searchTransactionalInfo List
     */
    public void getAllTransactionalInfos() {
        for (TransactionData transactionalData : this.transactionDataList) {
            System.out.println(transactionDataList.toString());
        }
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