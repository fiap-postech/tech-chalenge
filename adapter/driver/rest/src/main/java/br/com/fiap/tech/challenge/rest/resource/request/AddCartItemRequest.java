package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.entity.CartItem;
import br.com.fiap.tech.challenge.domain.validation.UUID;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import br.com.fiap.tech.challenge.rest.mapping.AddCartItemRequestMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Item do Carrinho")
public class AddCartItemRequest extends Request<CartItem> {

    @Serial
    private static final long serialVersionUID = -64224455952918649L;

    @Autowired
    private AddCartItemRequestMapper addCartItemRequestMapper;

    @NotBlank
    @UUID
    @Schema(description = "Identificador do produto que será adicionado ao carrinho", example = "12d1b555-6b86-41d8-afb6-17a01b293869")
    private String productId;

    @PositiveOrZero
    @Schema(description = "Quantidade do produto que será adicionado ao carrinho", example = "1")
    private int quantity;

    @Override
    public CartItem toDomain() {
        return addCartItemRequestMapper.toCartItem(this);
    }
}