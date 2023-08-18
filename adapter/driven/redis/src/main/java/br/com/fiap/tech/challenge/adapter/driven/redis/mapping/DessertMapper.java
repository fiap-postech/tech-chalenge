package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.Dessert;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import br.com.fiap.tech.challenge.mapper.common.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public interface DessertMapper {

    DessertMapper INSTANCE = Mappers.getMapper(DessertMapper.class);

    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Dessert toDessert(ProductEntity request);

    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "getUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToStringConverter")
    ProductEntity toProductEntity(Dessert request);

    @Named("getUuid")
    static String getUuid(UUID uuid){
        return uuid.toString();
    }

    @Named("generateUuid")
    static UUID generateUuid(String uuid){
        return UUID.fromString(uuid);
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Price price){
        return priceToBigDecimalConverter(price);
    }

    @Named("getPrice")
    static Price getPrice(BigDecimal source){
        return Price.of(makeMoney(source));
    }

    @Named("getImage")
    static Image getImage(String source){
        return Image.of(source);
    }

    @Named("imageToStringConverter")
    static String imageConverter(Image source){
        return imageToStringConverter(source);
    }
}
