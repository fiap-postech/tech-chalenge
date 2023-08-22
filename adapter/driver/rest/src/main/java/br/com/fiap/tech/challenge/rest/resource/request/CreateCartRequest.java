package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.entity.Cart;
import br.com.fiap.tech.challenge.domain.validation.UUID;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import br.com.fiap.tech.challenge.rest.mapping.CreateCartRequestMapper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Representação de um Carrinho")
public class CreateCartRequest extends Request<Cart> {

    @Serial
    private static final long serialVersionUID = -64224455952918649L;

    @Autowired
    private CreateCartRequestMapper createCartRequestMapper;

    @UUID(required = false)
    private String customerId;

    @Override
    public Cart toDomain() {
        return createCartRequestMapper.toCart(this);
    }
}