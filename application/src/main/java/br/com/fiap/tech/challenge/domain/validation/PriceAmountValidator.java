package br.com.fiap.tech.challenge.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.javamoney.moneta.Money;

import static java.util.Objects.isNull;

public class PriceAmountValidator implements ConstraintValidator<PriceAmount, Money> {
    @Override
    public boolean isValid(Money value, ConstraintValidatorContext context) {
        if (isNull(value)) {
            return false;
        }
        return value.isPositiveOrZero();
    }
}