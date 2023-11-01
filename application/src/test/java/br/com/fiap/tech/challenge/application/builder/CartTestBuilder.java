package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.Cart;

import java.util.List;
import java.util.UUID;

public class CartTestBuilder {

    public static final UUID CART_UUID = UUID.randomUUID();

    public static class Builder {
        private static final Cart.CartBuilder builder = Cart.builder();

        public Builder withUUID() {
            builder.uuid(CART_UUID);
            return this;
        }

        public Builder withCustomer() {
            builder.customer(new CustomerTestBuilder.Builder().fullFields());
            return this;
        }

        public Builder withCartItem() {
            builder.items(List.of(new CartItemTestBuilder.Builder().fullFields()));
            return this;
        }

        public Builder withCartItemTwoQuantity() {
            builder.items(List.of(new CartItemTestBuilder.Builder().buildWithTwoQuantity()));
            return this;
        }

        public Cart build() {
            return builder.build();
        }

        public Cart buildWithoutCartItem() {
            withUUID();
            withCustomer();
            return builder.build();
        }

        public Cart buildWithCartItem() {
            withUUID();
            withCustomer();
            withCartItem();
            return builder.build();
        }

        public Cart buildWithCartItemTwoQuantity() {
            withUUID();
            withCustomer();
            withCartItemTwoQuantity();
            return builder.build();
        }
    }
}
