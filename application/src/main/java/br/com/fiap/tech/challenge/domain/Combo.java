package br.com.fiap.tech.challenge.domain;

import br.com.fiap.tech.challenge.domain.validation.ComboValid;
import br.com.fiap.tech.challenge.domain.validation.PercentageDiscount;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.util.UUID;

@Getter
@Accessors(fluent = true)
@ComboValid
public class Combo extends Product {
    @Serial
    private static final long serialVersionUID = 2866585082574769968L;

    private final Sandwich sandwich;
    private final Beverage beverage;
    private final SideDish sideDish;

    @PercentageDiscount(max = 30)
    private final Percentage discount;

    @Builder
    protected Combo(UUID uuid, String name, String description, Money price, String image, Sandwich sandwich, Beverage beverage, SideDish sideDish, Percentage discount) {
        super(uuid, name, description, price, image);

        this.sandwich = sandwich;
        this.beverage = beverage;
        this.sideDish = sideDish;
        this.discount = discount;

        validate();
    }

    @Override
    public ProductCategory category() {
        return ProductCategory.COMBO;
    }
}
