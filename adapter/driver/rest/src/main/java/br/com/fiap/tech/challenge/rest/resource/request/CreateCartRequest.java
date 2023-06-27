package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serial;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateCartRequest extends Request<Cart> {

    @Serial
    private static final long serialVersionUID = -64224455952918649L;

    @Override
    public Cart toDomain(ModelMapper mapper) {
        return mapper.map(this, Cart.class);
    }
}