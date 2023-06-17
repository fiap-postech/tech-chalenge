package br.com.fiap.tech.challenge.domain;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.javamoney.moneta.Money;

import java.io.Serial;
import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.domain.MoneyConstants.CURRENCY_CODE;
import static br.com.fiap.tech.challenge.domain.MoneyConstants.CURRENCY_MATH_CONTEXT;
import static javax.money.Monetary.getCurrency;

@Getter
@Accessors(fluent = true)
@ToString
public class Discount extends ValueObject {
    @Serial
    private static final long serialVersionUID = -4877668256672282598L;

    @PositiveOrZero
    private final Money amount;

    private Discount(Money amount) {
        this.amount = amount;

        validate();
    }

    public Discount reset(){
        return withoutDiscount();
    }

    public static Discount of(Money amount){
        return new Discount(amount);
    }

    public static Discount of(Money price, double percentage) {
        var multiplicand = BigDecimal.valueOf(percentage)
                .divide(BigDecimal.valueOf(100), CURRENCY_MATH_CONTEXT);

        var discountValue = price
                .getNumber()
                .numberValue(BigDecimal.class)
                .multiply(multiplicand);

        return of(Money.of(discountValue, CURRENCY_CODE));
    }

    public static Discount withoutDiscount() {
        return of(Money.zero(getCurrency(CURRENCY_CODE)));
    }
}
