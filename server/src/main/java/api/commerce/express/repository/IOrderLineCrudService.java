package api.commerce.express.repository;

import api.commerce.express.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IOrderLineCrudService extends JpaRepository<OrderLine, Long> {
}
