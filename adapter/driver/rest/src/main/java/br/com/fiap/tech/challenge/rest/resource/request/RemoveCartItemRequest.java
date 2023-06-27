package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RemoveCartItemRequest extends Request<CartItem> {

    @Serial
    private static final long serialVersionUID = -64224455952918649L;

    @NotBlank
    private String productId;

    @Override
    public CartItem toDomain(ModelMapper mapper) {
        return mapper.map(this, CartItem.class);
    }
}