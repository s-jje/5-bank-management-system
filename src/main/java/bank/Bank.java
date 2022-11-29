package bank;

import bankaccount.BankAccount;
import useraccount.UserAccount;
import util.MoneyFormatter;

import java.util.*;
import java.util.stream.Collectors;

import static util.ScannerUtil.getScanner;

public abstract class Bank {

    private final String name;
    private final List<UserAccount> userAccountList;
    private final Map<String, List<BankAccount>> idBankAccountListMap;

    private final int NUM_OF_MAX_ACCOUNT;

    Scanner scanner = getScanner();

    public Bank(String name, int maxAccount) {
        this.name = name;
        this.userAccountList = new ArrayList<>();
        this.idBankAccountListMap = new HashMap<>();
        this.NUM_OF_MAX_ACCOUNT = maxAccount;
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<BankAccount>> idAccountListMap = getIdBankAccountListMap();

        while (true) {
            System.out.print("Please enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Please enter the ID: ");
            String id = scanner.nextLine();

            if (isExistId(id)) {
                System.out.printf("%nAlready used ID: %s%n", id);
                System.out.print("Would you like to create an additional account? [yes/no]: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                    List<BankAccount> bankAccounts = idAccountListMap.get(id);
                    System.out.print("Please enter the password: ");
                    String pw = scanner.nextLine();

                    if (pw.equals(bankAccounts.get(0).getPassword())) {
                        if (bankAccounts.size() < NUM_OF_MAX_ACCOUNT) {
                            String newAccountNumber = generateAccountNumber();
                            bankAccounts.add(createBankAccount(name, id, pw, newAccountNumber));
                            System.out.printf("%nBank account registration successful! Your account number is %s. Now you have %d bank accounts.%n", newAccountNumber, bankAccounts.size());
                        } else {
                            System.out.printf("%nBank registration failed.%nYou already have %d bank accounts.%n", NUM_OF_MAX_ACCOUNT);
                        }
                        break;
                    } else {
                        System.out.printf("%nIncorrect password.%n");
                    }
                } else if (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
                    throw new RuntimeException("Invalid input.");
                }
            } else {
                List<BankAccount> list = new ArrayList<>(NUM_OF_MAX_ACCOUNT);
                System.out.print("Please enter the password: ");
                String pw = scanner.nextLine();
                String newAccountNumber = generateAccountNumber();

                list.add(createBankAccount(name, id, pw, newAccountNumber));
                idAccountListMap.put(id, list);
                getUserAccountList().add(new UserAccount(name, id, pw));
                System.out.printf("%nAccount registration successful! Your account number is %s. Now you have 1 bank account.%n", newAccountNumber);
                break;
            }
        }
    }

    public void update() {
        String[] idAndPassword = inputIdAndPassword();

        if (isExistAccount(idAndPassword[0], idAndPassword[1])) {
            System.out.printf("%n======================%n");
            System.out.println("|      Update        |");
            System.out.println("----------------------");
            System.out.println("| 1. Change name     |");
            System.out.println("| 2. Change password |");
            System.out.println("======================");
            System.out.print("Please select a option [1 ~ 2]: ");
            String input = scanner.nextLine();

            List<BankAccount> bankAccountList = getIdBankAccountListMap().get(idAndPassword[0]);
            UserAccount userAccount = getUserAccount(idAndPassword[0], idAndPassword[1]);

            if (input.equals("1")) {
                System.out.printf("%nPlease enter your new name: ");
                input = scanner.nextLine();
                for (BankAccount bankAccount : bankAccountList) {
                    bankAccount.setName(input);
                }
                userAccount.setName(input);
                System.out.printf("%nRename successful!%n");
            } else if (input.equals("2")) {
                System.out.printf("%nPlease enter your new password: ");
                input = scanner.nextLine();
                for (BankAccount bankAccount : bankAccountList) {
                    bankAccount.setPassword(input);
                }
                userAccount.setPassword(input);
                System.out.printf("%nPassword change successful!%n");
            } else {
                throw new RuntimeException("Invalid input.");
            }
        }
    }

