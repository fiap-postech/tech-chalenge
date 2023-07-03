package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.*;
import br.com.fiap.tech.challenge.rest.resource.doc.CartResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import br.com.fiap.tech.challenge.rest.resource.response.OrderResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;
import static java.util.UUID.fromString;
@RestController
@RequestMapping("/cart")
public class CartResource implements CartResourceDoc {

    private final ModelMapper mapper;
    private final FindCartByUUIDService findCartByUUIDService;
    private final CreateCartService createCartService;
    private final AddCartItemService addCartItemService;
    private final UpdateCartItemService updateCartItemService;
    private final RemoveCartItemService removeCartItemService;
    private final CheckoutService checkoutService;

    public CartResource(@Qualifier(REST_MODEL_MAPPER) ModelMapper mapper,
                        FindCartByUUIDService findCartByUUIDService,
                        CreateCartService createCartService,
                        AddCartItemService addCartItemService,
                        UpdateCartItemService updateCartItemService,
                        RemoveCartItemService removeCartItemService,
                        CheckoutService checkoutService) {
        this.mapper = mapper;
        this.findCartByUUIDService = findCartByUUIDService;
        this.createCartService = createCartService;
        this.addCartItemService = addCartItemService;
        this.updateCartItemService = updateCartItemService;
        this.removeCartItemService = removeCartItemService;
        this.checkoutService = checkoutService;
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse get(@PathVariable("uuid") String uuid) {
        return mapper.map(
                findCartByUUIDService.get(fromString(uuid)),
                CartResponse.class
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse create(@RequestBody @Valid CreateCartRequest request) {
        return mapper.map(
                createCartService.create(request.toDomain(mapper)),
                CartResponse.class
        );
    }

    @PostMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse addItem(@PathVariable("cartId") String cartId, @RequestBody @Valid AddCartItemRequest request) {
        return mapper.map(
                addCartItemService.add(fromString(cartId), request.toDomain(mapper)),
                CartResponse.class
        );
    }

    @PatchMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse updateItem(@PathVariable("cartId") String cartId, @RequestBody @Valid UpdateCartItemRequest request) {
        return mapper.map(
                updateCartItemService.update(fromString(cartId), request.toDomain(mapper)),
                CartResponse.class
        );
    }

    @DeleteMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse removeItem(@PathVariable("cartId") String cartId, @RequestBody @Valid RemoveCartItemRequest request) {
        return mapper.map(
                removeCartItemService.remove(fromString(cartId), request.toDomain(mapper)),
                CartResponse.class
        );
    }
    @PostMapping("/{uuid}/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse checkout(@PathVariable String uuid) {
        checkoutService.checkout(UUID.fromString(uuid));

        //TODO get response and map it properly

        return new OrderResponse().setId(UUID.randomUUID().toString());
    }
}