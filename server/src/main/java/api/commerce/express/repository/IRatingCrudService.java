package api.commerce.express.repository;

import api.commerce.express.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IRatingCrudService extends JpaRepository<Rating, Long> {
}
