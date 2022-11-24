import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class BankManagementSystemApplication {

    public static void main(String[] args) {
        System.out.println("Welcome to Bank!");
        System.out.println("1. Transaction");
        System.out.println("2. Balance check");
        System.out.println("3. Create an account");
        System.out.println("4. Exit");
        System.out.println("Please enter a number 1 ~ 5.");

        Bank bank = new Bank();
        List<Customer> customerList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("1")) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("Please enter a number 1 ~ 2.");
            input = scanner.nextLine();

            if (input.equals("1")) {

            } else if (input.equals("2")) {

            } else {

            }
        } else if (input.equals("2")) {
            System.out.println("Enter your ID: ");
            String id = scanner.nextLine();
            System.out.println("Enter your PW: ");
            String pw = scanner.nextLine();
            bank.getAccount(id, pw).checkBalance(id, pw);
        } else if (input.equals("3")) {
            UUID uuid = UUID.randomUUID();
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            String accountNumber = "123-123456";

            Customer customer = new Customer(uuid, name, accountNumber);
            System.out.println("Your account has been created!");
            System.out.printf("name:%s account number: %s", name, accountNumber);
        } else if (input.equals("4")) {

        } else if (input.equals("manage")) {

        } else {

        }
    }
}
