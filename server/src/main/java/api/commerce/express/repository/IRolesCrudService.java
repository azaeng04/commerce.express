package api.commerce.express.repository;

import api.commerce.express.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IRolesCrudService extends JpaRepository<Role, Long> {
}
