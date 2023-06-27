package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_PRECISION;
import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_ROUNDING_MODE;

@Mapper
public class CartToCartResponseMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Cart.class, CartResponse.class)
                .addMapping(Cart::uuid, CartResponse::setId)
                .addMapping(Cart::items, CartResponse::setItems)
                .setProvider(cartResponseProvider());
    }

    private Provider<CartResponse> cartResponseProvider() {
        return provision -> {
            var cart = (Cart) provision.getSource();

            return CartResponse.builder()
                    .total(cart.items().stream()
                            .map(i -> i.total().amount().getNumberStripped())
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE))
                    .subtotal(cart.items().stream()
                            .map(i -> i.subtotal().amount().getNumberStripped())
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE))
                    .discount(cart.items().stream()
                            .map(i -> i.discount().amount().getNumberStripped())
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE))
                    .build();
        };
    }
}
