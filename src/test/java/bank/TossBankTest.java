package bank;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TossBankTest {

    private static TossBank tossBank;

    @BeforeAll
    static void initAll() {
        tossBank = TossBank.getInstance();
    }

    @Order(1)
    @Test
    void firstRegister() {
        TossBank tossBank = TossBank.getInstance();

        String msg = "Please enter your name: Please enter the ID: Please enter the password: \nAccount registration successful! Your account number is . Now you have 1 bank account.\n";

        String name = "Seongje Cho\n";
        String id = "sj\n";
        String password = "123123\n";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(id).append(password);

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

    @Order(2)
    @Test
    void secondRegisterWithIncorrectPassword() {
        TossBank tossBank = TossBank.getInstance();

        String msg = "Please enter your name: Please enter the ID: \nAlready used ID: sj\nWould you like to create an additional account? [yes/no]: Please enter your name: Please enter the ID: Please enter the password: \nAccount registration successful! Your account number is . Now you have 1 bank account.\n";

        String name = "Seongje Cho\n";
        String id = "sj\n";

        String newName = "Kim\n";
        String newId = "kim\n";
        String newPassword = "12345\n";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(id).append("n\n").append(newName).append(newId).append(newPassword);

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

    @Order(3)
    @Test
    void secondRegister() {
        TossBank tossBank = TossBank.getInstance();

        String msg = "Please enter your name: Please enter the ID: \nAlready used ID: sj\nWould you like to create an additional account? [yes/no]: Please enter the password: \nBank account registration successful! Your account number is . Now you have 2 bank accounts.\n";

        String name = "Seongje Cho\n";
        String id = "sj\n";
        String password = "123123\n";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(id).append("y\n").append(password);

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

    @Order(4)
    @Test
    void thirdRegister() {
        TossBank tossBank = TossBank.getInstance();

        String msg = "Please enter your name: Please enter the ID: \nAlready used ID: sj\nWould you like to create an additional account? [yes/no]: Please enter the password: \nBank account registration successful! Your account number is . Now you have 3 bank accounts.\n";

        String name = "Seongje Cho\n";
        String id = "sj\n";
        String password = "123123\n";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(id).append("y\n").append(password);

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

    @Order(5)
    @Test
    void fourthRegister() {
        TossBank tossBank = TossBank.getInstance();

        String msg = "Please enter your name: Please enter the ID: \nAlready used ID: sj\nWould you like to create an additional account? [yes/no]: Please enter the password: \nBank registration failed.\nYou already have 3 bank accounts.\n";

        String name = "Seongje Cho\n";
        String id = "sj\n";
        String password = "123123\n";

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(id).append("y\n").append(password);

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

    @Order(6)
    @Test
    void updateName() {
        
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void withdraw() {
    }
}