package api.commerce.express.services.impl;

import api.commerce.express.services.IAdministratorService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Owner
 */
@Service("AdministratorService")
@Transactional
public class AdministratorService implements IAdministratorService {

    public AdministratorService() {
    }
}