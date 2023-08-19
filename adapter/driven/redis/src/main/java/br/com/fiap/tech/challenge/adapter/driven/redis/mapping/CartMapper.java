package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;


import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CustomerEntity;
import br.com.fiap.tech.challenge.domain.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;

import static br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisConfiguration.CACHE_REDIS_TTL;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class CartMapper {

    @Autowired
    protected Environment env;

    @Autowired
    protected CustomerMapper customerMapper;

    @Autowired
    protected CartItemMapper cartItemMapper;

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "customer", source = "source", qualifiedByName = "getCustomerEntity")
    @Mapping(target = "items", source = "source", qualifiedByName = "getCartItemsEntity")
    @Mapping(target = "ttl", source = "source", qualifiedByName = "getTtl")
    public abstract CartEntity toCartEntity (Cart source);

    @Named("getCustomerEntity")
    CustomerEntity getCustomerEntity(Cart source){
        return customerMapper.toCustomerEntity(source.customer());
    }

    @Named("getCartItemsEntity")
    List<CartItemEntity> getCartItems(Cart source){
        return source.items().stream()
                .map(cartItemMapper::toCartItemEntity)
                .toList();
    }

    @Named("getTtl")
    Long getTtl(Cart source){
        return env.getProperty(CACHE_REDIS_TTL, Long.class);
    }
}
