package bank;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class TossBankTest {

    private static TossBank tossBank;
    private final String msg = "Please enter your name: Please enter the ID: Please enter the password: \nAccount registration successful! Your account number is . Now you have 1 bank account.\n";

    @BeforeAll
    static void initAll() {
        tossBank = TossBank.getInstance();
    }

    @Test
    void register() {
        TossBank tossBank = TossBank.getInstance();

        String name = "Seongje Cho\n";
        String id = "sj\n";
        String password = "123123\n";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(id).append(password).append(name);

        InputStream stdin = System.in;
        try {
            OutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));

            InputStream in = new ByteArrayInputStream(stringBuilder.toString().getBytes());
            System.setIn(in);
            tossBank.register();
            assertThat(msg).isEqualTo(out.toString().replaceAll("\\d{3,5}-?\\d{2,5}-?\\d{3,6}", ""));
        } finally {
            System.setIn(stdin);
        }
    }

    @Test
    void update() {
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void withdraw() {
    }
}