package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.Dessert;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.javamoney.moneta.Money;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_CODE;
import static javax.money.Monetary.getCurrency;

public class DessertTestBuilder {

    public static class Builder {

        private static final Dessert.DessertBuilder builder = Dessert.builder();
        private static final UUID DESSERT_UUID = UUID.randomUUID();

        public DessertTestBuilder.Builder withUUID() {
            builder.uuid(DESSERT_UUID);
            return this;
        }

        public DessertTestBuilder.Builder withName() {
            builder.name("Tortinha de Maça");
            return this;
        }

        public DessertTestBuilder.Builder withDescription() {
            builder.description("Saborosa tortinha de maça");
            return this;
        }

        public DessertTestBuilder.Builder withPrice() {
            builder.price(Price.of(Money.of(8, getCurrency(CURRENCY_CODE))));
            return this;
        }

        public DessertTestBuilder.Builder withImage() {
            builder.image(Image.of("http://localhost:8080/image.jpg"));
            return this;
        }

        public DessertTestBuilder.Builder enableTrue() {
            builder.enabled(Boolean.TRUE);
            return this;
        }

        public DessertTestBuilder.Builder enableFalse() {
            builder.enabled(Boolean.FALSE);
            return this;
        }

        public Dessert build() {
            return builder.build();
        }

        public Dessert fullFields() {
            withUUID();
            withName();
            withDescription();
            withImage();
            withPrice();
            enableTrue();
            return builder.build();
        }
    }
}
