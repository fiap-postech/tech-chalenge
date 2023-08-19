package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Cart;
import br.com.fiap.tech.challenge.rest.resource.response.CartItemResponse;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_PRECISION;
import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_ROUNDING_MODE;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class CartMapper {

    @Autowired
    protected CartItemMapper cartItemMapper;

    @Autowired
    protected CustomerMapper customerMapper;

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "customer", source = "source", qualifiedByName = "getCustomer")
    @Mapping(target = "items", source = "source", qualifiedByName = "getCartItemsResponse")
    @Mapping(target = "total", source = "source", qualifiedByName = "getTotal")
    @Mapping(target = "subtotal", source = "source", qualifiedByName = "getSubtotal")
    @Mapping(target = "discount", source = "source", qualifiedByName = "getDiscount")
    public abstract CartResponse toCartResponse(Cart source);

    @Named("getCustomer")
    CustomerResponse getCustomer(Cart source){
        return customerMapper.toCustomerResponse(source.customer());
    }

    @Named("getCartItemsResponse")
    List<CartItemResponse> getCartItemsResponse(Cart source){
        return source.items().stream()
                .map(cartItemMapper::toCartItemResponse)
                .toList();
    }

    @Named("getTotal")
    BigDecimal getTotal(Cart source){
        return source.items().stream()
                .map(i -> i.total().amount().getNumberStripped())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }

    @Named("getSubtotal")
    BigDecimal getSubtotal(Cart source){
        return source.items().stream()
                .map(i -> i.subtotal().amount().getNumberStripped())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }

    @Named("getDiscount")
    BigDecimal getDiscount(Cart source){
        return source.items().stream()
                .map(i -> i.discount().amount().getNumberStripped())
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }

}
