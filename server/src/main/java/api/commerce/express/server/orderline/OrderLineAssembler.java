package api.commerce.express.server.orderline;

import api.commerce.express.domain.OrderLine;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderLineAssembler implements RepresentationModelAssembler<OrderLine, EntityModel<OrderLine>> {
    @Override
    public EntityModel<OrderLine> toModel(OrderLine orderLine) {
        try {
            return EntityModel.of(orderLine,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderLineController.class).getOrderLine(orderLine.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderLineController.class).getAllOrderLines()).withRel("order-lines"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
