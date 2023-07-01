package br.com.fiap.tech.challenge.rest.common.handler;

import br.com.fiap.tech.challenge.exception.error.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

public class HttpStatusHandler {

    public static HttpStatus handle(ErrorType errorType) {
        return map().getOrDefault(errorType, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static Map<ErrorType, HttpStatus> map() {
        return ofEntries(
                entry(ErrorType.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
                entry(ErrorType.INVALID_PARAMETER, HttpStatus.BAD_REQUEST),
                entry(ErrorType.NOT_FOUND, HttpStatus.NOT_FOUND),
                entry(ErrorType.CONFLICT, HttpStatus.CONFLICT),
                entry(ErrorType.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY)
        );
    }
}