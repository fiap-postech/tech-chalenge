package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
@ToString
public class Cart extends Entity {

    @Serial
    private static final long serialVersionUID = -7454461774303685197L;

    private final Set<CartItem> items;

    @Builder(toBuilder = true)
    public Cart(@Builder.ObtainVia(method = "uuid") UUID uuid, Set<CartItem> items) {
        super(uuid);
        this.items = defaultIfNull(items, new HashSet<>());
    }

    public void addItem(CartItem item) {
        this.items.add(item);
    }

    public void removeItem(CartItem item) {
        this.items.remove(item);
    }
}