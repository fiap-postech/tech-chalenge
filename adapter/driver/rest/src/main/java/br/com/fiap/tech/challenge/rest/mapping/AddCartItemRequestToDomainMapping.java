package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.Quantity;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

@Mapper
public class AddCartItemRequestToDomainMapping implements RestTypeMapConfiguration {

    private final FindProductByUUIDService findProductByUUIDService;

    public AddCartItemRequestToDomainMapping(FindProductByUUIDService findProductByUUIDService) {
        this.findProductByUUIDService = findProductByUUIDService;
    }

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(AddCartItemRequest.class, CartItem.class)
                .setProvider(cartItemProvider());
    }

    private Provider<CartItem> cartItemProvider() {
        return provision -> {
            var request = (AddCartItemRequest) provision.getSource();
            var product = getProduct(request.getProductId());
            return CartItem.builder()
                    .product(product)
                    .quantity(Quantity.of(request.getQuantity()))
                    .build();
        };
    }

    private Product getProduct(String uuid) {
        return findProductByUUIDService.get(UUID.fromString(uuid));
    }
}