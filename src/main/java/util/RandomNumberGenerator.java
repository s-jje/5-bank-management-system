package util;

import java.util.Random;

public class RandomNumberGenerator {

    public static String generateGivenLengthNumber(int length) {
        int max = (int) Math.pow(10, length);
        int min = (int) Math.pow(10, length - 1);
        Random random = new Random();
        return Integer.toString(random.nextInt(max - min) + min);
    }
}
