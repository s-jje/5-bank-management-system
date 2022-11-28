package util;

import java.util.Scanner;

public class ScannerUtil {

    public static Scanner scanner;

    private void Scanner() {

    }

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
