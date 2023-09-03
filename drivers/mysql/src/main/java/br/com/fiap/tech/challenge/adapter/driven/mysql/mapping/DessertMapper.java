package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.enterprise.entity.Dessert;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public interface DessertMapper {

    DessertMapper INSTANCE = Mappers.getMapper(DessertMapper.class);

    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Dessert toDessert(ProductEntity request);

    @Mapping(target = "uuid", source = "request", qualifiedByName = "getUuid")
    @Mapping(target = "name", expression = "java(request.name())")
    @Mapping(target = "category", expression = "java(request.category())")
    @Mapping(target = "description", expression = "java(request.description())")
    @Mapping(target = "enabled", expression = "java(request.enabled())")
    @Mapping(target = "price", source = "request", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "request", qualifiedByName = "imageToStringConverter")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ProductEntity toProductEntity(Dessert request);

    @Named("getUuid")
    static String getUuid(Dessert request) {
        return request.uuid().toString();
    }

    @Named("generateUuid")
    static UUID generateUuid(String uuid) {
        return UUID.fromString(uuid);
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Dessert request) {
        return priceToBigDecimalConverter(request.price());
    }

    @Named("getPrice")
    static Price getPrice(BigDecimal source) {
        return Price.of(makeMoney(source));
    }

    @Named("getImage")
    static Image getImage(String source) {
        return Image.of(source);
    }

    @Named("imageToStringConverter")
    static String imageConverter(Dessert request) {
        return imageToStringConverter(request.image());
    }
}
