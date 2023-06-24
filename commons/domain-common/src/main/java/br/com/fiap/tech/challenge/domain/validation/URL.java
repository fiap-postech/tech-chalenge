package br.com.fiap.tech.challenge.domain.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, CONSTRUCTOR, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = URLValidator.class)
@Documented
public @interface URL {
    String message() default "URL should respect format specified in RFC 2396";

    Class<?>[] groups () default {};

    Class<? extends Payload>[] payload () default {};
}
