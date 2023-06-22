package br.com.fiap.tech.challenge.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, CONSTRUCTOR, ANNOTATION_TYPE, TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ComboValidator.class)
@Documented
public @interface ComboValid {
    String message() default "Combo should have a sandwich, beverage and side dish";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
