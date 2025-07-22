package api.commerce.express;

import api.commerce.express.domain.Customer;
import api.commerce.express.repository.IAccessDetailsCrudService;
import api.commerce.express.repository.ICustomerCrudService;
import api.commerce.express.services.impl.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationTests {

	public ApplicationTests() {}

	@Autowired
	private CustomerService customerService;

	@MockitoBean
	private ICustomerCrudService customerCrudServiceMock;

	@MockitoBean
	private IAccessDetailsCrudService accessDetailsCrudServiceMock;

	@Test
	public void findById() {
		Long id = 123L;
		Optional<Customer> expected = Optional.empty();
		Optional<Customer> actual = customerService.findById(id);

		assertThat(expected).isEqualTo(actual);
	}

	@Test
	public void testGetCustomerByUsername() {
		var customer = customerService.getCustomerByUsername("eEngelbrecht1");

		assertThat(customer.isPresent()).isTrue();
	}
}
