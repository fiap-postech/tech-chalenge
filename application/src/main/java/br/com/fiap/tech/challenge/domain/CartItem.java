package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Getter
@Accessors(fluent = true)
public class CartItem extends ValueObject {

    @Serial
    private static final long serialVersionUID = -8071866742749224693L;

    private final Product product;
    private final Price price;
    private final Price discount;
    private final Quantity quantity;

    //TODO add method for total item

    @Builder(toBuilder = true)
    public CartItem(Product product, Price price, Price discount, Quantity quantity) {
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
        var discountToApply = Price.of(money);

        return toBuilder()
                .discount(discount().add(discountToApply))
                .price(price().subtract(discountToApply))
                .build();
    }

    public CartItem applyDiscount(double percentage) {
        //TODO make a VO for Discount
        var multiplicand = BigDecimal.valueOf(percentage)
                .divide(BigDecimal.valueOf(100), new MathContext(2, RoundingMode.HALF_UP));

        var discountValue = price()
                .amount()
                .getNumber()
                .numberValue(BigDecimal.class)
                .multiply(multiplicand);

        var discountToApply = Price.of(Money.of(discountValue, "BRL"));

        return toBuilder()
                .discount(discount().add(discountToApply))
                .price(price().subtract(discountToApply))
                .build();
    }

    public CartItem resetDiscount(){
        //TODO move this to Discount VO
        return toBuilder()
                .discount(Price.min())
                .price(Price.of(product.price()))
                .build();
    }
}
