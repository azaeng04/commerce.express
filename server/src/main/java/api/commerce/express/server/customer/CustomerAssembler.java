package api.commerce.express.server.customer;

import api.commerce.express.domain.Customer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                        .getCustomerById(customer.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                        .getAllCustomers(0, 10)).withRel("customers"));
    }

    public CollectionModel<EntityModel<Customer>> toCollectionModel(List<Customer> customers, int offset, int limit) {
        List<EntityModel<Customer>> models = customers.stream()
                .map(this::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(models,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                        .getAllCustomers(offset, limit)).withSelfRel());
    }
}
