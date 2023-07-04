package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.rest.config.RestTypeMapConfiguration;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import org.modelmapper.ModelMapper;

@Mapper
public class CustomerToCustomerResponseMapping implements RestTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Customer.class, CustomerResponse.class)
                .addMapping(Customer::uuid, CustomerResponse::setId)
                .addMapping(Customer::name, CustomerResponse::setName)
                .addMapping(Customer::toEmail, CustomerResponse::setEmail)
                .addMapping(Customer::toDocument, CustomerResponse::setDocument);
    }

}
