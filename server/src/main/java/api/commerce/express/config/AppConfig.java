package api.commerce.express.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(
        basePackages = "api.commerce.express",
        excludeFilters = @ComponentScan.Filter(Configuration.class)
)
@EntityScan(basePackages = "api.commerce.express.domain")
@EnableJpaRepositories(basePackages = "api.commerce.express.repository")
public class AppConfig {
    @Bean
    public OpenAPI appInfo() {
        return new OpenAPI().info(new Info()
                .title("Commerce Express REST API")
                .description("This consists of the CRUD operations that can be performed on the Commerce Express REST API"));
    }

//    @Value("${database.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${database.url}")
//    private String url;
//
//    @Value("${database.username}")
//    private String username;
//
//    @Value("${database.password}")
//    private String password;
//
//    @Value("${database.platform}")
//    private String platform;
//
//    @Value("${database.show-sql:false}")
//    private Boolean showSql;

//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName(driverClassName);
//        ds.setUrl(url);
//        ds.setUsername(username);
//        ds.setPassword(password);
//        ds.setInitialSize(5);
//        ds.setMaxTotal(10);
//        ds.setDefaultAutoCommit(true);
//        ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//        return ds;
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(dataSource());
//        emf.setJpaVendorAdapter(jpaVendorAdapter());
//        return emf;
//    }
//
//    @Bean
//    public JpaVendorAdapter jpaVendorAdapter() {
//        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
//        adapter.setDatabasePlatform(platform);
//        adapter.setShowSql(showSql);
//        adapter.setGenerateDdl(showSql);
//        return adapter;
//    }

//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        return new JpaTransactionManager(emf);
//    }

//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
}