import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Account> accountList;

    public Bank() {
        this.accountList = new ArrayList<>();
    }

    public void register(Account account) {

    }

    public void updateAccount(Account account) {

    }

    public void deleteAccount(Account account) {
        this.accountList.remove(account);
    }

    public Account getAccount(String accountNumber) {

        return null;
    }

    public Account getAccount(String id, String password) {
        return null;
    }

    public List<Account> showAllAccounts() {
        return null;
    }
}