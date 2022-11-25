
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String name;

    private final String id;
    private final String password;
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


    /***
     * 돈을 넣으면
     * 입금시간 / 계좌번호 / isdeposit
     * @param
     */
    public void deposit(long amount) {
        String date = String.valueOf(LocalDate.now());
//        TransactionalInfo transactionalInfo = new TransactionalInfo() ;
//        transactionalInfoList.add(transactionalInfo);
        this.balance += amount;
    }

    public void withdrawal(long amount) {
        this.balance -= amount;

    }

    public long checkBalance(String id, String password) throws Exception {
        Bank bank = new Bank();
        Account account = bank.getAccount(id, password);
        Long balance = validation_getBalance(id, password, account);
        if (balance != null) return balance;
        throw new RuntimeException("잘못된 id/ pw를 입력했습니다");
    }

    private Long validation_getBalance(String id, String password, Account account) {
        if (account.getId().equals(id) && account.getPassword().equals(password)) {

            return account.balance;
        }
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
