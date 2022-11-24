
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Account {

    private UUID uuid;
    private String name;

    private String accountNumber;

    private long balance;
    private List<TransactionalInfo> transactionalInfoList;
    public Account(UUID uuid, String name, String accountNumber, long balance, List<TransactionalInfo> transactionalInfos) {
        this.uuid = uuid;
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit() {

    }

    public void withdrawl() {

    }

    public long checkBalance(UUID uuid, String name) {

        return 0;
    }

    public UUID getUuid() {
        return uuid;
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
    public void getAllTranscationalInfos() {
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
