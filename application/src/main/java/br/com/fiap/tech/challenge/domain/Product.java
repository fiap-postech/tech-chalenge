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

    @NotBlank
    //TODO create a VO for image
    private final String image;

    protected Product(UUID uuid, String name, String description, Price price, String image) {
        super(uuid);

        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;

        validate();
    }

    public abstract ProductCategory category();
}
