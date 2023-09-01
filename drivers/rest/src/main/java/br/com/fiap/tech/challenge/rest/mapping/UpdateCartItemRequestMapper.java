package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import br.com.fiap.tech.challenge.rest.resource.request.UpdateCartItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static br.com.fiap.tech.challenge.rest.util.Mappings.getProduct;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UpdateCartItemRequestMapper {

    UpdateCartItemRequestMapper INSTANCE = Mappers.getMapper(UpdateCartItemRequestMapper.class);

    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantity")
    @Mapping(target = "product", source = "source", qualifiedByName = "getProductCartItem")
    CartItem toCartItem(UpdateCartItemRequest source);

    @Named("getQuantity")
    static Quantity getQuantity(UpdateCartItemRequest source) {
        return Quantity.of(source.getQuantity());
    }

    @Named("getProductCartItem")
    static Product getProductCartItem(UpdateCartItemRequest source) {
        return getProduct(source.getProductId());
    }
}
