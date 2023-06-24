package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class SideDish extends Product {

    @Serial
    private static final long serialVersionUID = -4700400815387828057L;

    @Builder(toBuilder = true)
    protected SideDish(
            @Builder.ObtainVia(method = "uuid") UUID uuid,
            @Builder.ObtainVia(method = "name") String name,
            @Builder.ObtainVia(method = "description") String description,
            @Builder.ObtainVia(method = "price") Price price,
            @Builder.ObtainVia(method = "image") String image,
            @Builder.ObtainVia(method = "enabled") boolean enabled
    ) {
        super(uuid, name, description, price, image, enabled);
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.SIDE_DISH;
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
}
