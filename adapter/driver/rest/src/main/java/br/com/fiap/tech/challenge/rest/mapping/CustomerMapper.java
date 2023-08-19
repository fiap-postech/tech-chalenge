package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.rest.resource.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "email", expression = "java(source.toEmail())")
    @Mapping(target = "document", expression = "java(source.toDocument())")
    CustomerResponse toCustomerResponse(Customer source);

}
