package account;

public class TransactionData {

    private final String date;
    private final String accountNumber;
    private final boolean isDeposit;
    private final long amount;
    private final String bank;

    public TransactionData(String date, String accountNumber, boolean isDeposit, long amount, String bank) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.isDeposit = isDeposit;
        this.amount = amount;
        this.bank = bank;
    }
}