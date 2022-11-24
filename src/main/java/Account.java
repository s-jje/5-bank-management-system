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

    public void withdrawal() {

    }

    public long checkBalance(UUID uuid, String name) {

        return 0;
    }

    public List<TransactionalInfo> getAllTransactionalInfos() {

        return null;
    }
}
