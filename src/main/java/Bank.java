

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

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

    }

    public Account getAccount(String accountNumber) {
        for (Account account : this.accountList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new NoSuchElementException("해당 계좌번호는 없는 번호 입니다");
    }

    public Account getAccount(String id, String password) {
        for (Account account : this.accountList) {
            if (account.getPassword().equals(password) && account.getId().equals(id)) {
                return account;
            }
        }
        throw new NoSuchElementException("해당 id와 password는 존재하지 않습니다");
    }


    public void showAllAccounts() {
        for (Account account : this.accountList) {
            System.out.println(account.toString());
        }
    }

    public void shotMoney(Account giveAccount, Account sendAccount, long amount) {


    }
}

