package bank;

import bankaccount.BankAccount;
import bankaccount.ShinhanBankAccount;
import util.RandomNumberGenerator;

public class ShinhanBank extends Bank {

    static ShinhanBank instance;

    private ShinhanBank() {
        super("Shinhan Bank", 4);
    }

    public static ShinhanBank getInstance() {
        if (instance == null) {
            instance = new ShinhanBank();
        }
        return instance;
    }

    @Override
    public String[] getAccountNumberRegex() {
        return new String[]{"\\d{3}\\d{3}\\d{6}", "\\d{3}-\\d{3}-\\d{6}"};
    }

    @Override
    protected BankAccount createBankAccount(String name, String id, String password, String newAccountNumber) {
        return new ShinhanBankAccount(name, id, password, getName(), newAccountNumber, 0L);
    }
    @Override
    protected String generateAccountNumber() {
        String first = "110";
        String second = RandomNumberGenerator.generateGivenLengthNumber(3);
        String third = RandomNumberGenerator.generateGivenLengthNumber(6);

        StringBuilder sb = new StringBuilder();
        sb.append(first).append("-").append(second).append("-").append(third);
        return sb.toString();
    }
}
