import java.util.UUID;

public class Customer {

    private final UUID uuid;
    private final String name;
    private final String accountNumber;

    public Customer(UUID uuid, String name, String accountNumber) {
        this.uuid = uuid;
        this.name = name;
        this.accountNumber = accountNumber;
    }
}
