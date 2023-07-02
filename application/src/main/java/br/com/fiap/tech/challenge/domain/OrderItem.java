package br.com.fiap.tech.challenge.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends ValueObject implements Serializable {
    @Serial
    private static final long serialVersionUID = -2071891141308689952L;

    @NotNull
    @Valid
    private final Product product;

    @NotNull
    @Valid
    private final Quantity quantity;

    @Builder(toBuilder = true)
    public OrderItem(@NotNull Product product, @NotNull Quantity quantity) {
        this.product = product;
        this.quantity = quantity;

        validate();
    }

    public Price unitPrice() {
        return product().fullPrice();
    }

    public Discount unitDiscount() {
        return product().discount();
    }

    public Price subTotal() {
        return product().fullPrice().multiply(quantity());
    }

    public Discount discount() {
        return product().discount().multiply(quantity());
    }

    public Price total() {
        return product().price().multiply(quantity());
    }
}
