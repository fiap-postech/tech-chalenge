package br.com.fiap.tech.challenge.rest.common.handler.error;

import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de retorno de erro padrão")
public class ApiErrorResponse extends Response {

	@Serial
    private static final long serialVersionUID = 7622195162214987627L;

    @Schema(description = "StatusCode do erro ocorrido", example = "400")
	private final Integer statusCode;

    @Schema(description = "Descritivo do StatusCode do erro ocorrido", example = "Bad Request")
    private final String statusError;

    @Schema(description = "Path da chamada aonde o erro ocorreu", example = "/example")
    private final String path;

    @Schema(description = "Qual verbo da chamada REST se refere a request", example = "POST")
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
