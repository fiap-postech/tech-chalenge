package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Cart;
import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByUUIDService;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

import java.util.UUID;

import static java.util.Objects.isNull;

@Mapper
@AllArgsConstructor
public class CreateCartRequestToDomainMapping implements RestTypeMapConfiguration {

    private final FindCustomerByUUIDService findCustomerByUUIDService;

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CreateCartRequest.class, Cart.class)
                .setProvider(cartProvider());
    }

    private Provider<Cart> cartProvider() {
        return provision -> {
            var request = (CreateCartRequest) provision.getSource();
            var customer = getCustomer(request.getCustomerId());

            return Cart.builder()
                    .customer(customer)
                    .build();
        };
    }

    private Customer getCustomer(String uuid) {
        if (isNull(uuid)) {
            return null;
        }
        return findCustomerByUUIDService.get(UUID.fromString(uuid));
    }
}