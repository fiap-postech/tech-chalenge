package br.com.fiap.tech.challenge.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(callSuper = true)
public abstract class Product extends Entity {
    @Serial
    private static final long serialVersionUID = -556035981231420003L;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    @Valid
    private final Price price;

    private final boolean enabled;

    @NotNull
    @Valid
    private final Image image;

    protected Product(UUID uuid, String name, String description, @NotNull Price price, @NotNull Image image, boolean enabled) {
        super(uuid);

        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.enabled = enabled;
    }

    public abstract ProductCategory category();
    public abstract Product enable();
    public abstract Product disable();
    public abstract Product update(Product product);
}
