package api.commerce.express.services.impl;

import api.commerce.express.domain.Customer;
import api.commerce.express.domain.Order;
import api.commerce.express.domain.OrderLine;
import api.commerce.express.domain.Product;
import api.commerce.express.services.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author Owner
 */
@Service("OrderService")
@Transactional
public class OrderService implements IOrderService {
    public OrderService() {
    }

    @Override
    public List<Order> getOrders(String customerID) throws Exception {
        throw new Exception("Method not implemented");
//        Customer customer = CE_CRUDS.getCustomerCrudService().getByPropertyName("userID", customerID);
//        List<Orders> orders = customer.getOrders();
//        return orders;
    }

    @Override
    public Integer getUniqueOrderNumber() throws Exception {
        throw new Exception("Method not implemented");
//        Random random = new Random();
//        Integer randomNumber = CE_SERVICES.getGeneralService().generateRandomNumber(10000, 99999, random);
//        Orders orders1;
//        Boolean isFound = true;
//        while (isFound) {
//            orders1 = CE_CRUDS.getOrdersCrudService().getByPropertyName("orderID", "ORD_" + randomNumber);
//            if (orders1 != null) {
//                randomNumber = CE_SERVICES.getGeneralService().generateRandomNumber(10000, 99999, random);
//            } else {
//                isFound = false;
//            }
//        }
//        return randomNumber;
    }

    @Override
    public void checkout(Map<String, String> cart, Customer customer) throws Exception {
        throw new Exception("Method not implemented");
//        List<Orders> orders = CE_SERVICES.getOrderService().getOrders(customer.getUserID());
//        List<OrderLine> orderLine = new ArrayList<OrderLine>();
//        String uniqueID;
//        Product product;
//        OrderLine item;
//        Orders order;
//        String productID;
//        String quantity;
//        for (Map.Entry<String, String> entry : cart.entrySet()) {
//            productID = entry.getKey();
//            quantity = entry.getValue();
//            product = CE_CRUDS.getProductCrudService().findById(Long.parseLong(productID));
//            uniqueID = CE_SERVICES.getOrderLineService().getUniqueOrderLineNumber().toString();
//            item = OrderLineFactory.getOrderLine(uniqueID, Integer.parseInt(quantity), product);
//            orderLine.add(item);
//        }
//        uniqueID = CE_SERVICES.getOrderService().getUniqueOrderNumber().toString();
//        Date date = new DateTime().toDate();
//        order = new OrdersFactory.Builder(uniqueID)
//                .setDateCreated(new SimpleDateFormat("EEEE dd MMM yyyy HH:mm:ss").format(date))
//                .setDateModified(new SimpleDateFormat("EEEE dd MMM yyyy HH:mm:ss").format(date))
//                .setOrderLines(orderLine)
//                .setStatus("Pending Delivery")
//                .buildOrder();
//        orders.add(order);
//        customer.setOrders(orders);
//        CE_CRUDS.getCustomerCrudService().merge(customer);
//        CE_SERVICES.getProductService().updateInStock(order.getOrderID());
    }

    @Override
    public String orderTable(List<Order> orders) throws Exception {
        throw new Exception("Method not implemented");
//        String table = "";
//        if (!orders.isEmpty()) {
//            table = "<table class='table table-bordered table-hover'>";
//            table += "<tr>";
//            table += "<th>Order ID</th>";
//            table += "<th>Date created</th>";
//            table += "<th>Date Modified</th>";
//            table += "<th>Order Status</th>";
//            table += "</tr>";
//            table += "<tr>";
//            table += "<td colspan='3' style='text-align: right'>Number of Orders</td>";
//            table += "<td>" + orders.size() + "</td>";
//            table += "<tr>";
//            for (Orders order : orders) {
//                table += "<tr>";
//                table += "<td><a href='memberOrderID=" + order.getOrderID() + "'>" + order.getOrderID() + "</a></td>";
//                table += "<td>" + order.getDateCreated() + "</td>";
//                table += "<td>" + order.getDateModified() + "</td>";
//                table += "<td>" + order.getStatus() + "</td>";
//                table += "</tr>";
//            }
//            table += "<tr>";
//            table += "<td colspan='3' style='text-align: right'>Number of Orders</td>";
//            table += "<td>" + orders.size() + "</td>";
//            table += "<tr>";
//            table += "</table>";
//        }
//        return table;
    }

