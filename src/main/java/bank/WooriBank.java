package bank;

import account.Account;

public class WooriBank extends Bank {

    static WooriBank instance = new WooriBank();

    private WooriBank() {
        super("Woori Bank");
    }

    public static WooriBank getInstance() {
        return instance;
    }
    @Override
    public void register() {

    }

    @Override
    public void updateAccount(Account account) {

    }
}
