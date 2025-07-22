package api.commerce.express.services.impl;

import api.commerce.express.domain.OrderLine;
import api.commerce.express.services.IOrderLineService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Owner
 */
@Service("OrderLineService")
@Transactional
public class OrderLineService implements IOrderLineService {

    public OrderLineService() {
    }

    @Override
    public Integer getUniqueOrderLineNumber() throws Exception {
        throw new Exception("Method not implemented");
//        Random random = new Random();
//        Integer randomNumber = CE_SERVICES.getGeneralService().generateRandomNumber(10000, 99999, random);
//        OrderLine orderLine;
//        Boolean isFound = true;
//        while (isFound) {
//            orderLine = CE_CRUDS.getOrderLineCrudService().getByPropertyName("orderLineID", "ORL_" + randomNumber);
//            if (orderLine != null) {
//                randomNumber = CE_SERVICES.getGeneralService().generateRandomNumber(10000, 99999, random);
//            } else {
//                isFound = false;
//            }
//        }
//        return randomNumber;
    }

    @Override
    public List<OrderLine> getAllOrderLine() throws Exception {
        throw new Exception("Method not implemented");

//        return CE_CRUDS.getOrderLineCrudService().findAll();
    }
}