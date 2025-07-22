package api.commerce.express.repository;

import api.commerce.express.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IAdministratorCrudService extends JpaRepository<Administrator, Long> {
}
