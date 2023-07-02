package br.com.fiap.tech.challenge.rest.resource.doc;

import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerResourceDoc {

    @Operation(
            summary = "Endpoint that returns a customer by Document",
            description = "Get a customer registered in the database by Document.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Return with success a customer according to request", useReturnTypeSchema = true),
                @ApiResponse(responseCode = "204", description = "The customer with given Document was not found.", content = { @Content(schema = @Schema()) }),
                @ApiResponse(responseCode = "400", description = "Returns informing that the customer document is incorrect", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    ResponseEntity<CustomerResponse> getByDocument(@Parameter(description = "Document for search a customer", required = true) String document);
    @Operation(
            summary = "Endpoint to register a new customer",
            description = "Register a new customer in database.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Return with success if a customer was registered", content = { @Content(schema = @Schema(implementation = CustomerResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "Returns informing which field of the customer is incorrect and why", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CustomerResponse create(CreateCustomerRequest request);

    @Operation(
            summary = "Endpoint to disable a customer",
            description = "Disables a customer that has a enabled status in the database.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Return with success if a customer was disabled", useReturnTypeSchema = true),
                @ApiResponse(responseCode = "204", description = "The customer with given Document was not found for disable", content = { @Content(schema = @Schema()) }),
                @ApiResponse(responseCode = "400", description = "Returns informing that the customer document is incorrect", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    ResponseEntity<CustomerResponse> disable(@Parameter(description = "Document for disable a customer", required = true) String document);
}
