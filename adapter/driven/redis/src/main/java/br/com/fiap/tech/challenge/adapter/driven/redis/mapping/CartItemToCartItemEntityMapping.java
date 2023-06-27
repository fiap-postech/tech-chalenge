package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.quantityToIntegerConverter;

@Mapper
public class CartItemToCartItemEntityMapping implements RedisTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CartItem.class, CartItemEntity.class)
                .addMapping(CartItem::product, CartItemEntity::setProduct)
//                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(CartItem::price, CartItemEntity::setPrice))
//                .addMappings(mapping -> mapping.using(discountToBigDecimalConverter()).map(CartItem::discount, CartItemEntity::setDiscount))
                .addMappings(mapping -> mapping.using(quantityToIntegerConverter()).map(CartItem::quantity, CartItemEntity::setQuantity))
        ;
    }
}