
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Account {
<<<<<<< HEAD
    private final String name;
=======

>>>>>>> origin/develop
    private final String id;
    private final String password;
    private final String name;
    private final String accountNumber;
    private long balance;
    private List<TransactionalInfo> transactionalInfoList;


    public Account(String name, String id, String password, String accountNumber, long balance) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionalInfoList = new ArrayList<>();
    }

    public void deposit() {
    public void deposit(long amount) {

    }

    public void withdrawal() {
    public void withdrawal(long amount) {

    }

    public long checkBalance(String name, String password) {
    public long checkBalance(String id, String password) {

        return 0;
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
        for (TransactionalInfo transactionalInfo : this.transactionalInfoList) {
            System.out.println(transactionalInfo.toString());
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
