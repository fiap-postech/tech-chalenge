package br.com.fiap.tech.challenge.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static javax.money.Monetary.getCurrency;

@Getter
@Accessors(fluent = true)
@ToString
public class Price extends ValueObject {

    @Serial
    private static final long serialVersionUID = -1420416290598941259L;

    private final Money amount;

    private Price(Money amount) {
        this.amount = amount;

        assertArgumentEquals(
                amount.getCurrency().getCurrencyCode(),
                "BRL",
                "monetary currency unit should be BRL in this system"
        );

        assertArgumentAtLeast(
                amount.getNumberStripped(),
                new BigDecimal("0.00"),
                "price cannot be a negative value"
        );
    }

    public Price add(Money money) {
        return of(amount().add(money));
    }

    public Price add(Price price) {
        return add(price.amount());
    }

    public Price subtract(Money money) {
        return of(amount().subtract(money));
    }

    public Price subtract(Price price) {
        return subtract(price.amount());
    }

    public static Price of(Money money) {
        return new Price(money);
    }

    public static Price min(){
        return Price.of(Money.zero(getCurrency("BRL")));
    }
}
