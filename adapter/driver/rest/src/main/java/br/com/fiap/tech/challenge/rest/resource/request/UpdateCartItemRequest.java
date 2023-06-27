package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UpdateCartItemRequest extends Request<CartItem> {

    @Serial
    private static final long serialVersionUID = 9020112551138763090L;

    @NotBlank
    private String productId;

    @PositiveOrZero
    private int quantity;

    @Override
    public CartItem toDomain(ModelMapper mapper) {
        return mapper.map(this, CartItem.class);
    }
}