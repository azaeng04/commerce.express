package api.commerce.express.services.impl;

import api.commerce.express.repository.IAccessDetailsCrudService;
import api.commerce.express.services.IAdministratorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Owner
 */
@Service("AccessDetailsService")
@Transactional
public class AccessDetailService implements IAdministratorService {
    private final IAccessDetailsCrudService accessDetailsCrudService;

    public AccessDetailService(IAccessDetailsCrudService accessDetailsCrudService) {
        this.accessDetailsCrudService = accessDetailsCrudService;
    }
}