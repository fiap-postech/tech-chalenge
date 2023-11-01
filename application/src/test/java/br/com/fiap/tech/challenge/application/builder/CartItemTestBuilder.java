package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;

public class CartItemTestBuilder {

    public static class Builder {
        private static final CartItem.CartItemBuilder builder = CartItem.builder();

        public CartItemTestBuilder.Builder withProduct() {
            builder.product(new SandwichTestBuilder.Builder().fullFields());
            return this;
        }

        public CartItemTestBuilder.Builder withQuantity() {
            builder.quantity(Quantity.of(1));
            return this;
        }

        public CartItemTestBuilder.Builder withTwoQuantity() {
            builder.quantity(Quantity.of(2));
            return this;
        }

        public CartItem buildWithTwoQuantity() {
            withProduct();
            withTwoQuantity();
            return builder.build();
        }

        public CartItem fullFields() {
            withProduct();
            withQuantity();
            return builder.build();
        }
    }
}
