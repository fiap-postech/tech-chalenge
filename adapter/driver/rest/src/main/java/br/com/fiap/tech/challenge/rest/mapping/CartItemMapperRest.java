package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.CartItem;
import br.com.fiap.tech.challenge.rest.resource.response.CartItemResponse;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.rest.util.Mappings.toProductResponse;
import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.quantityToIntegerConverter;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CartItemMapperRest {


    @Mapping(target = "product", source = "source", qualifiedByName = "getProductResponse")
    @Mapping(target = "total", source = "source", qualifiedByName = "getTotal")
    @Mapping(target = "subtotal", source = "source", qualifiedByName = "getSubtotal")
    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscount")
    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantity")
    CartItemResponse toCartItemResponse(CartItem source);


    @Named("getProductResponse")
    static ProductResponse getProductResponse(CartItem source) {
        return toProductResponse(source.product());
    }

    @Named("getTotal")
    static BigDecimal getTotal(CartItem source) {
        return priceToBigDecimalConverter(source.total());
    }

    @Named("getSubtotal")
    static BigDecimal getSubtotal(CartItem source) {
        return priceToBigDecimalConverter(source.subtotal());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(CartItem source) {
        return discountToBigDecimalConverter(source.discount());
    }

    @Named("getQuantity")
    static Integer getQuantity(CartItem source) {
        return quantityToIntegerConverter(source.quantity());
    }

}
