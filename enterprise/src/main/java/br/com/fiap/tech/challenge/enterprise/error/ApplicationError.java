package br.com.fiap.tech.challenge.enterprise.error;

import br.com.fiap.tech.challenge.exception.error.BaseApplicationError;
import br.com.fiap.tech.challenge.exception.error.ErrorType;

import static br.com.fiap.tech.challenge.exception.error.ErrorType.CONFLICT;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.INTERNAL_SERVER_ERROR;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.INVALID_PARAMETER;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.NOT_FOUND;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.UNPROCESSABLE_ENTITY;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public enum ApplicationError implements BaseApplicationError {

    UNKNOWN_ERROR("AE-001", INTERNAL_SERVER_ERROR, TRUE, "Unexpected error [{}]"),
    PRODUCT_NOT_FOUND_BY_UUID("AE-002", INVALID_PARAMETER, TRUE, "Product not found [uuid={}]"),
    IMAGE_URL_INVALID("AE-003", INVALID_PARAMETER, FALSE, "Image has invalid url"),
    CUSTOMER_HAS_REGISTRATION("AE-004", CONFLICT, FALSE, "Customer already has registration"),
    CUSTOMER_NOT_FOUND_BY_UUID("AE-005", INVALID_PARAMETER, TRUE, "Customer not found [uuid={}]"),
    PRODUCT_SHOULD_BE_SAME_CATEGORY_FOR_UPDATE("AE-006", INVALID_PARAMETER, TRUE, "Product should be updated by one that have same category [stored: {}, received: {}]"),
    CART_NOT_FOUND_BY_UUID("AE-007", INVALID_PARAMETER, TRUE, "Cart not found [uuid={}]"),
    CART_ITEM_NOT_AVAILABLE("AE-008", INVALID_PARAMETER, TRUE, "Cart item not available [cartUuid={} cartItemUuid={}]"),
    CART_CUSTOMER_NOT_AVAILABLE("AE-009", INVALID_PARAMETER, TRUE, "Cart customer not available [customerUuid={}]"),
    PAYMENT_ERROR("AE-010", UNPROCESSABLE_ENTITY, FALSE, "There was an error in payment attempt, please try again soon"),
    PAYMENT_NOT_FOUND("AE-011", NOT_FOUND, TRUE, "There are no payment registered for purchase [purchaseUUID={}]"),
    PURCHASE_NOT_FOUND_BY_UUID("AE-012", INVALID_PARAMETER, TRUE, "Purchase not found [uuid={}]"),
    INVALID_CATEGORY_FOR_PRODUCT("AE-013", INVALID_PARAMETER, FALSE, "Received category is not valid for this product"),
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