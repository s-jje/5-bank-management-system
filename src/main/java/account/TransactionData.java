package account;

import util.TimeFormatter;

public class TransactionData {

    private final String date;
    private final String accountNumber;
    private final boolean isDeposit; // 입 / 출금
    private final long amount;
    private final long balance;
    private final String destination;

    public TransactionData(String accountNumber, boolean isDeposit, long amount, long balance, String destination) {
        this.date = TimeFormatter.getCurrentTime();
        this.accountNumber = accountNumber;
        this.isDeposit = isDeposit;
        this.amount = amount;
        this.balance = balance;
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public long getAmount() {
        return amount;
    }

    public long getBalance() {
        return balance;
    }

    public String getDestination() {
        return destination;
    }
}
