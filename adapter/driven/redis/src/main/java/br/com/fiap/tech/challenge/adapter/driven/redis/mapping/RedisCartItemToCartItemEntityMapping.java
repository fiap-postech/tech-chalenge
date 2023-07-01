package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ComboProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.quantityToIntegerConverter;
import static java.util.Objects.isNull;

@Mapper
public class RedisCartItemToCartItemEntityMapping implements RedisTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CartItem.class, CartItemEntity.class)
                .addMappings(mapping -> mapping.using(productConverter(mapper)).map(CartItem::product, CartItemEntity::setProduct))
                .addMappings(mapping -> mapping.using(quantityToIntegerConverter()).map(CartItem::quantity, CartItemEntity::setQuantity));
    }

    private Converter<? extends Product, ProductEntity> productConverter(ModelMapper mapper) {
        return ctx -> {
            var product = ctx.getSource();

            if (isNull(product)){
                return null;
            }

            return switch (product.category()){
                case COMBO -> mapper.map(product, ComboProductEntity.class);
                case DESSERT, SANDWICH, BEVERAGE, SIDE_DISH -> mapper.map(product, ProductEntity.class);
            };
        };

    }
}