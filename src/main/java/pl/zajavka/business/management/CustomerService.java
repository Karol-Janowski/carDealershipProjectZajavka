package pl.zajavka.business.management;

import lombok.AllArgsConstructor;
import pl.zajavka.business.dao.CustomerDAO;
import pl.zajavka.infrastructure.database.entity.CustomerEntity;

import java.util.Optional;

@AllArgsConstructor
public class CustomerService {

    private final CustomerDAO customerDAO;

    public void issueInvoice(CustomerEntity customer) {
        customerDAO.issueInvoice(customer);
    }

    public CustomerEntity findCustomer(String mail) {
        Optional<CustomerEntity> customer = customerDAO.findByEmail(mail);
        if (customer.isEmpty()) {
            throw new RuntimeException("Could not find customer with email: [%s]".formatted(mail));
        }
        return customer.get();
    }
}
