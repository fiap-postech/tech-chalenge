package br.com.fiap.tech.challenge.domain.entity;

import br.com.fiap.tech.challenge.domain.enums.ProductCategory;
import br.com.fiap.tech.challenge.domain.valueobject.Discount;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

import static br.com.fiap.tech.challenge.error.ApplicationError.PRODUCT_SHOULD_BE_SAME_CATEGORY_FOR_UPDATE;

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

    public Product update(Product product) {
        if (product.category() != category()) {
            throw new ApplicationException(PRODUCT_SHOULD_BE_SAME_CATEGORY_FOR_UPDATE, category(), product.category());
        }

        return doUpdate(product);
    }

    public Discount discount() {
        return Discount.withoutDiscount();
    }

    public Price fullPrice() {
        return price();
    }

    public abstract ProductCategory category();
    public abstract Product enable();
    public abstract Product disable();
    public abstract Product doUpdate(Product product);
}
