package br.com.fiap.tech.challenge.util;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.domain.MoneyConstants.CURRENCY_CODE;

public class MoneyUtil {

    private MoneyUtil() {
    }

    public static Money makeMoney(double value) {
        return Money.of(value, CURRENCY_CODE);
    }

    public static Money makeMoney(BigDecimal value){
        return Money.of(value, CURRENCY_CODE);
    }
}
