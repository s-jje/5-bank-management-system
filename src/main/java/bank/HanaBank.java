package bank;

import account.Account;

public class HanaBank extends Bank {

    static HanaBank instance = new HanaBank();

    private HanaBank() {
        super("Hana Bank");
    }

    public static HanaBank getInstance() {
        return instance;
    }

    @Override
    public void register() {

    }

    @Override
    public void updateAccount(Account account) {

    }
}
