package api.commerce.express.repository;

import api.commerce.express.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IWishListCrudService extends JpaRepository<WishList, Long> {
}
