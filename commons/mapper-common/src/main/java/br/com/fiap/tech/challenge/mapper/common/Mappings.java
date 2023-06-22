package br.com.fiap.tech.challenge.mapper.common;

import br.com.fiap.tech.challenge.domain.Price;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_PRECISION;
import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_ROUNDING_MODE;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    public static Converter<Price, BigDecimal> priceToMoneyConverter() {
        return ctx -> defaultIfNull(ctx.getSource(), Price.min())
                .amount()
                .getNumberStripped()
                .setScale(CURRENCY_PRECISION, CURRENCY_ROUNDING_MODE);
    }
}
