package main.java;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Bank {

    private List<Account> accountList;

    public void register() {

    }

    public void update() {

    }

    public void delete() {

    }

    public Account getAccount(String accountNumber) {
        for (Account account : this.accountList) {
            if(account.getAccountNumber() == accountNumber){
                return account;

            }
        }
        throw new NoSuchElementException("해당 계좌번호는 없는 번호 입니다");
    }

    public Account getAccount(UUID uuid, String name) {
        for (Account account : this.accountList) {
            if(account.getUuid() == uuid && account.getName()==name){
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
