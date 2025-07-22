package api.commerce.express.server.category;


import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author cna canal walk
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@Param("id") Long id) throws Exception {
        throw new Exception("Method not implemented");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() throws Exception {
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
