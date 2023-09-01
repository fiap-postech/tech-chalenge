package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.Dessert;
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

@Mapper(
        imports = {
                br.com.fiap.tech.challenge.util.Mappings.class
        }
)
public interface DessertMapper {

    DessertMapper INSTANCE = Mappers.getMapper(DessertMapper.class);

    @Mapping(target = "uuid", source = "id", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Dessert toDessert(ProductEntity request);

    @Mapping(target = "id", expression = "java(request.uuid().toString())")
    @Mapping(target = "price", expression = "java(Mappings.priceToBigDecimalConverter(request.price()))")
    @Mapping(target = "image", expression = "java(Mappings.imageToStringConverter(request.image()))")
    @Mapping(target = "name", expression = "java(request.name())")
    @Mapping(target = "category", expression = "java(request.category())")
    @Mapping(target = "description", expression = "java(request.description())")
    @Mapping(target = "enabled", expression = "java(request.enabled())")
    ProductEntity toProductEntity(Dessert request);

    @Named("getUuid")
    static String getUuid(UUID uuid) {
        return uuid.toString();
    }

    @Named("generateUuid")
    static UUID generateUuid(String uuid) {
        return UUID.fromString(uuid);
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Price price) {
        return priceToBigDecimalConverter(price);
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
    static String imageConverter(Image source) {
        return imageToStringConverter(source);
    }
}
