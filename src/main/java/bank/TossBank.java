package bank;

import account.BankAccount;
import account.TossBankAccount;
import customer.Customer;
import util.MoneyFormatter;
import util.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TossBank extends Bank {

    private static TossBank instance;

    private final int NUM_OF_MAX_ACCOUNT = 3;

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
                getCustomerList().add(new Customer(name, id, pw));
                System.out.printf("%nAccount registration successful! Your account number is %s. Now you have 1 bank account.%n", newAccountNumber);
                break;
            }
        }
    }

    @Override
    public void update() {
        String[] idAndPassword = inputIdAndPassword();

        if (isExistAccount(idAndPassword[0], idAndPassword[1])) {
            System.out.printf("%n==========================%n");
            System.out.println("|         Update          |");
            System.out.println("---------------------------");
            System.out.println("| 1. Rename               |");
            System.out.println("| 2. Change your password |");
            System.out.println("===========================");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            List<BankAccount> bankAccountList = getIdAccountListMap().get(idAndPassword[0]);
            Customer customer = getCustomer(idAndPassword[0], idAndPassword[1]);

            if (input.equals("1")) {
                System.out.printf("%nPlease enter your new name: ");
                input = scanner.nextLine();
                for (BankAccount bankAccount : bankAccountList) {
                    bankAccount.setName(input);
                }
                customer.setName(input);
                System.out.printf("%nRename successful!%n");
            } else if (input.equals("2")) {
                System.out.printf("%nPlease enter your new password: ");
                input = scanner.nextLine();
                for (BankAccount bankAccount : bankAccountList) {
                    bankAccount.setPassword(input);
                }
                customer.setPassword(input);
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
                System.out.printf("Here are your accounts.%n");
                for (BankAccount bankAccount : bankAccounts) {
                    System.out.printf("%s ₩%s%n", bankAccount.getAccountNumber(), MoneyFormatter.formatToWon(bankAccount.getBalance()));
                }
                System.out.println("Please enter the account number you want delete: ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();

                for (BankAccount bankAccount : bankAccounts) {
                    if (input.equals(bankAccount.getAccountNumber())) {
                        if (bankAccount.getBalance() == 0) {
                            bankAccounts.remove(bankAccount);
                            System.out.printf("%nDeletion successful!%n");
                        } else {
                            System.out.printf("%nOnly accounts with zero balance can be deleted.%n");
                        }
                        return;
                    }
                }
                System.out.printf("%nAccount not found.%n");
            } else {
                System.out.printf("%nYou have only one account.%n");
            }
        }
    }

    @Override
    public void withdraw() {
        String[] idAndPassword = inputIdAndPassword();

        if (isExistAccount(idAndPassword[0], idAndPassword[1])) {
            System.out.print("Do you really want to withdraw from your Toss Bank Account? [yes/no]%n");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                getIdAccountListMap().remove(idAndPassword[0]);
                getCustomerList().remove(getCustomer(idAndPassword[0], idAndPassword[1]));
                System.out.printf("%nWithdraw successful.%n");
            } else if (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("no")) {
                throw new RuntimeException("Invalid input.");
            }
        }
    }

    private String generateAccountNumber() {
        String first = RandomNumberGenerator.generateGivenLengthNumber(2);
        String second = RandomNumberGenerator.generateGivenLengthNumber(3);
        String third = RandomNumberGenerator.generateGivenLengthNumber(2);

        StringBuilder sb = new StringBuilder();
        sb.append(first).append("-").append(second).append("-").append(third);
        return sb.toString();
    }
}
