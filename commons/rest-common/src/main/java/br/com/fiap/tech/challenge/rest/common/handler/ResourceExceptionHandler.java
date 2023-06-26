package br.com.fiap.tech.challenge.rest.common.handler;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.exception.error.CommonApplicationError;
import br.com.fiap.tech.challenge.rest.common.handler.error.ApiError;
import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorField;
import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.common.response.Response;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.MappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static br.com.fiap.tech.challenge.rest.common.handler.HttpStatusHandler.handle;

@ControllerAdvice
@SuppressWarnings("squid:S1452")
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var applicationError = CommonApplicationError.INVALID_PARAMETERS;
        var httpStatus = handle(applicationError.getErrorType());
        var fields = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(violation -> new ApiErrorField(violation.getField(), violation.getObjectName(), violation.getDefaultMessage()))
                .toList();

        var error = new ApiError(applicationError, fields);
        var apiError = createApiErrorResponse(request, httpStatus, error);

        logger.error(getLogMessage(request, exception, error.getCode()), exception);
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var applicationError = CommonApplicationError.INVALID_PARAMETERS;
        var httpStatus = handle(applicationError.getErrorType());
        var apiError = createApiErrorResponse(request, httpStatus, new ApiError(applicationError, exception.getMostSpecificCause().getMessage()));

        logger.error(getLogMessage(request, exception, apiError.getError().getCode()), exception);
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<? extends Response> handleApplicationException(ApplicationException exception, WebRequest request) {
        var httpStatus = handle(exception.getError().getErrorType());
        var apiError = new ApiError(exception.getData(), exception.getError(), exception.getParameters());
        var apiErrorResponse = this.createApiErrorResponse(request, httpStatus, apiError);

        logger.error(getLogMessage(request, exception, apiError.getCode()), exception);
        return new ResponseEntity<>(apiErrorResponse, httpStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<? extends Response> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {
        var applicationError = CommonApplicationError.CONSTRAINT_VIOLATION;
        var httpStatus = handle(applicationError.getErrorType());
        var fields = exception.getConstraintViolations()
                .stream()
                .map(violation -> new ApiErrorField(violation.getPropertyPath().toString(), violation.getRootBeanClass().getName(), violation.getMessage()))
                .toList();
        var error = new ApiError(applicationError, fields);
        var apiError = createApiErrorResponse(request, httpStatus, error);

        logger.error(getLogMessage(request, exception, error.getCode()), exception);
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<? extends Response> handleGeneralException(RuntimeException exception, WebRequest request) {
        var applicationError = CommonApplicationError.GENERIC_ERROR;
        var httpStatus = handle(applicationError.getErrorType());
        var apiError = createApiErrorResponse(request, httpStatus, new ApiError(applicationError, exception.getMessage()));

        logger.error(getLogMessage(request, exception, apiError.getError().getCode()), exception);
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<? extends Response> handleMappingException(MappingException exception, WebRequest request) {
        if (exception.getCause() instanceof ConstraintViolationException cause) {
            return this.handleConstraintViolation(cause, request);
        }
        return this.handleGeneralException(exception, request);
    }

    private ApiErrorResponse createApiErrorResponse(WebRequest webRequest, HttpStatus httpStatus, ApiError apiError) {
        var request = ((ServletWebRequest) webRequest).getRequest();
        return new ApiErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), request.getRequestURI(), request.getMethod(), apiError);
    }

    private String getLogMessage(WebRequest request, Exception exception, String code){
        var servletRequest = ((ServletWebRequest) request).getRequest();

        return String.format(
                "httpMethod=%s path=%s stage=error message='%s' errorCode=%s",
                servletRequest.getMethod(),
                servletRequest.getRequestURI(),
                exception.getMessage(),
                code
        );
    }
}