package br.com.fiap.tech.challenge.rest.common.handler.error;

import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;

@Getter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um campo que apresentou erro")
public class ApiErrorField extends Response {

	@Serial
    private static final long serialVersionUID = 5576639104940629030L;

    @Schema(description = "Nome do campo que apresentou erro", example = "price")
	private final String field;

    @Schema(description = "Informa o tipo do erro, em qual modelo de request", example = "ProductRequest")
    private final String type;

    @Schema(description = "Mensagem relaciona ao problema do campo", example = "Price should be greater than or 0. Found: -1")
    private final String message;

    public ApiErrorField(String field, String type, String message) {
        this.field = field;
        this.type = type;
        this.message = message;
    }
}