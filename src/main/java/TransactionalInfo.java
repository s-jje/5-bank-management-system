package main.java;

public class TransactionalInfo {
    private final String date;
    private final String accountNumber;
    private final boolean isDeposit;
    private final long amount;
    private final String bank;

    public TransactionalInfo(String date, String accountNumber, boolean isDeposit, long amount, String bank) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.isDeposit = isDeposit;
        this.amount = amount;
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "TransactionalInfo{" +
                "date='" + date + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", bank='" + bank + '\'' +
                '}';
    }
}
