package br.com.fiap.tech.challenge.rest.resource;

import br.com.fiap.tech.challenge.port.driver.AddCartItemService;
import br.com.fiap.tech.challenge.port.driver.CreateCartService;
import br.com.fiap.tech.challenge.port.driver.FindCartByUUIDService;
import br.com.fiap.tech.challenge.port.driver.RemoveCartItemService;
import br.com.fiap.tech.challenge.port.driver.UpdateCartItemService;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import br.com.fiap.tech.challenge.rest.resource.response.CartResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static br.com.fiap.tech.challenge.rest.config.RestModelMapperConfiguration.REST_MODEL_MAPPER;
import static java.util.UUID.fromString;

@RestController
@RequestMapping("/cart")
public class CartResource {

    private final ModelMapper mapper;
    private final FindCartByUUIDService findCartByUUIDService;
    private final CreateCartService createCartService;
    private final AddCartItemService addCartItemService;
    private final UpdateCartItemService updateCartItemService;
    private final RemoveCartItemService removeCartItemService;

    public CartResource(@Qualifier(REST_MODEL_MAPPER) ModelMapper mapper,
                        FindCartByUUIDService findCartByUUIDService,
                        CreateCartService createCartService,
                        AddCartItemService addCartItemService,
                        UpdateCartItemService updateCartItemService,
                        RemoveCartItemService removeCartItemService) {
        this.mapper = mapper;
        this.findCartByUUIDService = findCartByUUIDService;
        this.createCartService = createCartService;
        this.addCartItemService = addCartItemService;
        this.updateCartItemService = updateCartItemService;
        this.removeCartItemService = removeCartItemService;
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

    @PostMapping("{cartId}/item")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse addItem(@PathVariable("cartId") String cartId, @RequestBody @Valid AddCartItemRequest request) {
        return mapper.map(
                addCartItemService.add(fromString(cartId), request.toDomain(mapper)),
                CartResponse.class
        );
    }

    @PatchMapping("{cartId}/item")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse updateItem(@PathVariable("cartId") String cartId, @RequestBody @Valid UpdateCartItemRequest request) {
        return mapper.map(
                updateCartItemService.update(fromString(cartId), request.toDomain(mapper)),
                CartResponse.class
        );
    }

    @DeleteMapping("{cartId}/item")
    @ResponseStatus(HttpStatus.OK)
    public CartResponse removeItem(@PathVariable("cartId") String cartId, @RequestBody @Valid RemoveCartItemRequest request) {
        return mapper.map(
                removeCartItemService.remove(fromString(cartId), request.toDomain(mapper)),
                CartResponse.class
        );
    }
}