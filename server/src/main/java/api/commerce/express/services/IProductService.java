package api.commerce.express.services;

import api.commerce.express.domain.Product;

import java.util.List;

/**
 * @author Owner
 */
public interface IProductService {

    List<Product> getProducts(Long categoryID) throws Exception;

    void updateInStock(String order) throws Exception;

    List<Product> getProducts() throws Exception;
}
