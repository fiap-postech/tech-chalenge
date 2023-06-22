package br.com.fiap.tech.challenge.domain.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class UUIDValidator implements ConstraintValidator<UUID, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            //noinspection ResultOfMethodCallIgnored
            java.util.UUID.fromString(value);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
