package bank;

import bankaccount.BankAccount;
import bankaccount.TossBankAccount;
import useraccount.UserAccount;
import util.MoneyFormatter;
import util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TossBank extends Bank {

    private final int NUM_OF_MAX_ACCOUNT = 3;

    private static TossBank instance;

    private TossBank() {
        super("Toss Bank");
    }

    public static TossBank getInstance() {
        if (instance == null) {
            instance = new TossBank();
        }
        return instance;
    }

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<BankAccount>> idAccountListMap = getIdAccountListMap();

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
                            bankAccounts.add(new TossBankAccount(name, id, pw, getName(), newAccountNumber, 0L));
                            System.out.printf("%nBank account registration successful! Your account number is %s. Now you have %d bank accounts.%n", newAccountNumber, bankAccounts.size());
                        } else {
                            System.out.printf("%nBank registration failed.%nYou already have 3 bank accounts.%n");
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

                list.add(new TossBankAccount(name, id, pw, getName(), newAccountNumber, 0L));
                idAccountListMap.put(id, list);
                getUserAccountList().add(new UserAccount(name, id, pw));
                System.out.printf("%nAccount registration successful! Your account number is %s. Now you have 1 bank account.%n", newAccountNumber);
                break;
            }
        }
    }

    @Override
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

            List<BankAccount> bankAccountList = getIdAccountListMap().get(idAndPassword[0]);
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

    @Override
    public void deleteAccount() {
        String[] idAndPassword = inputIdAndPassword();

        if (isExistAccount(idAndPassword[0], idAndPassword[1])) {
            List<BankAccount> bankAccounts = getIdAccountListMap().get(idAndPassword[0]);

            if (bankAccounts.size() > 1) {
                int size = bankAccounts.size();
                String[] accountNumbers = new String[size];

                System.out.printf("%n=============================================%n");
                System.out.println("|               Accounts list               |");
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

    @Override
    public void withdraw() {
        String[] idAndPassword = inputIdAndPassword();

        if (isExistAccount(idAndPassword[0], idAndPassword[1])) {
            System.out.print("Do you really want to withdraw from your Toss Bank Account? [yes/no]: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                getIdAccountListMap().remove(idAndPassword[0]);
                getUserAccountList().remove(getUserAccount(idAndPassword[0], idAndPassword[1]));
                System.out.printf("%nWithdraw successful.%n");
            } else if (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
                throw new RuntimeException("Invalid input.");
            }
        }
    }

    private String generateAccountNumber() {
        String first = RandomNumberGenerator.generateGivenLengthNumber(3);
        String second = RandomNumberGenerator.generateGivenLengthNumber(5);
        String third = RandomNumberGenerator.generateGivenLengthNumber(3);

        StringBuilder sb = new StringBuilder();
        sb.append(first).append("-").append(second).append("-").append(third);
        return sb.toString();
    }
}
