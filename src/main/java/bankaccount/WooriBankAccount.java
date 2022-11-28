package bankaccount;

public class WooriBankAccount extends BankAccount {

    public WooriBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance, 9.51294e-10); // 3.0%
    }

    @Override
    public void transfer() {

    }

    @Override
    protected String formatAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        accountNumber = accountNumber.replace("-", "");
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 9).append("-").append(accountNumber, 9, 11);
        return sb.toString();
    }
}
