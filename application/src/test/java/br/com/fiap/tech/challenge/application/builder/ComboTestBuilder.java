package br.com.fiap.tech.challenge.application.builder;

import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.javamoney.moneta.Money;

import java.util.UUID;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_CODE;
import static javax.money.Monetary.getCurrency;

public class ComboTestBuilder {

    public static class Builder {

        private static final Combo.ComboBuilder builder = Combo.builder();
        private static final UUID COMBO_UUID = UUID.randomUUID();

        public ComboTestBuilder.Builder withUUID() {
            builder.uuid(COMBO_UUID);
            return this;
        }

        public ComboTestBuilder.Builder withName() {
            builder.name("Combo Nº1");
            return this;
        }

        public ComboTestBuilder.Builder withDescription() {
            builder.description("Combo de hamburguer, batata frita e refrigerante");
            return this;
        }

        public ComboTestBuilder.Builder withPrice() {
            builder.price(Price.of(Money.of(30, getCurrency(CURRENCY_CODE))));
            return this;
        }

        public ComboTestBuilder.Builder withImage() {
            builder.image(Image.of("http://localhost:8080/image.jpg"));
            return this;
        }

        public ComboTestBuilder.Builder enableTrue() {
            builder.enabled(Boolean.TRUE);
            return this;
        }

        public ComboTestBuilder.Builder enableFalse() {
            builder.enabled(Boolean.FALSE);
            return this;
        }

        public Combo build() {
            return builder.build();
        }

        public Combo fullFields() {
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
