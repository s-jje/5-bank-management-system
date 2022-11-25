package account;

import util.MoneyFormatter;

import java.util.Scanner;

public class ShinhanBankAccount extends Account {

    public ShinhanBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit() {
        System.out.print("Please enter the amount you want to deposit: ");
        Scanner scanner = new Scanner(System.in);
        long amount = Long.parseLong(scanner.nextLine());

        if (amount > 0) {
            long balance = getBalance() + amount;
            setBalance(balance);
            addTransactionData(new TransactionData(getAccountNumber(), true, amount, balance, "Shinhan Bank"));
            System.out.println("Deposit successful!");
            System.out.printf("Your balance is ₩%s%n", MoneyFormatter.formatToWon(balance));
        } else {
            System.out.println("You can deposit more than ₩0.");
        }
    }

    @Override
    public void withdrawal() {
        System.out.print("Please enter the amount you want to withdrawal: ");
        Scanner scanner = new Scanner(System.in);
        long amount = Long.parseLong(scanner.nextLine());

    }

    @Override
    public void transfer() {

    }

    @Override
    public void receive(String srcBank, String srcAccountNumber, String dstAccountNumber, long amount) {

    }

    @Override
    public void showBalance() {
    }
}
