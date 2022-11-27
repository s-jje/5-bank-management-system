package bank;

import account.BankAccount;
import customer.Customer;

import java.util.*;

public abstract class Bank {

    private final String name;
    private final List<Customer> customerList;
    private final List<BankAccount> bankAccountList;
    private final Map<String, List<BankAccount>> idAccountListMap;

    public Bank(String name) {
        this.name = name;
        this.bankAccountList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.idAccountListMap = new HashMap<>();
    }

    public abstract void register();

    public abstract void update();

    public abstract void deleteAccount();

    public abstract void withdraw();

    public String[] inputIdAndPassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter the Password: ");
        String password = scanner.nextLine();
        System.out.println();
        return new String[]{id, password};
    }

    public boolean isExistId(String id) {
        return idAccountListMap.containsKey(id);
    }

    public boolean isExistAccount(String id, String password) {
        if (idAccountListMap.containsKey(id)) {
            List<BankAccount> bankAccounts = idAccountListMap.get(id);
            for (BankAccount bankAccount : bankAccounts) {
                if (bankAccount.getId().equals(id)) {
                    if (bankAccount.getPassword().equals(password)) {
                        return true;
                    }
                    System.out.printf("%nIncorrect password.%n%n");
                }
            }
            System.out.printf("%nID not found.%n%n");
        }
        return false;
    }

    public BankAccount getBankAccount(String accountNumber) {
        for (BankAccount bankAccount : bankAccountList) {
            if (accountNumber.equals(bankAccount.getAccountNumber())) {
                return bankAccount;
            }
        }
        throw new NoSuchElementException("Account not found.");
    }

    public BankAccount getBankAccount(String id, String password) {
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getId().equals(id)) {
                if (bankAccount.getPassword().equals(password)) {
                    return bankAccount;
                }
                throw new NoSuchElementException("Incorrect password.");
            }
        }
        throw new NoSuchElementException("ID not found.");
    }

    public BankAccount getBankAccount(Customer customer) {
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.getId().equals(customer.getId())) {
                if (bankAccount.getPassword().equals(customer.getPassword())) {
                    return bankAccount;
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
        for (BankAccount bankAccount : bankAccountList) {
            System.out.printf(" %10s %15s %n", bankAccount.getName(), bankAccount.getAccountNumber());
        }
        System.out.println("===============================");
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public Map<String, List<BankAccount>> getIdAccountListMap() {
        return idAccountListMap;
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