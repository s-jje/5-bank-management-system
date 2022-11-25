package bank;

import account.Account;

public class TossBank extends Bank {

    static TossBank instance;

    public TossBank() {
        super("Toss Bank");
    }

    public static TossBank getInstance() {
        if (instance == null) {
            return new TossBank();
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
