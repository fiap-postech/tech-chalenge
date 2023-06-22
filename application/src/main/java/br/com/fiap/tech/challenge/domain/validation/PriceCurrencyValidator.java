package br.com.fiap.tech.challenge.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.javamoney.moneta.Money;

import static br.com.fiap.tech.challenge.util.MoneyConstants.CURRENCY_CODE;
import static java.util.Objects.isNull;

public class PriceCurrencyValidator implements ConstraintValidator<PriceCurrency, Money> {
    @Override
    public boolean isValid(Money value, ConstraintValidatorContext context) {
        if (isNull(value)) {
            return false;
        }
        return CURRENCY_CODE.equals(value.getCurrency().getCurrencyCode());
    }
}