package api.commerce.express.server.order;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cna canal walk
 */
@Controller
@RequestMapping("order")
public class OrderController {
    public Class<?> getOrder(Long id) throws Exception {
        throw new Exception("Method not implemented");
    }

    public Class<?> getAllOrders() throws Exception {
        throw new Exception("Method not implemented");
    }

//    @Autowired
//    private IOrderService orderService;
//
//    @Autowired
//    private OrderAssembler ordersAssembler;
//
//    @GetMapping("/{customerId}")
//    public ResponseEntity<List<OrdersResource>> getOrder() {
//        List<Orders> orders = orderService.getAllOrders();
//        List<OrdersResource> resource = ordersAssembler.toResources(orders);
//        return new ResponseEntity<List<OrdersResource>>(resource, HttpStatus.OK);
//    }
}

