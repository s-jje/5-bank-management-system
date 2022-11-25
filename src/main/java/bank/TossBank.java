package bank;

import account.Account;
import account.TossBankAccount;
import customer.Customer;

import java.util.*;

public class TossBank extends Bank {

    private final int MAX_ACCOUNT_NUM = 3;
    private final Map<Customer, List<Account>> customerAccountListMap;
    static TossBank instance;

    private TossBank() {
        super("Toss Bank");
        this.customerAccountListMap = new HashMap<>();
    }

    public static TossBank getInstance() {
        if (instance == null) {
            return new TossBank();
        }
        return instance;
    }

    @Override
    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Please enter the ID: ");
        String id = scanner.nextLine();

        System.out.print("Please enter the Password: ");
        String pw = scanner.nextLine();

        System.out.println();
        String accountNumber = "123-123";

        Customer customer = new Customer(name, id, pw, accountNumber);
        Account account = new TossBankAccount(name, id, pw, getName(), accountNumber, 0L);

        if (customerAccountListMap.containsKey(customer)) {
            if (customerAccountListMap.get(customer).size() < 3) {
                addAccount(account);
                customerAccountListMap.get(customer).add(account);
            } else {
                System.out.printf("You already have 3 accounts.%n%n");
                return;
            }
        } else {
            addAccount(account);
            addCustomer(customer);
            List<Account> list = new ArrayList<>(MAX_ACCOUNT_NUM);
            list.add(account);
            customerAccountListMap.put(customer, list);
        }

        System.out.printf("떳냐: %s%n", getAccount(accountNumber).getAccountNumber());

        System.out.printf("Account registration successful!%n%n");
    }

    @Override
    public void updateAccount(Account account) {

    }
}
