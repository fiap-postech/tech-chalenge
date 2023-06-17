package br.com.fiap.tech.challenge.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, CONSTRUCTOR, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = PriceAmountValidator.class)
@Documented
public @interface PriceAmount {
    String message() default "Price should be greater than or 0. Found: ${validatedValue.amount}";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
