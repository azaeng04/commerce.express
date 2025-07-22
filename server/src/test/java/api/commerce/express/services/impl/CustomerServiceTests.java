package api.commerce.express.services.impl;

import api.commerce.express.config.TestConfig;
import api.commerce.express.domain.*;
import api.commerce.express.repository.ICustomerCrudService;
import api.commerce.express.services.ICustomerService;

import java.util.Optional;

import api.commerce.express.test.doubles.fakes.FakeAccessDetailCrudService;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestConfig.class)
public class CustomerServiceTests {

	public CustomerServiceTests() {}

	@Autowired
	private ICustomerService customerService;

	private ICustomerCrudService customerCrudService;

	@Autowired
	private FakeAccessDetailCrudService fakeAccessDetailCrudService;

	@Test
	public void shouldGetACustomerByUsername_WhenCustomerUsernameIsValid() {
		String username = "fakeuser";

		AccessDetail accessDetail = new AccessDetail();
		accessDetail.setUsername(username);

		Customer customer = new Customer();
		accessDetail.setCustomer(customer);
		var expectedResult = Optional.of(customer);

		fakeAccessDetailCrudService.addAccessDetail(accessDetail);

		Optional<Customer> actualResult = customerService.getCustomerByUsername(username);

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	public void shouldNotGetACustomerByUsername_WhenCustomerUsernameDoesNotExist() {
		String username = "userdoesnotexist";
		var expectedResult = Optional.empty();

		Optional<Customer> actualResult = customerService.getCustomerByUsername(username);

		assertThat(actualResult).isEqualTo(expectedResult);
	}
}
