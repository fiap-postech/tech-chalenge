package br.com.fiap.tech.challenge.mapper.common;

import br.com.fiap.tech.challenge.domain.Percentage;
import br.com.fiap.tech.challenge.util.Moneys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.javamoney.moneta.Money;
import org.modelmapper.Converter;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_PRECISION;
import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_ROUNDING_MODE;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    public static Converter<Money, BigDecimal> priceToMoneyConverter() {
        return ctx -> defaultIfNull(ctx.getSource(), Moneys.zero())
                .getNumberStripped()
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }

    public static Converter<Percentage, BigDecimal> percentageToBigDecimalConverter(){
        return ctx -> defaultIfNull(ctx.getSource(), Percentage.zero()).value();
    }
}
