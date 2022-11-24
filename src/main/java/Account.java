import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private String password;
    private String accountNumber;
    private long balance;

    private List<TransactionalInfo> transactionalInfoList;

    public Account(String name, String password, String accountNumber, long balance) {
        this.name = name;
        this.password = password;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionalInfoList = new ArrayList<>();
    }

    public void deposit() {

    }

    public void withdrawal() {

    }

    public long checkBalance(String name, String password) {

        return 0;
    }

    public List<TransactionalInfo> getAllTransactionalInfos() {

        return null;
    }
}
