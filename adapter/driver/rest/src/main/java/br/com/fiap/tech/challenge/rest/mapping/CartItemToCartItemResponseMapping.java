package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.CartItem;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.CartItemResponse;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.quantityToIntegerConverter;
import static br.com.fiap.tech.challenge.rest.util.Mappings.toProductResponse;

@Mapper
public class CartItemToCartItemResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CartItem.class, CartItemResponse.class)
                .addMappings(mapping -> mapping.using(productConverter(mapper)).map(CartItem::product, CartItemResponse::setProduct))
                //         .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(CartItem::total, CartItemResponse::setTotal))
                //           .addMappings(mapping -> mapping.using(priceToBigDecimalConverter()).map(CartItem::subtotal, CartItemResponse::setSubtotal))
                .addMappings(mapping -> mapping.using(discountToBigDecimalConverter()).map(CartItem::discount, CartItemResponse::setDiscount))
                .addMappings(mapping -> mapping.using(quantityToIntegerConverter()).map(CartItem::quantity, CartItemResponse::setQuantity));
    }

    private Converter<Product, ProductResponse> productConverter(ModelMapper mapper) {
        return ctx -> toProductResponse(mapper, ctx.getSource());
    }
}