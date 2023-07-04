package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.CartItem;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

@Mapper
@AllArgsConstructor
public class RemoveCartItemRequestToDomainMapping implements RestTypeMapConfiguration {

    private final FindProductByUUIDService findProductByUUIDService;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(RemoveCartItemRequest.class, CartItem.class)
                .setProvider(cartItemProvider());
    }

    private Provider<CartItem> cartItemProvider() {
        return provision -> {
            var request = (RemoveCartItemRequest) provision.getSource();
            var product = getProduct(request.getProductId());
            return CartItem.builder()
                    .product(product)
                    .build();
        };
    }

    private Product getProduct(String uuid) {
        return findProductByUUIDService.get(UUID.fromString(uuid));
    }
}