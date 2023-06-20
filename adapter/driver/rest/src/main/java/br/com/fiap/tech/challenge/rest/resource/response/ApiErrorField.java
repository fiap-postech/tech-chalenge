package br.com.fiap.tech.challenge.rest.resource.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ApiErrorField extends Response {

	@Serial
    private static final long serialVersionUID = 5576639104940629030L;

	private final String field;
    private final String type;
    private final String message;

    public ApiErrorField(String field, String type, String message) {
        this.field = field;
        this.type = type;
        this.message = message;
    }
}