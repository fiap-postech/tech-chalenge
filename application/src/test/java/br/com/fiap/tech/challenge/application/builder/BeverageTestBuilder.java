package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.Beverage;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.javamoney.moneta.Money;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_CODE;
import static javax.money.Monetary.getCurrency;

public class BeverageTestBuilder {

    public static final UUID BEVERAGE_UUID = UUID.randomUUID();

    public static class Builder {

        private static final Beverage.BeverageBuilder builder = Beverage.builder();

        public BeverageTestBuilder.Builder withUUID() {
            builder.uuid(BEVERAGE_UUID);
            return this;
        }

        public BeverageTestBuilder.Builder withName() {
            builder.name("Refrigerante refrescante");
            return this;
        }

        public BeverageTestBuilder.Builder withDescription() {
            builder.description("O refrigerante mais refrescante que as pessoas gostam");
            return this;
        }

        public BeverageTestBuilder.Builder withPrice() {
            builder.price(Price.of(Money.of(10, getCurrency(CURRENCY_CODE))));
            return this;
        }

        public BeverageTestBuilder.Builder withImage() {
            builder.image(Image.of("http://localhost:8080/image.jpg"));
            return this;
        }

        public BeverageTestBuilder.Builder enableTrue() {
            builder.enabled(Boolean.TRUE);
            return this;
        }

        public BeverageTestBuilder.Builder enableFalse() {
            builder.enabled(Boolean.FALSE);
            return this;
        }

        public Beverage build() {
            return builder.build();
        }

        public Beverage fullFields() {
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
