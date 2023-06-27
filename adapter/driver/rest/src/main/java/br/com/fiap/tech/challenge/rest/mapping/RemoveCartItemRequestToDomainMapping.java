package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.domain.Quantity;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

@Mapper
//TODO: deletar
public class RemoveCartItemRequestToDomainMapping implements RestTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(RemoveCartItemRequest.class, CartItem.class)
                .setProvider(cartItemProvider());
    }

    private static Provider<CartItem> cartItemProvider() {
        return provision -> {
            var request = (RemoveCartItemRequest) provision.getSource();
            return CartItem.builder()
//                    .uuid(UUID.fromString(request.getId()))
//                    .discount(Discount.of(makeMoney(request.getDiscount())))
//                    .price(Price.of(makeMoney(request.getPrice())))
                    .quantity(Quantity.of(request.getQuantity()))
                    .build();
        };
    }
}