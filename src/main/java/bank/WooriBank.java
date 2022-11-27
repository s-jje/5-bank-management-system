package bank;

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
    public void update() {

    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void withdraw() {

    }
}