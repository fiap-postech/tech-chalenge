package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.CartItemResponse;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.quantityToIntegerConverter;

@Mapper
public class CartItemToCartItemResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CartItem.class, CartItemResponse.class)
                .addMapping(CartItem::product, CartItemResponse::setProduct)
                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(CartItem::total, CartItemResponse::setTotal))
                .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(CartItem::subtotal, CartItemResponse::setSubtotal))
                .addMappings(mapping -> mapping.using(discountToBigDecimalConverter()).map(CartItem::discount, CartItemResponse::setDiscount))
                .addMappings(mapping -> mapping.using(quantityToIntegerConverter()).map(CartItem::quantity, CartItemResponse::setQuantity));
    }
}