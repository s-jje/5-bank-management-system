package bankaccount;


public class ShinhanBankAccount extends BankAccount {

    public ShinhanBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance, 1.58549e-9); // 5.0%
    }

    @Override
    public void transfer() {

    }

    @Override
    protected String formatAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        accountNumber = accountNumber.replace("-", "");
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 6).append("-").append(accountNumber, 6, 12);
        return sb.toString();
    }

    @Override
    public void showBalance() {

    }
}