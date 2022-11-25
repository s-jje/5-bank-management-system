package bank;

import account.Account;

public class WooriBank extends Bank {

    static WooriBank instance;

    private WooriBank() {
        super("Woori Bank");
    }

    public static WooriBank getInstance() {
        if (instance == null) {
            instance = new WooriBank();
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
