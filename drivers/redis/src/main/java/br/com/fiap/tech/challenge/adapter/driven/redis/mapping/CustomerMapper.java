package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CustomerEntity;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CustomerMapper {

    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "email", expression = "java(source.email())")
    @Mapping(target = "document", expression = "java(source.document())")
    @Mapping(target = "enabled", expression = "java(source.enabled())")
    CustomerEntity toCustomerEntity(Customer source);

    @Mapping(target = "uuid", source = "source", qualifiedByName = "generateUuid")
    Customer toCustomer(CustomerEntity source);


    @Named("generateUuid")
    static UUID generateUuid(CustomerEntity source) {
        return UUID.fromString(source.getId());
    }
}
