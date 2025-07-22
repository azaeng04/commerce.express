package api.commerce.express.config;

import api.commerce.express.repository.IAccessDetailsCrudService;
import api.commerce.express.test.doubles.fakes.FakeAccessDetailCrudService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestConfig {
    @Bean
    @Primary
    public IAccessDetailsCrudService fakeAccessDetailsCrudService() {
        return new FakeAccessDetailCrudService();
    }
}
