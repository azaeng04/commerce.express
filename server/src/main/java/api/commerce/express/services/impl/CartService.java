package api.commerce.express.services.impl;

//import api.commerce.express.app.facade.CommerceExpressCRUD;

import api.commerce.express.domain.Product;
import api.commerce.express.repository.IProductCrudService;
import api.commerce.express.services.ICartService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Optional;

/**
 * @author om95446
 */
@Service("CartService")
@Transactional
public class CartService implements ICartService {

    private final IProductCrudService productCrudService;

    public CartService(IProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

    @Override
    public String displayCart(Map<String, String> cart) {
        StringBuilder table = new StringBuilder();
        String productID;
        String quantity;
        Optional<Product> product;
        Double subtotal;
        if (!cart.isEmpty()) {
            DecimalFormat decimalFormat = new DecimalFormat("###########.00");
            Double grandTotal = 0.0;
            table = new StringBuilder("<form method='POST' action='checkout'>");
            table.append("<table class='table table-bordered table-hover'>");
            table.append("<th>Product ID</th>");
            table.append("<th>Product Name</th>");
            table.append("<th>Product Price</th>");
            table.append("<th>Quantity</th>");
            table.append("<th>Subtotal</th>");
            for (Map.Entry<String, String> entry : cart.entrySet()) {
                productID = entry.getKey();
                quantity = entry.getValue();
                product = productCrudService.findById(Long.parseLong(productID));
                if (product.isPresent()) {
                    Product presentProduct = product.get();
                    subtotal = Double.parseDouble(presentProduct.getProductPrice()) * Integer.parseInt(quantity);
                    table.append("<tr>");
                    table.append("<td>").append(productID).append("</td>");
                    table.append("<td>").append(presentProduct.getProductName()).append("</td>");
                    table.append("<td>R").append(presentProduct.getProductPrice()).append("</td>");
                    table.append("<td><input type'text' name='qty").append(presentProduct.getId()).append("' value='").append(quantity).append("' readonly='true' /></td>");
                    table.append("<td>R").append(decimalFormat.format(subtotal)).append("</td>");
                    table.append("</tr>");
                    grandTotal += subtotal;
                }
            }
            table.append("<tr>");
            table.append("<td colspan='4' style='text-align: right'>Grand Total </td>");
            table.append("<td>R").append(decimalFormat.format(grandTotal)).append("</td>");
            table.append("</tr>");
            table.append("</table>");
            table.append("<input type='submit' class='btn btn-primary btn-large' value='Checkout'/>");
            table.append("</form>");
        }
        return table.toString();
    }
}
