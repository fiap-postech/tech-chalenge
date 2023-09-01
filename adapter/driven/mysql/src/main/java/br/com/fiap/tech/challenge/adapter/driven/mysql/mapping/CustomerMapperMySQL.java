package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapperMySQL {

    @Mapping(target = "document", expression = "java(customer.toDocument())")
    @Mapping(target = "email", expression = "java(customer.toEmail())")
    @Mapping(target = "name", expression = "java(customer.name())")
    @Mapping(target = "enabled", expression = "java(customer.enabled())")
    @Mapping(target = "uuid", expression = "java(customer.uuid().toString())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    CustomerEntity toCustomerEntity(Customer customer);
}