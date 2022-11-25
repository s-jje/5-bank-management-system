package account;

public class WooriBankAccount extends Account {

    public WooriBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
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
    public void receive(String accountNumber, long amount) {

    }

    @Override
    public void showBalance() {

    }
}
