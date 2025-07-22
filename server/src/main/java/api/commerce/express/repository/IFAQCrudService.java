package api.commerce.express.repository;

import api.commerce.express.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IFAQCrudService extends JpaRepository<FAQ, Long> {
}
