package bank;

import account.Account;

public class HanaBank extends Bank {

    static HanaBank instance;

    private HanaBank() {
        super("Hana Bank");
    }

    public static HanaBank getInstance() {
        if (instance == null) {
            instance = new HanaBank();
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
