package bank;

import account.Account;
import customer.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Bank {

    private final String name;
    private final List<Account> accountList;
    private final List<Customer> customerList;
    public Bank(String name) {
        this.name = name;
        this.accountList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public abstract void register();

    public abstract void updateAccount(Account account);

    public void deleteAccount(Account account) {
        this.accountList.remove(account);
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accountList) {
            if (accountNumber.equals(account.getAccountNumber())) {
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

    public void showAllAccounts() {
        System.out.printf(" %s%n", name);
        System.out.println("===============================");
        System.out.println("   Name      Account Number    ");
        System.out.println("-------------------------------");
        for (Account account : accountList) {
            System.out.printf(" %10s %15s %n", account.getName(), account.getAccountNumber());
        }
        System.out.println("===============================");
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public Customer getCustomer(String id, String password) {
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                if (customer.getPassword().equals(password)) {
                    return customer;
                }
                throw new NoSuchElementException("Incorrect password.");
            }
        }
        throw new NoSuchElementException("Customer not found.");
    }
}