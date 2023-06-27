package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import org.modelmapper.ModelMapper;

@Mapper
public class CartToCartResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Cart.class, CartResponse.class)
                .addMapping(Cart::uuid, CartResponse::setId)
                .addMapping(Cart::items, CartResponse::setItems);
    }
}
