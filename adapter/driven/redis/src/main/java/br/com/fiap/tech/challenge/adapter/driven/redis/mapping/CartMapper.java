package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;


import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import br.com.fiap.tech.challenge.domain.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import static br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisConfiguration.CACHE_REDIS_TTL;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class CartMapper {

    @Autowired
    private Environment env;

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    public abstract CartEntity toCartEntity (Cart source);

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Cart.class, CartEntity.class)
                .addMapping(Cart::uuid, CartEntity::setId)
                .addMapping(Cart::customer, CartEntity::setCustomer)
                .addMapping(Cart::items, CartEntity::setItems)
                .addMappings(m -> m.map(src -> env.getProperty(CACHE_REDIS_TTL, Long.class), CartEntity::setTtl));
    }
}
