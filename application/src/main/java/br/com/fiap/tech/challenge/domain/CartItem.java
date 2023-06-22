package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;

@Getter
@Accessors(fluent = true)
public class CartItem extends ValueObject {

    @Serial
    private static final long serialVersionUID = -8071866742749224693L;

    private final Product product;
    private final Price price;
    private final Discount discount;
    private final Quantity quantity;

    //TODO add method for total item

    @Builder(toBuilder = true)
    public CartItem(Product product, Price price, Discount discount, Quantity quantity) {
        this.product = product;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    public CartItem incrementQuantity() {
        return toBuilder()
                .quantity(quantity().increment(1))
                .build();
    }

    public CartItem decrementQuantity() {
        return toBuilder()
                .quantity(quantity().decrement(1))
                .build();
    }

    public CartItem applyDiscount(Money money) {
        var newDiscount = Discount.of(money);

        return toBuilder()
                .discount(newDiscount)
                .price(price().subtract(newDiscount.amount()))
                .build();
    }

    public CartItem applyDiscount(double percentage) {
        var newDiscount = Discount.of(
                Percentage.of(percentage).apply(price().amount())
        );

        return toBuilder()
                .discount(newDiscount)
                .price(price().subtract(newDiscount.amount()))
                .build();
    }

    public CartItem resetDiscount() {
        return toBuilder()
                .discount(discount().reset())
                .price(Price.of(product.price()))
                .build();
    }
}
