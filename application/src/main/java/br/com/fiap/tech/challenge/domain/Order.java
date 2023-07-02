package br.com.fiap.tech.challenge.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends Entity {

    @Serial
    private static final long serialVersionUID = -9196907733871633595L;

    @NotNull
    private final OrderStatus status;

    @NotNull
    private final LocalDate date;

    @NotNull
    @NotEmpty
    @Valid
    private final List<OrderItem> items;

    @Builder(toBuilder = true)
    public Order(@Builder.ObtainVia(method = "uuid") UUID uuid,
                 @NotNull OrderStatus status,
                 @NotNull LocalDate date,
                 @NotNull List<OrderItem> items) {
        super(uuid);
        this.status = status;
        this.date = date;
        this.items = items;
    }

    public Order prepared() {
        return toBuilder()
                .status(OrderStatus.MADE)
                .build();
    }

    public Order delivered() {
        return toBuilder()
                .status(OrderStatus.DELIVERED)
                .build();
    }

    public Order done() {
        return toBuilder()
                .status(OrderStatus.DONE)
                .build();
    }

    public Order addItem(Product product, Quantity quantity) {
        var item = OrderItem.builder()
                .product(product)
                .quantity(quantity)
                .build();

        var itemList = items();
        itemList.add(item);

        return toBuilder().items(itemList).build();
    }
}
