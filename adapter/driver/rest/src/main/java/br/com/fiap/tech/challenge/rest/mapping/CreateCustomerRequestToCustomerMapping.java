package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.Document;
import br.com.fiap.tech.challenge.domain.EmailRegistration;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCustomerRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;

@Mapper
public class CreateCustomerRequestToCustomerMapping implements RestTypeMapConfiguration {
    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(CreateCustomerRequest.class, Customer.class)
                .setProvider(customerProvider());
    }

    private static Provider<Customer> customerProvider() {
        return provision -> {
            var request = (CreateCustomerRequest) provision.getSource();

            return Customer.builder()
                    .name(request.getName())
                    .email(EmailRegistration.of(request.getEmail()))
                    .document(Document.of(request.getDocument()))
                    .enabled(Boolean.TRUE)
                    .build();
        };
    }
}
