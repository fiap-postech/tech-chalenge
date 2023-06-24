package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import org.modelmapper.ModelMapper;

@Mapper
public class CustomerToCustomerResponseMapping implements TypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Customer.class, CustomerResponse.class)
                .addMapping(Customer::uuid, CustomerResponse::setId)
                .addMapping(Customer::name, CustomerResponse::setName)
                .addMapping(Customer::email, CustomerResponse::setEmail)
                .addMapping(Customer::document, CustomerResponse::setDocument);
    }

}