    @Override
    public List<Product> getProducts(String orderNumber) throws Exception {
        throw new Exception("Method not implemented");
//        Orders order = CE_CRUDS.getOrdersCrudService().getByPropertyName("orderID", orderNumber);
//        List<Product> products = new ArrayList<Product>();
//        List<OrderLine> orderLines = order.getOrderLines();
//        for (OrderLine orderLine : orderLines) {
//            products.add(orderLine.getProduct());
//        }
//        return products;
    }

    @Override
    public List<OrderLine> getOrderLine(String orderNumber) throws Exception {
        throw new Exception("Method not implemented");
//        Orders order = CE_CRUDS.getOrdersCrudService().getByPropertyName("orderID", orderNumber);
//        return order.getOrderLines();
    }

    @Override
    public String displayProductsOnOrder(String orderID) throws Exception {
        throw new Exception("Method not implemented");
//        DecimalFormat decimalFormat = new DecimalFormat("##########.00");
//        Orders order = CE_CRUDS.getOrdersCrudService().getByPropertyName("orderID", orderID);
//        List<Product> productsOnOrder = CE_SERVICES.getOrderService().getProducts(orderID);
//        List<OrderLine> orderLines = order.getOrderLines();
//        Double subtotal = 0.0;
//        Double grandTotal = 0.0;
//        String table = "";
//        table = orderHeaderDetails(order);
//        table = productHeaderDetails(table, productsOnOrder);
//        for (Product productOnOrder : productsOnOrder) {
//            for (OrderLine orderLine : orderLines) {
//                if (productOnOrder.getId().equals(orderLine.getProduct().getId())) {
//                    subtotal = Double.parseDouble(productOnOrder.getProductPrice()) * orderLine.getQuantity();
//                    table += "<tr>";
//                    table += "<td>" + productOnOrder.getProductID() + "</td>";
//                    table += "<td>" + productOnOrder.getProductName() + "</td>";
//                    table += "<td>" + productOnOrder.getProductPrice() + "</td>";
//                    table += "<td>" + productOnOrder.getDescription() + "</td>";
//                    table += "<td>" + orderLine.getQuantity() + "</td>";
//                    table += "<td>" + decimalFormat.format(subtotal) + "</td>";
//                    table += "</tr>";
//                    grandTotal += subtotal;
//                    break;
//                }
//            }
//        }
//        table += "<tr>";
//        table += "<td colspan='5' style='text-align: right'>Grand Total</td>";
//        table += "<td>" + decimalFormat.format(grandTotal) + "</td>";
//        table += "</tr>";
//        table += "<tr>";
//        table += "<td colspan='5' style='text-align: right'>Number of Products on Order</td>";
//        table += "<td>" + productsOnOrder.size() + "</td>";
//        table += "<tr>";
//        table += "</table>";
//        return table;
    }

    private String orderHeaderDetails(Order order) throws Exception {
        throw new Exception("Method not implemented");
//        String table;
//        table = "<table class='table table-bordered table-hover'>";
//        table += "<tr>";
//        table += "<th>Order ID</th>";
//        table += "<th>Date created</th>";
//        table += "<th>Date Modified</th>";
//        table += "<th>Order Status</th>";
//        table += "</tr>";
//        table += "<tr>";
//        table += "<td>";
//        table += order.getOrderID();
//        table += "</td>";
//        table += "<td>";
//        table += order.getDateCreated();
//        table += "</td>";
//        table += "<td>";
//        table += order.getDateModified();
//        table += "</td>";
//        table += "<td>";
//        table += order.getStatus();
//        table += "</td>";
//        table += "</tr>";
//        table += "</table>";
//        return table;
    }

    private String productHeaderDetails(String table, List<Product> productsOnOrder) throws Exception {
        throw new Exception("Method not implemented");
//        table += "<table class='table table-bordered table-hover'>";
//        table += "<tr>";
//        table += "<th>Product ID</th>";
//        table += "<th>Product Name</th>";
//        table += "<th>Product Price</th>";
//        table += "<th>Product Description</th>";
//        table += "<th>Quantity</th>";
//        table += "<th>Subtotal</th>";
//        table += "</tr>";
//        table += "<tr>";
//        table += "<td colspan='5' style='text-align: right'>Number of Products on Order</td>";
//        table += "<td>" + productsOnOrder.size() + "</td>";
//        table += "<tr>";
//        return table;
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        throw new Exception("Method not implemented");

//        return CE_CRUDS.getOrdersCrudService().findAll();
    }
}
