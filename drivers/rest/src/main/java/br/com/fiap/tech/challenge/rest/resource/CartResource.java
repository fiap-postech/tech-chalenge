package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.*;
import br.com.fiap.tech.challenge.rest.mapping.CartMapperRest;
import br.com.fiap.tech.challenge.rest.mapping.PurchaseMapperRest;
import br.com.fiap.tech.challenge.rest.resource.doc.CartResourceDoc;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import br.com.fiap.tech.challenge.rest.resource.response.PurchseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static java.util.UUID.fromString;

@RestController
@RequestMapping("/cart")
public class CartResource implements CartResourceDoc {

    private final CartMapperRest mapper;
    private final PurchaseMapperRest purchaseMapperRest;
    private final FindCartByUUIDService findCartByUUIDService;
    private final CreateCartService createCartService;
    private final AddCartItemService addCartItemService;
    private final UpdateCartItemService updateCartItemService;
    private final RemoveCartItemService removeCartItemService;
    private final CheckoutService checkoutService;

    public CartResource(CartMapperRest mapper,
                        PurchaseMapperRest purchaseMapperRest,
                        FindCartByUUIDService findCartByUUIDService,
                        CreateCartService createCartService,
                        AddCartItemService addCartItemService,
                        UpdateCartItemService updateCartItemService,
                        RemoveCartItemService removeCartItemService,
                        CheckoutService checkoutService) {
        this.mapper = mapper;
        this.purchaseMapperRest = purchaseMapperRest;
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
        return mapper.toCartResponse(findCartByUUIDService.get(fromString(uuid)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse create(@RequestBody @Valid CreateCartRequest request) {
        return mapper.toCartResponse(createCartService.create(request.toDomain()));
    }

    @PostMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse addItem(@PathVariable("cartId") String cartId, @RequestBody @Valid AddCartItemRequest request) {
        return mapper.toCartResponse(addCartItemService.add(fromString(cartId), request.toDomain()));
    }

    @PatchMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse updateItem(@PathVariable("cartId") String cartId, @RequestBody @Valid UpdateCartItemRequest request) {
        return mapper.toCartResponse(updateCartItemService.update(fromString(cartId), request.toDomain()));
    }

    @DeleteMapping("{cartId}/items")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse removeItem(@PathVariable("cartId") String cartId, @RequestBody @Valid RemoveCartItemRequest request) {
        return mapper.toCartResponse(removeCartItemService.remove(fromString(cartId), request.toDomain()));
    }

    @PostMapping("/{uuid}/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public PurchseResponse checkout(@PathVariable String uuid) {
        return purchaseMapperRest.toPurchseResponse(checkoutService.checkout(UUID.fromString(uuid)));
    }
}