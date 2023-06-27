package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.domain.Quantity;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

@Mapper
public class CartItemEntityToCartItemMapping implements TypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CartItemEntity.class, CartItem.class)
                .setProvider(cartItemProvider());
    }

    private static Provider<CartItem> cartItemProvider() {
        return provision -> {
            var entity = (CartItemEntity) provision.getSource();
            return CartItem.builder()
//                    .uuid(UUID.fromString(entity.getId()))
//                    .discount(Discount.of(makeMoney(entity.getDiscount())))
//                    .price(Price.of(makeMoney(entity.getPrice())))
                    .quantity(Quantity.of(entity.getQuantity()))
                    .build();
        };
    }
}