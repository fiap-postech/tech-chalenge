package br.com.fiap.tech.challenge.domain;

import br.com.fiap.tech.challenge.domain.validation.PriceAmount;
import br.com.fiap.tech.challenge.domain.validation.PriceCurrency;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.domain.MoneyConstants.CURRENCY_CODE;
import static javax.money.Monetary.getCurrency;

@Getter
@Accessors(fluent = true)
@ToString
public class Price extends ValueObject {

    @Serial
    private static final long serialVersionUID = -1420416290598941259L;

    @PriceAmount
    @PriceCurrency
    private final Money amount;

    private Price(Money amount) {
        this.amount = amount;

        validate();
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
        return Price.of(Money.zero(getCurrency(CURRENCY_CODE)));
    }
}
