package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.SideDish;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.javamoney.moneta.Money;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_CODE;
import static javax.money.Monetary.getCurrency;

public class SideDishTestBuilder {

    public static class Builder {

        private static final SideDish.SideDishBuilder builder = SideDish.builder();
        private static final UUID SIDEDISH_UUID = UUID.randomUUID();

        public SideDishTestBuilder.Builder withUUID() {
            builder.uuid(SIDEDISH_UUID);
            return this;
        }

        public SideDishTestBuilder.Builder withName() {
            builder.name("Batata Frita Crocrante");
            return this;
        }

        public SideDishTestBuilder.Builder withDescription() {
            builder.description("Batata Frita Crocrante e gostosa");
            return this;
        }

        public SideDishTestBuilder.Builder withPrice() {
            builder.price(Price.of(Money.of(7, getCurrency(CURRENCY_CODE))));
            return this;
        }

        public SideDishTestBuilder.Builder withImage() {
            builder.image(Image.of("http://localhost:8080/image.jpg"));
            return this;
        }

        public SideDishTestBuilder.Builder enableTrue() {
            builder.enabled(Boolean.TRUE);
            return this;
        }

        public SideDishTestBuilder.Builder enableFalse() {
            builder.enabled(Boolean.FALSE);
            return this;
        }

        public SideDish build() {
            return builder.build();
        }

        public SideDish fullFields() {
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
