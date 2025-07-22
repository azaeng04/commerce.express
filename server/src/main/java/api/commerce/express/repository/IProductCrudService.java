package api.commerce.express.repository;

import api.commerce.express.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IProductCrudService extends JpaRepository<Product, Long> {
}
