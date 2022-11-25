package account;

import customer.Customer;

public class HanaBankAccount extends Account {

    public HanaBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit(Customer customer) {

    }

    @Override
    public void withdrawal(Customer customer) {

    }
}
