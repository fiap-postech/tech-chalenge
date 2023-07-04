package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.domain.entity.CartItem;
import br.com.fiap.tech.challenge.domain.valueobject.Quantity;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

@Mapper
public class RedisCartItemEntityToCartItemMapping implements RedisTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CartItemEntity.class, CartItem.class)
                .setProvider(cartItemProvider());
    }

    private static Provider<CartItem> cartItemProvider() {
        return provision -> {
            var entity = (CartItemEntity) provision.getSource();
            return CartItem.builder()
                    .quantity(Quantity.of(entity.getQuantity()))
                    .build();
        };
    }
}