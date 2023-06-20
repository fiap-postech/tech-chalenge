package br.com.fiap.tech.challenge.rest.resource.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ApiErrorResponse extends Response {

	@Serial
    private static final long serialVersionUID = 7622195162214987627L;

	private final Integer statusCode;
    private final String statusError;
    private final String path;
    private final String method;
    private final ApiError error;

    public ApiErrorResponse(Integer statusCode, String statusError, String path, String method, ApiError error) {
        this.statusCode = statusCode;
        this.statusError = statusError;
        this.path = path;
        this.method = method;
        this.error = error;
    }
}
