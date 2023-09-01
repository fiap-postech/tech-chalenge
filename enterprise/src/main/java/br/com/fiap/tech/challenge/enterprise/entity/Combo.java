package br.com.fiap.tech.challenge.enterprise.entity;

import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.enterprise.validation.ComboValid;
import br.com.fiap.tech.challenge.enterprise.valueobject.Discount;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;
import java.util.stream.Stream;

@Getter
@Accessors(fluent = true)
@ComboValid
public class Combo extends Product {
    @Serial
    private static final long serialVersionUID = 2866585082574769968L;

    private final Sandwich sandwich;
    private final Beverage beverage;
    private final SideDish sideDish;

    @Builder(toBuilder = true)
    @SuppressWarnings("squid:S107")
    protected Combo(
            @Builder.ObtainVia(method = "uuid") UUID uuid,
            @Builder.ObtainVia(method = "name") String name,
            @Builder.ObtainVia(method = "description") String description,
            @Builder.ObtainVia(method = "price") Price price,
            @Builder.ObtainVia(method = "image") Image image,
            Sandwich sandwich,
            Beverage beverage,
            SideDish sideDish,
            @Builder.ObtainVia(method = "enabled") boolean enabled
    ) {
        super(
                uuid,
                name,
                description,
                price,
                image,
                currentEnabled(enabled, sandwich.enabled(), beverage.enabled(), sideDish.enabled())
        );

        this.sandwich = sandwich;
        this.beverage = beverage;
        this.sideDish = sideDish;

        validate();
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.COMBO;
    }

    @Override
    public Product enable() {
        return toBuilder()
                .enabled(true)
                .build();
    }

    @Override
    public Product disable() {
        return toBuilder()
                .enabled(false)
                .build();
    }

    @Override
    public Price fullPrice() {
        return Stream.of(beverage, sideDish, sandwich)
                .map(Product::price)
                .reduce(Price.min(), Price::add);
    }

    @Override
    public Discount discount() {
        return Discount.of(fullPrice().subtract(price()).amount());
    }

    @Override
    public Product doUpdate(Product product) {
        return toBuilder()
                .name(product.name())
                .description(product.description())
                .image(product.image())
                .price(product.price())
                .enabled(product.enabled())
                .build();
    }

    private static boolean currentEnabled(boolean enabled, Boolean... dependentsEnabled){
        if (Stream.of(dependentsEnabled).anyMatch(b -> !b)){
            return false;
        }

        return enabled;
    }
}
