import java.util.List;
import java.util.ArrayList;

public class Account {

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

    public void deposit(long amount) {

    }

    public void withdrawal(long amount) {

    }

    public long checkBalance(String id, String password) {

        return 0;
    }

    public List<TransactionalInfo> getAllTransactionalInfos() {

        return null;
    }
}