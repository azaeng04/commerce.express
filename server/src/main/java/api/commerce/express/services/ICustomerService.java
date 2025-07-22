package api.commerce.express.services;

import api.commerce.express.client.web.model.CustomerModel;
import api.commerce.express.domain.Customer;

import java.util.List;
import java.util.Optional;

/**
 * @author Owner
 */
public interface ICustomerService {
    Optional<Customer> findById(Long id);

    Optional<Customer> getCustomerByUsername(String username);

    List<Customer> getAllCustomers(int offset, int limit);

    void createCustomer(CustomerModel model);
}
