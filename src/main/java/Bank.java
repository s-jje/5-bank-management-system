import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Account> accountList;
    private final String name;
    public Bank(String name) {
        this.name = name;
        this.accountList = new ArrayList<>();
    }

    public void register(Account account) {

    }

    public void updateAccount(Account account) {

    }

    public void deleteAccount(Account account) {
        this.accountList.remove(account);
    }

    public String getName() {
        return this.name;
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