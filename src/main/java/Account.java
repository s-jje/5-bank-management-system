import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String id;
    private final String password;
    private final String name;
    private final String bankName;
    private final String accountNumber;
    private long balance;
    private List<TransactionalInfo> transactionalInfoList;

    public Account(String name, String id, String password, String bankName, String accountNumber, long balance) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionalInfoList = new ArrayList<>();
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

    public List<TransactionalInfo> getAllTransactionalInfos() {

        return null;
    }
}