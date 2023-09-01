package br.com.fiap.tech.challenge.rest.mapping;


import br.com.fiap.tech.challenge.enterprise.valueobject.PurchaseItem;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.rest.resource.response.PurchaseItemResponse;
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
public interface PurchaseItemMapperRest {


    @Mapping(target = "price", source = "source", qualifiedByName = "getPrice")
    @Mapping(target = "fullPrice", source = "source", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscount")
    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantity")
    @Mapping(target = "product", source = "source", qualifiedByName = "getProductResponse")
    PurchaseItemResponse toPurchaseItemResponse(PurchaseItem source);

    @Named("getPrice")
    static BigDecimal getPrice(PurchaseItem source) {
        return priceToBigDecimalConverter(source.price());
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(PurchaseItem source) {
        return priceToBigDecimalConverter(source.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(PurchaseItem source) {
        return discountToBigDecimalConverter(source.discount());
    }

    @Named("getQuantity")
    static Integer getQuantity(PurchaseItem source) {
        return quantityToIntegerConverter(source.quantity());
    }

    @Named("getProductResponse")
    static ProductResponse getProductResponse(PurchaseItem source) {
        return toProductResponse(source.product());
    }

}
