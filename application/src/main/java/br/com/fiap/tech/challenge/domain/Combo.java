package br.com.fiap.tech.challenge.domain;

import br.com.fiap.tech.challenge.domain.validation.ComboValid;
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
            @Builder.ObtainVia(method = "image") String image,
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

    private static boolean currentEnabled(boolean enabled, Boolean... dependentsEnabled){
        if (Stream.of(dependentsEnabled).anyMatch(b -> !b)){
            return false;
        }

        return enabled;
    }
}
