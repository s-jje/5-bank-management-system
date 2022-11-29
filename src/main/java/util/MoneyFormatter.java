package util;

import java.text.DecimalFormat;

public class MoneyFormatter {

    public static String formatToWon(long balance) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        return "₩" + decimalFormat.format(balance);
    }
}
