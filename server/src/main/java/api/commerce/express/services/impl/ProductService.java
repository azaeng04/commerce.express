package api.commerce.express.services.impl;

import api.commerce.express.domain.Product;
import api.commerce.express.services.IProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Owner
 */
@Service("ProductService")
@Transactional
public class ProductService implements IProductService {

    public ProductService() {}

    @Override
    public void updateInStock(String orderID) throws Exception {
        throw new Exception("Method not implemented");
//        Orders order = commerceExpressCRUD.getOrdersCrudService().getByPropertyName("orderID", orderID);
//        List<OrderLine> productsOnOrder = order.getOrderLines();
//        for (OrderLine productOrdered : productsOnOrder) {
//            Long productID = productOrdered.getProduct().getId();
//            Product product = commerceExpressCRUD.getProductCrudService().findById(productID);
//
//            ProductStatus productStatus = product.getProductStatus();
//            Integer currentInStockOfProduct = productStatus.getInStock();
//            Integer amountOrderedOfProduct = productOrdered.getQuantity();
//            Integer inStockOfProductNow = currentInStockOfProduct - amountOrderedOfProduct;
//
//            productStatus.setInStock(inStockOfProductNow);
//            product.setProductStatus(productStatus);
//            commerceExpressCRUD.getProductCrudService().merge(product);
//        }
    }

    @Override
    public List<Product> getProducts(Long categoryID) throws Exception {
        throw new Exception("Method not implemented");
//        Category category = commerceExpressCRUD.getCategoryCrudService().findById(categoryID);
//        List<Product> products = category.getProducts();
//        return products;
    }

    @Override
    public List<Product> getProducts() throws Exception {
        throw new Exception("Method not implemented");
//        return commerceExpressCRUD.getProductCrudService().findAll();

    }
}