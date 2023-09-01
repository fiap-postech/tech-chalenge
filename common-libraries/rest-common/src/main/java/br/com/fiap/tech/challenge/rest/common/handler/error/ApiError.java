package br.com.fiap.tech.challenge.rest.common.handler.error;

import br.com.fiap.tech.challenge.exception.error.BaseError;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.util.List;

import static br.com.fiap.tech.challenge.util.Strings.formatMessage;

@Getter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação dos detalhes da representação do erro padrão")
public class ApiError extends Response {

	@Serial
	private static final long serialVersionUID = 3673305337110772582L;

	@Schema(description = "Codigo associado ao erro ocorrido para facilitar seu track pelo consumidor", example = "CE-003")
	private final String code;

	@Schema(description = "Mensagem especificando do que o erro se trata", example = "Invalid arguments")
    private final String message;

    private List<ApiErrorField> fields;

    private Object data;

	public ApiError(BaseError error, List<ApiErrorField> fields, Object... parameters) {
		this.code = error.getCode();
		this.fields = fields;
		this.message = error.getAcceptParameters() ? formatMessage(error.getDescription(), parameters) : error.getDescription();
	}

	public ApiError(Object data, BaseError error, Object... parameters) {
		this.code = error.getCode();
		this.data = data;
		this.message = error.getAcceptParameters() ? formatMessage(error.getDescription(), parameters) : error.getDescription();
	}

	public ApiError(BaseError error, Object... parameters) {
		this.code = error.getCode();
		this.message = error.getAcceptParameters() ? formatMessage(error.getDescription(), parameters) : error.getDescription();
	}
}