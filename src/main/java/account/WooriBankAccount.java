package account;

import customer.Customer;

public class WooriBankAccount extends Account {

    public WooriBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance);
    }

    @Override
    public void deposit(Customer customer) {

    }

    @Override
    public void withdrawal(Customer customer) {

    }
}
