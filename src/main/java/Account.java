import java.util.List;
import java.util.ArrayList;

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