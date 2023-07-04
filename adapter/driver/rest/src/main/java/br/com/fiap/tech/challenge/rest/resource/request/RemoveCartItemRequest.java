package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.CartItem;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Item do Carrinho")
public class RemoveCartItemRequest extends Request<CartItem> {

    @Serial
    private static final long serialVersionUID = -64224455952918649L;

    @NotBlank
    @Schema(description = "Identificador do produto que será removido ao carrinho", example = "12d1b555-6b86-41d8-afb6-17a01b293869")
    private String productId;

    @Override
    public CartItem toDomain(ModelMapper mapper) {
        return mapper.map(this, CartItem.class);
    }
}