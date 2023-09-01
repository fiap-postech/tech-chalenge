package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.resource.request.AddCartItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public abstract class AddCartItemRequestMapper {

    public static AddCartItemRequestMapper INSTANCE = Mappers.getMapper(AddCartItemRequestMapper.class);

    protected static FindProductByUUIDService findProductByUUIDService;

    @Mapping(target = "product", source = "source", qualifiedByName = "getProduct")
    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantity")
    public abstract CartItem toCartItem(AddCartItemRequest source);

    @Named("getProduct")
    Product getProduct(AddCartItemRequest source) {
        var id = source.getProductId();
        return findProductByUUIDService.get(UUID.fromString(id));
    }

    @Named("getQuantity")
    Quantity getQuantity(AddCartItemRequest source) {
        return Quantity.of(source.getQuantity());
    }

    public static AddCartItemRequestMapper init(FindProductByUUIDService service) {
        findProductByUUIDService = service;
        return AddCartItemRequestMapper.INSTANCE;
    }

}
