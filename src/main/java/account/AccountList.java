package account;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountList {

    private final List<Account> accountList;

    public AccountList() {
        this.accountList = new ArrayList<>();
    }

    public void add(Account account) {
        accountList.add(account);
    }

    public void remove(Account account) {
        accountList.remove(account);
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accountList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new NoSuchElementException("Account not found.");
    }

    public Account getAccount(String id, String password) {
        for (Account account : accountList) {
            if (account.getId().equals(id)) {
                if (account.getPassword().equals(password)) {
                    return account;
                }
                throw new NoSuchElementException("Incorrect password.");
            }
        }
        throw new NoSuchElementException("ID not found.");
    }
}
