package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;

@Mapper
public class CartToCartEntityMapping implements RedisTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Cart.class, CartEntity.class)
                .addMapping(Cart::uuid, CartEntity::setId)
                .addMapping(Cart::items, CartEntity::setItems);
    }
}