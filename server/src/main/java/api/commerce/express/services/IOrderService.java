package api.commerce.express.services;

import api.commerce.express.domain.Customer;
import api.commerce.express.domain.Order;
import api.commerce.express.domain.OrderLine;
import api.commerce.express.domain.Product;

import java.util.List;
import java.util.Map;

/**
 * @author Owner
 */
public interface IOrderService {

    List<Order> getOrders(String customerID) throws Exception;

    Integer getUniqueOrderNumber() throws Exception;

    void checkout(Map<String, String> cart, Customer customer) throws Exception;

    String orderTable(List<Order> orders) throws Exception;

    List<Product> getProducts(String orderNumber) throws Exception;

    List<OrderLine> getOrderLine(String orderNumber) throws Exception;

    String displayProductsOnOrder(String orderID) throws Exception;

    List<Order> getAllOrders() throws Exception;
}
