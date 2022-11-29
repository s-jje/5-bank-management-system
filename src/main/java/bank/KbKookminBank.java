package bank;

import bankaccount.BankAccount;
import bankaccount.KbKookminBankAccount;
import util.RandomNumberGenerator;

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
    public String[] getAccountNumberRegex() {
        return new String[]{"\\d{3}\\d{2}\\d{6}", "\\d{3}-\\d{2}-\\d{6}"};
    }

    @Override
    protected BankAccount createBankAccount(String name, String id, String password, String newAccountNumber) {
        return new KbKookminBankAccount(name, id, password, getName(), newAccountNumber, 0L);
    }


    @Override
    protected String generateAccountNumber() {
        String first = RandomNumberGenerator.generateGivenLengthNumber(3);
        String second = RandomNumberGenerator.generateGivenLengthNumber(2);
        String third = RandomNumberGenerator.generateGivenLengthNumber(6);

        StringBuilder sb = new StringBuilder();
        sb.append(first).append("-").append(second).append("-").append(third);
        return sb.toString();
    }

    @Override
    public String formatAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        accountNumber = accountNumber.replace("-", "");
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 5).append("-").append(accountNumber, 5, 11);
        return sb.toString();
    }
}
