package bankaccount;

import bank.Bank;
import util.BankingSystem;
import util.TimeFormatter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static util.Time.getCurrentDateTime;

public class TossBankAccount extends BankAccount {

    public TossBankAccount(String name, String id, String password, String bankName, String accountNumber, long balance) {
        super(name, id, password, bankName, accountNumber, balance, 3.1709792e-9); // 10.0%
    }

    protected String formatAccountNumber(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        accountNumber = accountNumber.replace("-", "");
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 8).append("-").append(accountNumber, 8, 11);
        return sb.toString();
    }
}
