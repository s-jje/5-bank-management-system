package bank;

import account.Account;
import account.AccountList;
import customer.CustomerList;

public abstract class Bank {

    private final String name;
    private final AccountList accountList;
    private final CustomerList customerList;
    public Bank(String name) {
        this.name = name;
        this.accountList = new AccountList();
        this.customerList = new CustomerList();
    }

    public abstract void register();

    public abstract void updateAccount(Account account);

    public void deleteAccount(Account account) {
        this.accountList.remove(account);
    }

    public Account getAccount(String accountNumber) {
        return accountList.getAccount(accountNumber);
    }

    public Account getAccount(String id, String password) {
        return accountList.getAccount(id, password);
    }

    public void showAllAccounts() {

    }

    public String getName() {
        return name;
    }

    public AccountList getAccountList() {
        return accountList;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }
}