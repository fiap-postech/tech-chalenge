package br.com.fiap.tech.challenge.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public abstract class Product extends Entity {
    @Serial
    private static final long serialVersionUID = -556035981231420003L;

    private final String name;
    private final Money price;
    private final Money cost;

    protected Product(UUID uuid, String name, Money price, Money cost) {
        super(uuid);
        this.name = name;
        this.price = price;
        this.cost = cost;
    }

    public abstract ProductType type();

    public Money profit() {
        return price.subtract(cost);
    }
}
