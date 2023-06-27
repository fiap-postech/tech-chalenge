package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

@Mapper
public class CreateCartRequestToDomainMapping implements RestTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CreateCartRequest.class, Cart.class)
                .setProvider(cartProvider());
    }

    private static Provider<Cart> cartProvider() {
        return provision -> Cart.builder().build();
    }
}