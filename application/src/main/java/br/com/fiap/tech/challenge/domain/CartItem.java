package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class CartItem extends ValueObject {

    @Serial
    private static final long serialVersionUID = -8071866742749224693L;

//    private final UUID uuid;
    private final Product product;
//    private final Price price;
//    private final Discount discount;
    private final Quantity quantity;

    @Builder(toBuilder = true)
    public CartItem(Product product, Quantity quantity) {
//        this.uuid = defaultIfNull(uuid, UUID.randomUUID());
        this.product = product;
//        this.price = price;
//        this.discount = discount;
        this.quantity = quantity;
    }

    public Price total() {
        return Price.min();
    }

    public Price subtotal() {
        return Price.min();
    }

    public Discount discount() {
        return Discount.withoutDiscount();
    }

//    public CartItem incrementQuantity() {
//        return toBuilder()
//                .quantity(quantity().increment(1))
//                .build();
//    }
//
//    public CartItem decrementQuantity() {
//        return toBuilder()
//                .quantity(quantity().decrement(1))
//                .build();
//    }
//
//    public CartItem applyDiscount(Money money) {
//        var newDiscount = Discount.of(money);
//
//        return toBuilder()
//                .discount(newDiscount)
//                .price(price().subtract(newDiscount.amount()))
//                .build();
//    }
//
//    public CartItem applyDiscount(double percentage) {
//        var newDiscount = Discount.of(price().amount(), percentage);
//
//        return toBuilder()
//                .discount(newDiscount)
//                .price(price().subtract(newDiscount.amount()))
//                .build();
//    }
//
//    public CartItem resetDiscount(){
//        return toBuilder()
//                .discount(discount().reset())
//                .price(product.price())
//                .build();
//    }
}
