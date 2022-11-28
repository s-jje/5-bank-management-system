package bankaccount;

public class TransactionData {

    private final String date;
    private final String accountNumber;
    private final boolean isDeposit; // 입 / 출금
    private final long amount;
    private final long balance;
    private final String description;

    public TransactionData(String date, String accountNumber, boolean isDeposit, long amount, long balance, String description) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.isDeposit = isDeposit;
        this.amount = amount;
        this.balance = balance;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
