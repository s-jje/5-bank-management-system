package customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {

    private final List<Customer> customerList;

    public CustomerList() {
        this.customerList = new ArrayList<>();
    }

    public Customer getCustomer(String id, String pw) {
        for (Customer customer : customerList) {
            if (id.equals(customer.getId()) && pw.equals(customer.getPassword())) {
                return customer;
            }
        }
        throw new RuntimeException("Not found customer.");
    }
}
