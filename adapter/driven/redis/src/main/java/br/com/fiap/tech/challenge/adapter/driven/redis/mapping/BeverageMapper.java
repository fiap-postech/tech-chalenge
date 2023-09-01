package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.enterprise.entity.Beverage;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
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
public interface BeverageMapper {

    BeverageMapper INSTANCE = Mappers.getMapper(BeverageMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Beverage toBeverage(ProductEntity request);


    @Mapping(target = "id", expression = "java(source.uuid().toString())")
    @Mapping(target = "name", expression = "java(source.name())")
    @Mapping(target = "description", expression = "java(source.description())")
    @Mapping(target = "enabled", expression = "java(source.enabled())")
    @Mapping(target = "category", expression = "java(source.category())")
    @Mapping(target = "price", source = "source", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "source", qualifiedByName = "imageToStringConverter")
    ProductEntity toProductEntity(Beverage source);

    @Named("generateUuid")
    static UUID generateUuid(String uuid) {
        return UUID.fromString(uuid);
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Beverage source) {
        return priceToBigDecimalConverter(source.price());
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
    static String imageConverter(Beverage source) {
        return imageToStringConverter(source.image());
    }
}
