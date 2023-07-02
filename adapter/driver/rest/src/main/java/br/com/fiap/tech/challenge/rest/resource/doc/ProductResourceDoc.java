package br.com.fiap.tech.challenge.rest.resource.doc;

import br.com.fiap.tech.challenge.domain.ProductCategory;
import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.resource.request.CreateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import br.com.fiap.tech.challenge.util.ResponseList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Pageable;

public interface ProductResourceDoc {

    @Operation(
        summary = "Endpoint that returns all products",
        description = "Get all products registered in the database according to request.",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK - Return with success all products according to request", useReturnTypeSchema = true)
    })
    ResponseList<ProductResponse> getAllAvailable(Pageable pageable,
                                                  @Parameter(description = "Filter by product category", required = true) ProductCategory category);
    @Operation(
            summary = "Endpoint that returns a product by UUID",
            description = "Get a product registered in the database by UUID.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Return with success a product according to request", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "The Product with given UUID was not found.", content = { @Content(schema = @Schema()) })
            }
    )
    ProductResponse getByUUID(@Parameter(description = "UUID for search a product", required = true) String uuid);
    @Operation(
            summary = "Endpoint to register a new product",
            description = "Register a new product in database.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Return with success if a product was registered", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "Returns informing which field of the product is incorrect and why", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse create(CreateProductRequest request);
    @Operation(
            summary = "Endpoint to update a product",
            description = "Updates product data in database.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Return with success if a product was updated", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") }),
                @ApiResponse(responseCode = "400", description = "Returns informing which field of the product is incorrect and why", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse update(UpdateProductRequest request);
    @Operation(
            summary = "Endpoint to enable a product",
            description = "Enables a product that has a disabled status in the database.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Return with success if a product was enabled", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse enable(@Parameter(description = "UUID for enable a product", required = true) String uuid);
    @Operation(
            summary = "Endpoint to disable a product",
            description = "Disables a product that has a enabled status in the database.",
            responses = {
                @ApiResponse(responseCode = "200", description = "OK - Return with success if a product was disabled", content = { @Content(schema = @Schema(implementation = ProductResponse.class), mediaType = "application/json") })
            }
    )
    ProductResponse disable(@Parameter(description = "UUID for disable a product", required = true) String uuid);
}
