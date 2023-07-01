package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.CheckoutService;
import br.com.fiap.tech.challenge.rest.resource.response.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;

@RestController
@RequestMapping("/cart")
public class CartResource {

    private final ModelMapper mapper;
    private final CheckoutService checkoutService;

    public CartResource(@Qualifier(REST_MODEL_MAPPER) ModelMapper mapper, CheckoutService checkoutService) {
        this.mapper = mapper;
        this.checkoutService = checkoutService;
    }

    @PostMapping("/{uuid}/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse checkout(@PathVariable String uuid) {
        checkoutService.checkout(UUID.fromString(uuid));

        //TODO get response and map it properly

        return new OrderResponse().setId(UUID.randomUUID().toString());
    }
}
