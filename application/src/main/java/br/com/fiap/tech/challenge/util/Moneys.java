package br.com.fiap.tech.challenge.util;

import org.javamoney.moneta.Money;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.domain.MoneyConstants.CURRENCY_CODE;
import static javax.money.Monetary.getCurrency;

public class Moneys {

    private Moneys() {
    }

    public static Money makeMoney(double value) {
        return Money.of(value, CURRENCY_CODE);
    }

    public static Money makeMoney(BigDecimal value){
        return Money.of(value, CURRENCY_CODE);
    }

    public static Money zero(){
        return Money.zero(getCurrency(CURRENCY_CODE));
    }

}
