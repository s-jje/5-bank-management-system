package bank;

import util.RandomNumberGenerator;

public class WooriBank extends Bank {

    static WooriBank instance;

    private WooriBank() {
        super("Woori Bank", 2);
    }

    public static WooriBank getInstance() {
        if (instance == null) {
            instance = new WooriBank();
        }
        return instance;
    }

    @Override
    protected String generateAccountNumber() {
        String first = RandomNumberGenerator.generateGivenLengthNumber(3);
        String second = RandomNumberGenerator.generateGivenLengthNumber(5);
        String third = RandomNumberGenerator.generateGivenLengthNumber(2);

        StringBuilder sb = new StringBuilder();
        sb.append(first).append("-").append(second).append("-").append(third);
        return sb.toString();
    }
}