    public void deleteBankAccount() {
        String[] idAndPassword = inputIdAndPassword();

        if (isExistAccount(idAndPassword[0], idAndPassword[1])) {
            List<BankAccount> bankAccounts = getIdBankAccountListMap().get(idAndPassword[0]);

            if (bankAccounts.size() > 1) {
                int size = bankAccounts.size();
                String[] accountNumbers = new String[size];

                System.out.printf("%n=============================================%n");
                System.out.println("|                Accounts                   |");
                System.out.println("---------------------------------------------");
                for (int i = 0; i < size; i++) {
                    BankAccount bankAccount = bankAccounts.get(i);
                    accountNumbers[i] = bankAccount.getAccountNumber();
                    System.out.printf("| %d. %-16s %20s |%n", i + 1, accountNumbers[i], MoneyFormatter.formatToWon(bankAccount.getBalance()));
                }
                System.out.println("=============================================");
                System.out.print("Please select the account you want to delete: ");

                int input = Integer.parseInt(scanner.nextLine());

                if (1 <= input && input <= size) {
                    for (BankAccount bankAccount : bankAccounts) {
                        if (accountNumbers[input - 1].equals(bankAccount.getAccountNumber())) {
                            if (bankAccount.getBalance() == 0) {
                                bankAccounts.remove(bankAccount);
                                System.out.printf("%nDeletion successful!%n");
                            } else {
                                System.out.printf("%nOnly accounts with zero balance can be deleted.%n");
                            }
                            return;
                        }
                    }
                } else {
                    throw new RuntimeException("Invalid input.");
                }
            } else {
                System.out.printf("You have only one account.%n");
            }
        }
    }

    public void withdraw() {
        String[] idAndPassword = inputIdAndPassword();

        if (isExistAccount(idAndPassword[0], idAndPassword[1])) {
            System.out.print("Do you really want to withdraw from your Toss Bank Account? [yes/no]: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                getIdBankAccountListMap().remove(idAndPassword[0]);
                getUserAccountList().remove(getUserAccount(idAndPassword[0], idAndPassword[1]));
                System.out.printf("%nWithdraw successful.%n");
            } else if (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
                throw new RuntimeException("Invalid input.");
            }
        }
    }

    public void showAllUserAccounts() {
        System.out.printf("%n%s%n", name);
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
        System.out.printf("%n%s%n", name);
        System.out.println("=====================================");
        System.out.println("      Name         Account Number    ");
        System.out.println("-------------------------------------");
        List<BankAccount> bankAccountList = getBankAccountList();
        for (BankAccount bankAccount : bankAccountList) {
            System.out.printf(" %-15s %-20s %n", bankAccount.getName(), bankAccount.getAccountNumber());
        }
        System.out.println("=====================================");
    }

    public abstract String[] getAccountNumberRegex();

    protected abstract BankAccount createBankAccount(String name, String id, String password, String newAccountNumber);

    protected abstract String generateAccountNumber();

    public abstract String formatAccountNumber(String accountNumber);

    protected String[] inputIdAndPassword() {
        System.out.print("Please enter the ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter the Password: ");
        String password = scanner.nextLine();
        System.out.println();
        return new String[]{id, password};
    }

    protected boolean isExistId(String id) {
        return idBankAccountListMap.containsKey(id);
    }

    protected boolean isExistAccount(String id, String password) {
        if (idBankAccountListMap.containsKey(id)) {
            List<BankAccount> bankAccounts = idBankAccountListMap.get(id);
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

    public BankAccount getBankAccount(String accountNumber) {
        for (BankAccount bankAccount : getBankAccountList()) {
            if (accountNumber.equals(bankAccount.getAccountNumber())) {
                return bankAccount;
            }
        }
        throw new NoSuchElementException("Account not found.");
    }

    public String getName() {
        return name;
    }

    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public List<BankAccount> getBankAccountList() {
        return idBankAccountListMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    public Map<String, List<BankAccount>> getIdBankAccountListMap() {
        return idBankAccountListMap;
    }
}