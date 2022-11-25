package bank;

import account.Account;

public class HanaBank extends Bank {

    static HanaBank instance;

    public HanaBank() {
        super("Hana Bank");
    }

    public static HanaBank getInstance() {
        if (instance == null) {
            return new HanaBank();
        }
        return instance;
    }

    @Override
    public void register() {

    }

    @Override
    public void updateAccount(Account account) {

    }
}
