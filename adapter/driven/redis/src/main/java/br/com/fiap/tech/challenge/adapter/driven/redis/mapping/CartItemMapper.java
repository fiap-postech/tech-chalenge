package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.CartItemEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ComboProductEntity;
import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.*;
import br.com.fiap.tech.challenge.domain.valueobject.Quantity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.beans.BeanUtils;

import static br.com.fiap.tech.challenge.domain.enums.ProductCategory.*;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.quantityToIntegerConverter;
import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CartItemMapper {


    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantityEntity")
    @Mapping(target = "product", source = "source", qualifiedByName = "getProductEntity")
    CartItemEntity toCartItemEntity (CartItem source);


    @Mapping(target = "quantity", source = "source", qualifiedByName = "getQuantity")
    @Mapping(target = "product", source = "source", qualifiedByName = "getProduct")
    CartItem toCartItemEntity (CartItemEntity source);
    
    
    @Named("getQuantity")
    static Quantity getQuantity(CartItemEntity source){
        return Quantity.of(source.getQuantity());
    }

    @Named("getQuantityEntity")
    static int getQuantityEntity(CartItem source){
        return quantityToIntegerConverter(source.quantity());
    }

    @Named("getProductEntity")
    static ProductEntity getProductEntity(CartItem source){
        var product = source.product();

        if(isNull(product)) return null;

        return switch (product.category()){
            case BEVERAGE -> BeverageMapper.INSTANCE.toProductEntity((Beverage) product);
            case SANDWICH -> SandwichMapper.INSTANCE.toProductEntity((Sandwich) product);
            case DESSERT -> DessertMapper.INSTANCE.toProductEntity((Dessert) product);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toProductEntity((SideDish) product);
            case COMBO -> ComboMapper.INSTANCE.toProductType((Combo) product);
        };
    }

    @Named("getProduct")
    static Product getProduct(CartItemEntity source){
        var productEntity = source.getProduct();
        if(isNull(productEntity)) return null;
        return productEntity.toDomain();
    }
}
