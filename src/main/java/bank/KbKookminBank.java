package bank;

import account.Account;

public class KbKookminBank extends Bank {

    static KbKookminBank instance;

    private KbKookminBank() {
        super("KB Kookmin Bank");
    }

    public static KbKookminBank getInstance() {
        if (instance == null) {
            instance = new KbKookminBank();
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
