package api.commerce.express.server.product;


import org.springframework.web.bind.annotation.RestController;

/**
 * @author cna canal walk
 */
@RestController
public class ProductController {
    public Class<?> getProduct(Long id) throws Exception {
        throw new Exception("Method not implemented");
    }

    public Class<?> getAllProducts() throws Exception {
        throw new Exception("Method not implemented");
    }

//    private static final CommerceExpressServices CE_SERVICES = CommerceExpressServices.getCommerceExpressServices();
//    private static final CommerceExpressCRUD CE_CRUDS = CommerceExpressCRUD.getCommerceExpressCRUD();
//    List<Product> products = new ArrayList<Product>();
//
//    @Autowired
//    private ProductAssembler productResourceAssembler;
//
//    @RequestMapping(value = "product/{id}", method = RequestMethod.GET)
//    public ResponseEntity getProduct(@PathVariable("id") Long id) {
//        clearProductListIfNotEmpty(products);
//        Product product = CE_CRUDS.getProductCrudService().findById(id);
//        products.add(product);
//        List<ProductResource> resource = productResourceAssembler.toResources(products);
//        return new ResponseEntity<List<ProductResource>>(resource, HttpStatus.OK);
//    }
//
//    @RequestMapping(value = "products", method = RequestMethod.GET)
//    public ResponseEntity<List<ProductResource>> getProducts() {
//        clearProductListIfNotEmpty(products);
//        products = CE_SERVICES.getProductService().getProducts();
//        List<ProductResource> resource = productResourceAssembler.toResources(products);
//        return new ResponseEntity<List<ProductResource>>(resource, HttpStatus.OK);
//    }
//
//    public void clearProductListIfNotEmpty(List<Product> products){
//        if (!products.isEmpty()) {
//            products.clear();
//        }
//    }
}
