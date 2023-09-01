package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.enterprise.entity.CartItem;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.rest.resource.request.RemoveCartItemRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import static br.com.fiap.tech.challenge.rest.util.Mappings.getProduct;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RemoveCartItemRequestMapper {

    RemoveCartItemRequestMapper INSTANCE = Mappers.getMapper(RemoveCartItemRequestMapper.class);

    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "product", source = "source", qualifiedByName = "getProductCartItem")
    CartItem toCartItem(RemoveCartItemRequest source);

    @Named("getProductCartItem")
    static Product getProductCartItem(RemoveCartItemRequest source) {
        return getProduct(source.getProductId());
    }

}
