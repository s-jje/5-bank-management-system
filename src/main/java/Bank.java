

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Bank {

    private final List<Account> accountList;

    public Bank() {
        this.accountList = new ArrayList<>();
    }


    public void register() {

    }

    public void update() {

    }

    public void delete() {

    }

    public Account getAccount(String accountNumber) {
        for (Account account : this.accountList) {
            if (account.getAccountNumber() == accountNumber) {
                return account;

            }
        }
        throw new NoSuchElementException("해당 계좌번호는 없는 번호 입니다");
    }

    public Account getAccount(String id, String password) {
        for (Account account : this.accountList) {
            if (account.getPassword() == password && account.getId() == id) {
                return account;
            }
        }
        throw new NoSuchElementException("해당 uuid와 name은 존재하지 않습니다");
    }

    public void checkAllAccounts() {
        for (Account account : this.accountList) {
            System.out.println(account.toString());
        }

    }
}
