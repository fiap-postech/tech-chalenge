package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import br.com.fiap.tech.challenge.mapper.common.TypeMapConfiguration;
import org.modelmapper.ModelMapper;

@Mapper
public class CustomerToCustomerEntityMapping implements TypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Customer.class, CustomerEntity.class)
                .addMapping(Customer::uuid, CustomerEntity::setUuid)
                .addMapping(Customer::name, CustomerEntity::setName)
                .addMapping(Customer::email, CustomerEntity::setEmail)
                .addMapping(Customer::document, CustomerEntity::setDocument);
    }

}
