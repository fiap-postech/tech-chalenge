package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CustomerEntity;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {


    CustomerEntity toCustomerEntity(Customer source);

    Customer toCustomer(CustomerEntity source);
}
