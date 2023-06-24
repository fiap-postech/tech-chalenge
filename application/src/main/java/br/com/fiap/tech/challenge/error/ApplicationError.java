package br.com.fiap.tech.challenge.error;

import br.com.fiap.tech.challenge.exception.error.BaseApplicationError;
import br.com.fiap.tech.challenge.exception.error.ErrorType;

import static br.com.fiap.tech.challenge.exception.error.ErrorType.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public enum ApplicationError implements BaseApplicationError {

    UNKNOWN_ERROR("AE-001", INTERNAL_SERVER_ERROR, TRUE, "Unexpected error [{}]"),
    PRODUCT_NOT_FOUND_BY_UUID("AE-002", INVALID_PARAMETER, TRUE, "Product not found [uuid={}]"),
    IMAGE_URL_INVALID("AE-003", INVALID_PARAMETER, FALSE, "Image has invalid url"),
    CUSTOMER_HAS_REGISTRATION("AE-004", UNPROCESSABLE_ENTITY, FALSE, "Customer already has registration")
    ;

    private final String code;

    private final ErrorType errorType;

    private final boolean acceptParameters;

    private final String description;

    ApplicationError(String code, ErrorType errorType, boolean acceptParameters, String description) {
        this.code = code;
        this.errorType = errorType;
        this.acceptParameters = acceptParameters;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public boolean getAcceptParameters() {
        return acceptParameters;
    }

    @Override
    public String getDescription() {
        return description;
    }
}