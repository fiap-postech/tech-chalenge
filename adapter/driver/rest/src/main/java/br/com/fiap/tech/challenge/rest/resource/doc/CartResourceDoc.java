package br.com.fiap.tech.challenge.rest.resource.doc;

import br.com.fiap.tech.challenge.rest.common.handler.error.ApiErrorResponse;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import br.com.fiap.tech.challenge.rest.resource.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface CartResourceDoc {

    @Operation(
            summary = "Endpoint that returns a cart by UUID",
            description = "Get a cart registered in the memory database by UUID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Return with success a cart according to request", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "The cart with given UUID was not found.", content = { @Content(schema = @Schema()) })
            }
    )
    CartResponse get(@Parameter(description = "UUID for search a cart", required = true) String uuid);
    @Operation(
            summary = "Endpoint to register a new cart",
            description = "Register a new cart in memory database.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Return with success if a cart was registered", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Returns informing which field of the cart is incorrect and why", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse create(CreateCartRequest request);
    @Operation(
            summary = "Endpoint to add new item in cart",
            description = "Add item to cart and save in memory database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Returns the cart with the new item that was added", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Returns informing which field of the cart is incorrect and why", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse addItem(@Parameter(description = "CartId of the cart you want to add the item to", required = true) String cartId, AddCartItemRequest request);
    @Operation(
            summary = "Endpoint to update item in cart",
            description = "Update item to cart and save in memory database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Returns the cart with the item that was updated", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Returns informing which field of the cart is incorrect and why", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse updateItem(@Parameter(description = "CartId of the cart you want to update the item to", required = true) String cartId, UpdateCartItemRequest request);
    @Operation(
            summary = "Endpoint to remove item in cart",
            description = "Remove item to cart and save in memory database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Returns the cart with the item that was removed", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Returns informing which field of the cart is incorrect and why", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse removeItem(@Parameter(description = "CartId of the cart you want to remove the item to", required = true) String cartId, RemoveCartItemRequest request);
    @Operation(
            summary = "Endpoint that performs cart checkout",
            description = "Cart checkout making the payment and creating the order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "OK - Returns the order created after checkout has been successfully performed", content = { @Content(schema = @Schema(implementation = OrderResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Returns informing that the CartId is incorrect", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    OrderResponse checkout(@Parameter(description = "CartId of the cart that will be checkout", required = true) String uuid);
}
