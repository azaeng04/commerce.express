package api.commerce.express.repository;

import api.commerce.express.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IAddressCrudService extends JpaRepository<Address, Long> {
}
