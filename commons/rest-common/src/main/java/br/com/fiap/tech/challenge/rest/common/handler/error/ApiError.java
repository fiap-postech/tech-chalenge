package br.com.fiap.tech.challenge.rest.common.handler.error;

import br.com.fiap.tech.challenge.exception.error.BaseError;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.util.List;

import static br.com.fiap.tech.challenge.util.Strings.formatMessage;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ApiError extends Response {

	@Serial
	private static final long serialVersionUID = 3673305337110772582L;

	private final String code;
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