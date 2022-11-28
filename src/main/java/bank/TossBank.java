package bank;

import bankaccount.BankAccount;
import bankaccount.TossBankAccount;
import util.RandomNumberGenerator;

public class TossBank extends Bank {

    private static TossBank instance;

    private TossBank() {
        super("Toss Bank", 3);
    }

    public static TossBank getInstance() {
        if (instance == null) {
            instance = new TossBank();
        }
        return instance;
    }

    @Override
    public String[] getAccountNumberRegex() {
        return new String[]{"\\d{3}\\d{5}\\d{3}", "\\d{3}-\\d{5}-\\d{3}"};
    }

    @Override
    protected BankAccount createBankAccount(String name, String id, String password, String newAccountNumber) {
        return new TossBankAccount(name, id, password, getName(), newAccountNumber, 0L);
    }

    @Override
    protected String generateAccountNumber() {
        String first = RandomNumberGenerator.generateGivenLengthNumber(3);
        String second = RandomNumberGenerator.generateGivenLengthNumber(5);
        String third = RandomNumberGenerator.generateGivenLengthNumber(3);

        StringBuilder sb = new StringBuilder();
        sb.append(first).append("-").append(second).append("-").append(third);
        return sb.toString();
    }
}
