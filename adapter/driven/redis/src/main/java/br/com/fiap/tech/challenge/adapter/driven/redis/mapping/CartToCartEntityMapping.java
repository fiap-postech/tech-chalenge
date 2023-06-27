package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartEntity;
import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;

import static br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisConfiguration.CACHE_REDIS_TTL;

@Mapper
@AllArgsConstructor
public class CartToCartEntityMapping implements RedisTypeMapConfiguration {

    private Environment env;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Cart.class, CartEntity.class)
                .addMapping(Cart::uuid, CartEntity::setId)
                .addMapping(Cart::items, CartEntity::setItems)
                .addMappings(m -> m.map(src -> env.getProperty(CACHE_REDIS_TTL, Long.class), CartEntity::setTtl));
    }
}