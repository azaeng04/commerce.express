package api.commerce.express.server.faq;

import api.commerce.express.domain.FAQ;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class FAQAssembler implements RepresentationModelAssembler<FAQ, EntityModel<FAQ>> {
    @Override
    public EntityModel<FAQ> toModel(FAQ faq) {
        try {
            return EntityModel.of(faq,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FAQController.class).getFAQ(faq.getId())).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(FAQController.class).getAllFAQs()).withRel("faqs")
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
