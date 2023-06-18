package br.com.fiap.tech.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
public class Sandwich extends Product {

    @Serial
    private static final long serialVersionUID = 270547921615282785L;

    @Builder
    public Sandwich(UUID uuid, String name, Money price, Money cost) {
        super(uuid, name, price, cost);
    }

    @Override
    public ProductCategory type() {
        return ProductCategory.SANDWICH;
    }
}
