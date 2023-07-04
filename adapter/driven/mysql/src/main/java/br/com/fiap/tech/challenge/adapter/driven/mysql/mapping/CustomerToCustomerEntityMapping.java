package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.config.MySQLTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;

@Mapper
public class CustomerToCustomerEntityMapping implements MySQLTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Customer.class, CustomerEntity.class)
                .addMapping(Customer::uuid, CustomerEntity::setUuid)
                .addMapping(Customer::name, CustomerEntity::setName)
                .addMapping(Customer::toEmail, CustomerEntity::setEmail)
                .addMapping(Customer::toDocument, CustomerEntity::setDocument)
                .addMapping(Customer::enabled, CustomerEntity::setEnabled);
    }

}