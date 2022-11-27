package bank;

import bankaccount.BankAccount;
import useraccount.UserAccount;
import util.AccountNumberFormatter;

import java.util.*;

public abstract class Bank {

    private final String name;
    private final List<UserAccount> userAccountList;
    private final List<BankAccount> bankAccountList;
    private final Map<String, List<BankAccount>> idAccountListMap;

    public Bank(String name) {
        this.name = name;
        this.bankAccountList = new ArrayList<>();
        this.userAccountList = new ArrayList<>();
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
        throw new NoSuchElementException("Account not found.");
    }

    public void showAllAccounts() {
        System.out.printf(" %s%n", name);
        System.out.println("===============================");
        System.out.println("   Name      Account Number    ");
        System.out.println("-------------------------------");
        for (BankAccount bankAccount : bankAccountList) {
            System.out.printf(" %10s %15s %n", bankAccount.getName(), AccountNumberFormatter.format(bankAccount.getAccountNumber()));
        }
        System.out.println("===============================");
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public List<UserAccount> getCustomerList() {
        return userAccountList;
    }

    public Map<String, List<BankAccount>> getIdAccountListMap() {
        return idAccountListMap;
    }

    public UserAccount getUserAccount(String id, String password) {
        for (UserAccount userAccount : userAccountList) {
            if (userAccount.getId().equals(id)) {
                if (userAccount.getPassword().equals(password)) {
                    return userAccount;
                }
                throw new NoSuchElementException("Incorrect password.");
            }
        }
        throw new NoSuchElementException("Account not found.");
    }
}