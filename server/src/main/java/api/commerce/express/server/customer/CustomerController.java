package api.commerce.express.server.customer;

import api.commerce.express.services.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Shannon
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    private final ICustomerService customerService;
    private final CustomerAssembler customerAssembler;

    public CustomerController(
            @Autowired ICustomerService customerService,
            @Autowired CustomerAssembler customerAssembler
    ) {
        this.customerService = customerService;
        this.customerAssembler = customerAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return customerService.findById(id)
                .map(customer -> ResponseEntity.ok(customerAssembler.toModel(customer)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<?> getAllCustomers(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ) {
        if (offset < 0 || limit <= 0) {
            return ResponseEntity.badRequest().build();
        }

        var customerModels = customerService.getAllCustomers(offset, limit);
        var customerCollection = customerAssembler.toCollectionModel(customerModels, offset, limit);

        return ResponseEntity.ok(customerCollection);
    }

//    @RequestMapping(value = "/memberCategoryId={id}", method = RequestMethod.GET)
//    public String categorySelected(@PathVariable("id") Long id, Model model) {
//        categoryModel(model);
//        List<Product> products = CE_SERVICES.getProductService().getProducts(id);
//        Category category = CE_CRUDS.getCategoryCrudService().findById(id);
//        model.addAttribute("categoryName", category.getCategoryName());
//        model.addAttribute("products", products);
//        model.addAttribute("title", "Products in Category");
//        model.addAttribute("categoryName", category.getCategoryName());
//        return "customer/categorySelected";
//    }
//
//    @RequestMapping(value = "/addCustomerToSession", method = RequestMethod.GET)
//    public String addCustomerToSession() {
//
//        return "customer/addCustomerToSession";
//    }
//
//    @RequestMapping(value = "/memberSession", method = RequestMethod.POST)
//    public String session() {
//
//        return "customer/addToSession";
//    }
//
//    @RequestMapping(value = "/memberHome", method = RequestMethod.GET)
//    public String memberIndex(Model model) {
//        categoryModel(model);
//        List<Product> products = CE_CRUDS.getProductCrudService().findAll();
//        model.addAttribute("categoryName", "All Categories");
//        model.addAttribute("products", products);
//        model.addAttribute("title", "Customer Home");
//        model.addAttribute("active", "/");
//        return "customer/index";
//    }
//
//    @RequestMapping(value = "/memberAboutUs", method = RequestMethod.GET)
//    public String aboutUs(Model model) {
//        categoryModel(model);
//        model.addAttribute("title", "About Us");
//        model.addAttribute("active", "aboutus");
//        return "customer/aboutus";
//    }
//
//    @RequestMapping(value = "/memberContactUs", method = RequestMethod.GET)
//    public String contactUs(Model model) {
//        categoryModel(model);
//        model.addAttribute("title", "Contact Us");
//        model.addAttribute("active", "contactus");
//        return "customer/contactus";
//    }
//
//    @RequestMapping(value = "/memberHelp", method = RequestMethod.GET)
//    public String help(Model model) {
//        categoryModel(model);
//
//        model.addAttribute("title", "Help");
//        model.addAttribute("active", "help");
//        return "customer/help";
//    }
//
//    @RequestMapping(value = "/memberOrders", method = RequestMethod.GET)
//    public String orders(Model model) {
//        categoryModel(model);
//        model.addAttribute("title", "Orders");
//        model.addAttribute("active", "orders");
//        return "customer/orders";
//    }
//
//    @RequestMapping(value = "/memberWishlist", method = RequestMethod.GET)
//    public String wishlist(Model model) {
//        categoryModel(model);
//        model.addAttribute("title", "Wishlist");
//        model.addAttribute("active", "wishlist");
//        return "customer/wishlist";
//    }
//
//    @RequestMapping(value = "/memberBasket", method = RequestMethod.GET)
//    public String basket(Model model) {
//        categoryModel(model);
//        model.addAttribute("title", "Basket");
//        return "customer/basket";
//    }
//
//    @RequestMapping(value = "/memberOrderID={id}", method = RequestMethod.GET)
//    public String productsOnOrder(@PathVariable("id") String orderID, HttpSession session, Model model) {
//        categoryModel(model);
//        session.setAttribute("orderID", orderID);
//        model.addAttribute("title", "Products on Order");
//        return "customer/productsOnOrder";
//    }
//
//    private void categoryModel(Model model) {
//        List<Category> categories = CE_CRUDS.getCategoryCrudService().findAll();
//        model.addAttribute("categories", categories);
//    }
}
