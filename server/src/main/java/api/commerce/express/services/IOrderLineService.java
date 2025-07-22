package api.commerce.express.services;

import api.commerce.express.domain.OrderLine;

import java.util.List;

/**
 * @author Ronald
 */
public interface IOrderLineService {

    Integer getUniqueOrderLineNumber() throws Exception;

    List<OrderLine> getAllOrderLine() throws Exception;
}
