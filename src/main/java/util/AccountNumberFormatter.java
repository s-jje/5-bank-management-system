package util;

public class AccountNumberFormatter {

    public static String format(String accountNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append(accountNumber, 0, 3).append("-").append(accountNumber, 3, 8).append("-").append(accountNumber, 8, 11);
        return sb.toString();
    }
}
