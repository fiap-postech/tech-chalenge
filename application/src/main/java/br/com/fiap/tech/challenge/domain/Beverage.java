package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class Beverage extends Product {

    @Serial
    private static final long serialVersionUID = 8271359996195513007L;

    @Builder
    protected Beverage(UUID uuid, String name, String description, Money price, Image image) {
        super(uuid, name, description, price, image);
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.BEVERAGE;
    }
}
