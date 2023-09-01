package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.enterprise.entity.Dessert;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import br.com.fiap.tech.challenge.rest.resource.request.CreateSingleProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Mappings.discountToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public interface DessertMapper {

    DessertMapper INSTANCE = Mappers.getMapper(DessertMapper.class);

    @Mapping(target = "uuid", source = "request", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    Dessert toDessert(CreateSingleProductRequest request);

    @Mapping(target = "id", source = "request", qualifiedByName = "getUuid")
    @Mapping(target = "price", source = "request", qualifiedByName = "priceToBigDecimal")
    @Mapping(target = "image", source = "request", qualifiedByName = "imageToStringConverter")
    @Mapping(target = "name", expression = "java(request.name())")
    @Mapping(target = "description", expression = "java(request.description())")
    @Mapping(target = "fullPrice", source = "request", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "request", qualifiedByName = "getDiscount")
    @Mapping(target = "enabled", expression = "java(request.enabled())")
    @Mapping(target = "category", source = "request", qualifiedByName = "getCategory")
    ProductResponse toProductEntity(Dessert request);

    @Named("getUuid")
    static String getUuid(Dessert request) {
        return request.uuid().toString();
    }

    @Named("generateUuid")
    static UUID generateUuid(CreateSingleProductRequest request) {
        return UUID.randomUUID();
    }

    @Named("priceToBigDecimal")
    static BigDecimal priceToBigDecimal(Dessert request) {
        return priceToBigDecimalConverter(request.price());
    }

    @Named("getPrice")
    static Price getPrice(BigDecimal source) {
        return Price.of(makeMoney(source));
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(Dessert request) {
        return priceToBigDecimalConverter(request.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(Dessert request) {
        return discountToBigDecimalConverter(request.discount());
    }

    @Named("getCategory")
    static ProductCategory getCategory(Dessert request) {
        return request.category();
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
