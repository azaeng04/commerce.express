package api.commerce.express.services.impl;

import api.commerce.express.services.IGeneralService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author Ronald
 */
@Service("GeneralService")
@Transactional
public class GeneralService implements IGeneralService {

    public GeneralService() {
    }

    @Override
    public Integer generateRandomNumber(Integer start, Integer end, Random randomize) {
        if (start > end) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        Long range = (long) end - (long) start + 1;
        Long fraction = (long) (range * randomize.nextDouble());
        Integer randomNumber = (int) (fraction + start);
        return randomNumber;
    }
}
