package account;

public class HanaBankAccount extends BankAccount {

    public HanaBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit() {

    }

    @Override
    public void withdrawal() {

    }

    @Override
    public void transfer() {

    }

    @Override
    public void receive(BankAccount srcBankAccount, BankAccount dstBankAccount, long amount) {

    }

    @Override
    public void showBalance() {

    }
}
