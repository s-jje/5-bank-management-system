package bank;

public class KbKookminBank extends Bank {

    static KbKookminBank instance;

    private KbKookminBank() {
        super("KB Kookmin Bank", 3);
    }

    public static KbKookminBank getInstance() {
        if (instance == null) {
            instance = new KbKookminBank();
        }
        return instance;
    }

    @Override
    protected String generateAccountNumber() {
        String firstNumber = String.valueOf((int) (Math.random() * 1000));
        String middleNumber = String.valueOf((int) (Math.random() * 100));
        String lastNumber = String.valueOf((int) (Math.random() * 1000000));

        return firstNumber + "-" + middleNumber + "-" + lastNumber;
    }
}
