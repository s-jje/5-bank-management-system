package bank;

import bankaccount.BankAccount;
import bankaccount.WooriBankAccount;
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
    public String[] getAccountNumberRegex() {
        return new String[]{"\\d{3}\\d{6}\\d{2}", "\\d{3}-\\d{6}-\\d{2}"};
    }

    @Override
    protected BankAccount createBankAccount(String name, String id, String password, String newAccountNumber) {
        return new WooriBankAccount(name, id, password, getName(), newAccountNumber, 0L);
    }

    @Override
    protected String generateAccountNumber() {
        String first = RandomNumberGenerator.generateGivenLengthNumber(3);
        String second = RandomNumberGenerator.generateGivenLengthNumber(6);
        String third = RandomNumberGenerator.generateGivenLengthNumber(2);

        StringBuilder sb = new StringBuilder();
        sb.append(first).append("-").append(second).append("-").append(third);
        return sb.toString();
    }
}