package util;

import java.text.DecimalFormat;

public class Formatter {

    public static String getFormattedBalance(long balance) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(balance);
    }
}
