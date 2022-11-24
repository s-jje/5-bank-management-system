public class Customer {

    private final String name;

    private final String id;
    private final String password;
    private final String accountNumber;

    public Customer(String name, String id, String password, String accountNumber) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.accountNumber = accountNumber;
    }
}