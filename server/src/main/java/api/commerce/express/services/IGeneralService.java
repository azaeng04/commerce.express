package api.commerce.express.services;

import java.util.Random;

/**
 * @author Ronald
 */
public interface IGeneralService {
    Integer generateRandomNumber(Integer start, Integer end, Random randomize);
}
