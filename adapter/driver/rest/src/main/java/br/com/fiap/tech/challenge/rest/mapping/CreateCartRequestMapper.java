package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Cart;
import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.request.CreateCartRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class CreateCartRequestMapper {

    public static CreateCartRequestMapper INSTANCE = Mappers.getMapper(CreateCartRequestMapper.class);

    protected static FindCustomerByUUIDService findCustomerByUUIDService;

    @Mapping(target = "customer", source = "source", qualifiedByName = "getCustomer")
    @Mapping(target = "items", ignore = true)
    public abstract Cart toCart(CreateCartRequest source);


    @Named("getCustomer")
    Customer getCustomer(CreateCartRequest source) {
        var customerId = source.getCustomerId();
        return isNull(customerId) ? null : findCustomerByUUIDService.get(UUID.fromString(customerId));
    }

    public static CreateCartRequestMapper init(FindCustomerByUUIDService service) {
        findCustomerByUUIDService = service;
        return CreateCartRequestMapper.INSTANCE;
    }
}
