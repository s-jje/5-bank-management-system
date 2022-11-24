package util;

import java.text.DecimalFormat;

public class Formatter {

    public static String formatToWon(long balance) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return decimalFormat.format(balance);
    }
}
