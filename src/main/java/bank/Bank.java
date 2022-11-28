package bank;

import bankaccount.BankAccount;
import useraccount.UserAccount;

import java.util.*;
import java.util.stream.Collectors;

import static util.ScannerUtil.getScanner;

public abstract class Bank {

    private final String name;
    private final List<UserAccount> userAccountList;
    private final Map<String, List<BankAccount>> idAccountListMap;

    Scanner scanner = getScanner();

    public Bank(String name) {
        this.name = name;
        this.userAccountList = new ArrayList<>();
        this.idAccountListMap = new HashMap<>();
    }

    public abstract void register();

    public abstract void update();

    public abstract void deleteAccount();

    public abstract void withdraw();

    public String[] inputIdAndPassword() {
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
        for (BankAccount bankAccount : getBankAccountList()) {
            if (accountNumber.equals(bankAccount.getAccountNumber())) {
                return bankAccount;
            }
        }
        throw new NoSuchElementException("Account not found.");
    }

    public void showAllUserAccounts() {
        System.out.printf("%s%n", name);
        System.out.println("=====================================");
        System.out.println("       ID              Name          ");
        System.out.println("-------------------------------------");
        List<UserAccount> userAccountList = getUserAccountList();
        for (UserAccount userAccount : userAccountList) {
            System.out.printf(" %-15s %-20s %n", userAccount.getId(), userAccount.getName());
        }
        System.out.println("=====================================");
    }

    public void showAllBankAccounts() {
        System.out.printf("%s%n", name);
        System.out.println("=====================================");
        System.out.println("      Name         Account Number    ");
        System.out.println("-------------------------------------");
        List<BankAccount> bankAccountList = getBankAccountList();
        for (BankAccount bankAccount : bankAccountList) {
            System.out.printf(" %-15s %-20s %n", bankAccount.getName(), bankAccount.getAccountNumber());
        }
        System.out.println("=====================================");
    }

    public String getName() {
        return name;
    }

    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public List<BankAccount> getBankAccountList() {
        return idAccountListMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
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