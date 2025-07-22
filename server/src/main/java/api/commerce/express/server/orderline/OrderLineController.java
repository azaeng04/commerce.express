package api.commerce.express.server.orderline;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cna canal walk
 */
@Controller
@RequestMapping("orderline")
public class OrderLineController {
    public Class<?> getOrderLine(Long id) throws Exception {
        throw new Exception("Method not implemented");
    }

    public Class<?> getAllOrderLines() throws Exception {
        throw new Exception("Method not implemented");
    }

//    @Autowired
//    private OrderLineService orderLineService;
//    @Autowired
//    private OrderLineAssembler orderLineAssembler;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<List<OrderLineResource>> getAppoint() {
//        List<OrderLine> orderline = orderLineService.getAllOrderLine();
//        List<OrderLineResource> resource = orderLineAssembler.toResources(orderline);
//        return new ResponseEntity<List<OrderLineResource>>(resource, HttpStatus.OK);
//    }
}
