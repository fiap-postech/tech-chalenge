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
    private final String description;
    private final Money price;
    private final String image;

    protected Product(UUID uuid, String name, String description, Money price, String image) {
        super(uuid);

        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public abstract ProductCategory category();
}
