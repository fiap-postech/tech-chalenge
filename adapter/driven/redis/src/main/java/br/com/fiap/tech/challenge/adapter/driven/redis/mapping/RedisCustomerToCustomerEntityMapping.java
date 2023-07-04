package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.config.RedisTypeMapConfiguration;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.CustomerEntity;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.modelmapper.ModelMapper;

@Mapper
public class RedisCustomerToCustomerEntityMapping implements RedisTypeMapConfiguration {

    @Override
    public void configure(ModelMapper mapper) {
        mapper.typeMap(Customer.class, CustomerEntity.class)
                .addMapping(Customer::uuid, CustomerEntity::setId)
                .addMapping(Customer::name, CustomerEntity::setName)
                .addMapping(Customer::document, CustomerEntity::setDocument)
                .addMapping(Customer::email, CustomerEntity::setEmail)
                .addMapping(Customer::enabled, CustomerEntity::setEnabled);
    }
}