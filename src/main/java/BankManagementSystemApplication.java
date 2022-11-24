import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankManagementSystemApplication {

    public static void main(String[] args) {
        Bank bank = new Bank("KB Bank");
        List<Customer> customerList = new ArrayList<>();

        System.out.println("Welcome to Bank!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Transaction");
            System.out.println("2. Balance check");
            System.out.println("3. Create an account");
            System.out.println("4. Exit");
            System.out.println("Please enter a number 1 ~ 5.");

            String input = scanner.nextLine();

            try {
                if (input.equals("1")) {
                    System.out.println("1. Deposit");
                    System.out.println("2. Withdrawal");
                    System.out.println("Please enter a number 1 ~ 2.");
                    input = scanner.nextLine();

                    if (input.equals("1")) {
                        System.out.println("Enter your ID: ");
                        String id = scanner.nextLine();

                        System.out.println("Enter your Password: ");
                        String pw = scanner.nextLine();

                        Account account = bank.getAccount(id, pw);

                        System.out.println("Please enter the amount you want to deposit: ");
                        long amount = Long.parseLong(scanner.nextLine());

                        if (amount > 0) {
                            long balance = account.deposit(amount);

                            System.out.println("Deposit success!");
                            System.out.printf("Your balance is ₩%s%n", getFormattedBalance(balance));
                        } else {
                            System.out.println("You can deposit more than ₩0.");
                        }
                    } else if (input.equals("2")) {
                        System.out.println("Enter your ID: ");
                        String id = scanner.nextLine();

                        System.out.println("Enter your Password: ");
                        String pw = scanner.nextLine();

                        Account account = bank.getAccount(id, pw);

                        System.out.println("Please enter the amount you want to withdrawal: ");
                        long amount = Long.parseLong(scanner.nextLine());

                        if (amount > 0) {
                            long balance = account.withdrawal(amount);

                            if (balance >= 0) {
                                System.out.println("Withdrawal success!");
                                System.out.printf("Your balance is ₩%s%n", getFormattedBalance(balance));
                                bank.getAccount(id, pw).withdrawal(amount);
                            } else {
                                System.out.printf("Withdrawal failed. You have ₩%s in your account.%n", balance);
                            }
                        } else {
                            System.out.println("You can deposit more than ₩0.");
                        }
                    } else {
                        throw new RuntimeException("Invalid number.");
                    }
                } else if (input.equals("2")) {
                    System.out.println("Enter your ID: ");
                    String id = scanner.nextLine();

                    System.out.println("Enter your Password: ");
                    String pw = scanner.nextLine();

                    long balance = bank.getAccount(id, pw).checkBalance(id, pw);
                    if (balance == -1) {

                    } else if (balance == -2) {

                    } else {
                        System.out.printf("Your balance is ₩%s%n", getFormattedBalance(balance));
                    }

                } else if (input.equals("3")) {
                    System.out.println("Enter your name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter the ID: ");
                    String id = scanner.nextLine();

                    System.out.println("Enter the Password: ");
                    String pw = scanner.nextLine();

                    String accountNumber = "123-123456";
                    customerList.add(new Customer(id, pw, name, accountNumber));
                    bank.register(new Account(id, pw, name, bank.getName(), accountNumber, 0));

                    System.out.println("Your account has been created!");
                    System.out.printf("ID: %s name: %s account number: %s%n", id, name, accountNumber);
                } else if (input.equals("4")) {
                    break;
                } else if (input.equals("manage")) {
                    System.out.println("1. Update account");
                    System.out.println("2. Delete account");
                    System.out.println("3. Show all accounts");

                    input = scanner.nextLine();

                    if (input.equals("1")) {
                        System.out.println("Enter the account number to update: ");
                        String accountNumber = scanner.nextLine();
                        bank.updateAccount(bank.getAccount(accountNumber));
                    } else if (input.equals("2")) {
                        System.out.println("Enter the account number to delete: ");
                        String accountNumber = scanner.nextLine();
                        bank.deleteAccount(bank.getAccount(accountNumber));
                    } else if (input.equals("3")) {
                        bank.showAllAccounts();
                    } else {
                        throw new RuntimeException("Invalid number.");
                    }
                } else {
                    throw new RuntimeException("Invalid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }

        scanner.close();
    }

    private static String getFormattedBalance(long balance) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(balance);
    }
}
