package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class Cart extends Entity {

    @Serial
    private static final long serialVersionUID = -7454461774303685197L;

    private final List<CartItem> items;

    @Builder(toBuilder = true)
    public Cart(@Builder.ObtainVia(method = "uuid") UUID uuid, List<CartItem> items) {
        super(uuid);
        this.items = defaultIfNull(items, new ArrayList<>());
    }

    public Cart addItem(CartItem item) {
        var newItems = new ArrayList<>(items);

        items.stream()
                .filter(i -> i.product().uuid().equals(item.product().uuid()))
                .findFirst()
                .ifPresentOrElse(i -> {
                    var newItem = i.toBuilder()
                            .quantity(Quantity.of(i.quantity().value() + item.quantity().value()))
                            .product(item.product())
                            .build();

                    newItems.set(items.indexOf(i), newItem);
                }, () -> newItems.add(item));

        return toBuilder()
                .items(newItems)
                .build();
    }

    public Cart updateItem(CartItem item) {
        var newItems = new ArrayList<>(items);

        items.stream()
                .filter(i -> i.product().uuid().equals(item.product().uuid()))
                .findFirst()
                .ifPresent(i -> {
                    var newItem = i.toBuilder()
                            .quantity(item.quantity())
                            .product(item.product())
                            .build();

                    newItems.set(items.indexOf(i), newItem);
                });

        return toBuilder()
                .items(newItems)
                .build();
    }

    public Cart removeItem(CartItem item) {
        var newItems = new ArrayList<>(items);
        newItems.removeIf(i -> i.product().uuid().equals(item.product().uuid()));

        return toBuilder()
                .items(newItems)
                .build();
    }
}