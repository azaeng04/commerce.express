package api.commerce.express.services.impl;

import api.commerce.express.client.web.model.CustomerModel;
import api.commerce.express.domain.AccessDetail;
import api.commerce.express.domain.Customer;
import api.commerce.express.repository.IAccessDetailsCrudService;
import api.commerce.express.repository.ICustomerCrudService;
import api.commerce.express.services.ICustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Owner
 */
@Service
@Transactional
public class CustomerService implements ICustomerService {
    private final ICustomerCrudService customerCrudService;
    private final IAccessDetailsCrudService accessDetailsCrudService;

    public CustomerService(
            ICustomerCrudService customerCrudService,
            IAccessDetailsCrudService accessDetailsCrudService
    ) {
        this.customerCrudService = customerCrudService;
        this.accessDetailsCrudService = accessDetailsCrudService;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerCrudService.findById(id);
    }

    @Override
    public Optional<Customer> getCustomerByUsername(String username) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<AccessDetail> accessDetailsPage;
        do {
            accessDetailsPage = this.accessDetailsCrudService.findAll(pageable);
            var accessDetailsContent = accessDetailsPage.getContent();
            for (AccessDetail accessDetail : accessDetailsContent) {
                if (Objects.equals(accessDetail.getUsername(), username)) {
                    return Optional.ofNullable(accessDetail.getCustomer());
                }
            }
            pageable = pageable.next();
        } while (!accessDetailsPage.isLast());
        return Optional.empty();
    }

    @Override
    public List<Customer> getAllCustomers(int offset, int limit) {
        Pageable pageable = PageRequest.of(offset / limit, limit);
        return this.customerCrudService.findAll(pageable).toList();
    }

    @Override
    public void createCustomer(CustomerModel model) {

        Customer cust = new Customer();

        cust.setUserID(model.getUserID());
        cust.setAccessDetails(null);
        cust.setAddresses(null);
        cust.setContact(null);
        cust.setDateOfBirth(model.getDateOfBirth());
        cust.setFirstName(model.getFirstName());
        cust.setGender(model.getGender());
        cust.setLastName(model.getLastName());
        cust.setMiddleName(model.getMiddleName());

        customerCrudService.save(cust);
    }
}
