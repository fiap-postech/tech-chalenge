package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.UUID;

public class CartTestBuilder {

    private static UUID UUID_CART = UUID.randomUUID();

    public static Cart buildWithoutCartItem() {
        return Cart.builder()
                .customer(new CustomerTestBuilder.Builder().fullFields())
                .uuid(UUID_CART)
                .build();
    }
}
