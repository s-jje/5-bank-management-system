package customer;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private final String id;
    private String password;
    private final List<String> accountNumber = new ArrayList<>();

    public Customer(String name, String id, String password, String accountNumber) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.accountNumber.add(accountNumber);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getAccountNumber() {
        return accountNumber;
    }
}