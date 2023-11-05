package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.javamoney.moneta.Money;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_CODE;
import static javax.money.Monetary.getCurrency;

public class SandwichTestBuilder {

    public static final UUID SANDWICH_UUID = UUID.randomUUID();

    public static class Builder {

        private static final Sandwich.SandwichBuilder builder = Sandwich.builder();

        public SandwichTestBuilder.Builder withUUID() {
            builder.uuid(SANDWICH_UUID);
            return this;
        }

        public SandwichTestBuilder.Builder withName() {
            builder.name("Hamburguer Delicioso");
            return this;
        }

        public SandwichTestBuilder.Builder withDescription() {
            builder.description("Saboroso hamburguer do chefe");
            return this;
        }

        public SandwichTestBuilder.Builder withPrice() {
            builder.price(Price.of(Money.of(18, getCurrency(CURRENCY_CODE))));
            return this;
        }

        public SandwichTestBuilder.Builder withImage() {
            builder.image(Image.of("http://localhost:8080/image.jpg"));
            return this;
        }

        public SandwichTestBuilder.Builder enableTrue() {
            builder.enabled(Boolean.TRUE);
            return this;
        }

        public SandwichTestBuilder.Builder enableFalse() {
            builder.enabled(Boolean.FALSE);
            return this;
        }

        public Sandwich build() {
            return builder.build();
        }

        public Sandwich fullFields() {
            withUUID();
            withName();
            withDescription();
            withImage();
            withPrice();
            enableTrue();
            return builder.build();
        }

        public Sandwich buildDisable() {
            withUUID();
            withName();
            withDescription();
            withImage();
            withPrice();
            enableFalse();
            return builder.build();
        }
    }
}
