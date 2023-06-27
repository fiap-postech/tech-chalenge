package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class Sandwich extends Product {

    @Serial
    private static final long serialVersionUID = 270547921615282785L;

    @Builder
    public Sandwich(UUID uuid, String name, String description, Price price, Image image) {
        super(uuid, name, description, price, image);
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.SANDWICH;
    }

    @Override
    public Price fullPrice() {
        return price();
    }

    @Override
    public Discount discount() {
        return Discount.withoutDiscount();
    }
}
