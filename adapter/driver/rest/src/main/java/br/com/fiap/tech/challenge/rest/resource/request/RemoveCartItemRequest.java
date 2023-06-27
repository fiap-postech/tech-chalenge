package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
//TODO: deletar
public class RemoveCartItemRequest extends Request<CartItem> {

    @Serial
    private static final long serialVersionUID = -64224455952918649L;

    @NotEmpty
    private String id;

    @NotNull
    @Valid
    private AddCartItemProductRequest product;

    private BigDecimal price;

    private BigDecimal discount;

    @PositiveOrZero
    private int quantity;

    @Override
    public CartItem toDomain(ModelMapper mapper) {
        var cartItem = mapper.map(this, CartItem.class);
        return cartItem.toBuilder()
                .product(this.product.toDomain(mapper))
                .build();
    }
}