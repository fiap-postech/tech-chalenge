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
            summary = "Endpoint que retorna um carrinho pelo seu UUID",
            description = "Busca um carrinho cadastrado no memory database através do UUID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno do carrinho em caso de sucesso conforme requisição solicitada", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno em caso de carrinho não encontrado pelo UUID informado", content = { @Content(schema = @Schema()) })
            }
    )
    CartResponse get(@Parameter(description = "UUID para pesquisar um carrinho", required = true) String uuid);
    @Operation(
            summary = "Endpoint para cadastrar um novo carrinho",
            description = "Cadastra um novo carrinho no memory database",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Retorno em caso de sucesso em que o carrinho foi cadastrado", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse create(CreateCartRequest request);
    @Operation(
            summary = "Endpoint para adicionar novo item do carrinho",
            description = "Adiciona o item ao carrinho e salva no memory database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que o item foi adicionado do carrinho", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse addItem(@Parameter(description = "CartId do carrinho ao qual se deseja adicionar o item", required = true) String cartId, AddCartItemRequest request);
    @Operation(
            summary = "Endpoint para atualizar o item do carrinho",
            description = "Atualiza o item no carrinho e salve no memory database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que o item foi atualizado do carrinho", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse updateItem(@Parameter(description = "CartId do carrinho ao qual se deseja atualizar o item", required = true) String cartId, UpdateCartItemRequest request);
    @Operation(
            summary = "Endpoint para remover item do carrinho",
            description = "Remove o item do carrinho e salva no memory database",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK - Retorno em caso de sucesso em que o item foi removido do carrinho", content = { @Content(schema = @Schema(implementation = CartResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno informando qual campo do carrinho está incorreto e por qual motivo", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    CartResponse removeItem(@Parameter(description = "CartId do carrinho ao qual se deseja remover o item", required = true) String cartId, RemoveCartItemRequest request);
    @Operation(
            summary = "Endpoint que efetua o checkout do carrinho",
            description = "Faz o checkout do carrinho, efetuando o pagamento e criando o pedido",
            responses = {
                    @ApiResponse(responseCode = "201", description = "OK - Retorno em caso de sucesso com o pedido criado após o checkout ter sido realizado", content = { @Content(schema = @Schema(implementation = OrderResponse.class), mediaType = "application/json") }),
                    @ApiResponse(responseCode = "400", description = "Retorno em caso em que o CartId informando está incorreto", content = { @Content(schema = @Schema(implementation = ApiErrorResponse.class), mediaType = "application/json") })
            }
    )
    OrderResponse checkout(@Parameter(description = "CartId do carrinho que será feito o checkout", required = true) String uuid);
}
