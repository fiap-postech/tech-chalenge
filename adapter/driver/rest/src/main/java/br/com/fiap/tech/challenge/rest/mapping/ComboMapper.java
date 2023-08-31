package br.com.fiap.tech.challenge.rest.mapping;

import br.com.fiap.tech.challenge.domain.entity.Beverage;
import br.com.fiap.tech.challenge.domain.entity.Combo;
import br.com.fiap.tech.challenge.domain.entity.Sandwich;
import br.com.fiap.tech.challenge.domain.entity.SideDish;
import br.com.fiap.tech.challenge.domain.enums.ProductCategory;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import br.com.fiap.tech.challenge.rest.resource.request.CreateComboProductRequest;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.*;
import static br.com.fiap.tech.challenge.rest.util.Mappings.getProduct;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper(uses = {BeverageMapper.class, SideDishMapper.class, SandwichMapper.class})
public interface ComboMapper {

    ComboMapper INSTANCE = Mappers.getMapper(ComboMapper.class);

    @Mapping(target = "price", source = "price", qualifiedByName = "getComboPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getComboImage")
    @Mapping(target = "beverage", source = "source", qualifiedByName = "getBeverage")
    @Mapping(target = "sideDish", source = "source", qualifiedByName = "getSideDish")
    @Mapping(target = "sandwich", source = "source", qualifiedByName = "getSandwich")
    Combo toCombo(CreateComboProductRequest source);

    @Mapping(target = "price", source = "combo", qualifiedByName = "priceToBigDecimalCombo")
    @Mapping(target = "image", source = "combo", qualifiedByName = "imageToStringConverterCombo")
    @Mapping(target = "name", expression = "java(combo.name())")
    @Mapping(target = "description", expression = "java(combo.description())")
    @Mapping(target = "fullPrice", source = "combo", qualifiedByName = "getFullPrice")
    @Mapping(target = "discount", source = "combo", qualifiedByName = "getDiscount")
    @Mapping(target = "enabled", expression = "java(combo.enabled())")
    @Mapping(target = "id", expression = "java(combo.uuid().toString())")
    @Mapping(target = "category", source = "combo", qualifiedByName = "getCategory")
    ProductResponse toProductType(Combo combo);

    @Named("getComboPrice")
    static Price map(BigDecimal source) {
        return Price.of(makeMoney(source));
    }

    @Named("priceToBigDecimalCombo")
    static BigDecimal priceToBigDecimal(Combo source) {
        return priceToBigDecimalConverter(source.price());
    }

    @Named("getComboImage")
    static Image map(String source) {
        return Image.of(source);
    }

    @Named("imageToStringConverterCombo")
    static String imageConverter(Combo source) {
        return imageToStringConverter(source.image());
    }

    @Named("getBeverage")
    static Beverage getBeverage(CreateComboProductRequest source) {
        return (Beverage) getProduct(source.getBeverageId());
    }

    @Named("getSideDish")
    static SideDish getSideDish(CreateComboProductRequest source) {
        return (SideDish) getProduct(source.getSideDishId());
    }

    @Named("getSandwich")
    static Sandwich getSandwich(CreateComboProductRequest source) {
        return (Sandwich) getProduct(source.getSandwichId());
    }

    @Named("getFullPrice")
    static BigDecimal getFullPrice(Combo combo) {
        return priceToBigDecimalConverter(combo.fullPrice());
    }

    @Named("getDiscount")
    static BigDecimal getDiscount(Combo combo) {
        return discountToBigDecimalConverter(combo.discount());
    }

    @Named("getCategory")
    static ProductCategory getCategory(Combo combo) {
        return ProductCategory.COMBO;
    }
}
