package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.util.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.util.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper(uses = {BeverageMapper.class, SideDishMapper.class, SandwichMapper.class})
public interface ComboMapper {

    ComboMapper INSTANCE = Mappers.getMapper(ComboMapper.class);

    @Mapping(target = "price", source = "price", qualifiedByName = "getComboPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getComboImage")
    //TODO Antonio: pls review if this mapping is correct
    Combo toCombo(ProductEntity source);

    @Mapping(target = "price", source = "combo", qualifiedByName = "priceToBigDecimalCombo")
    @Mapping(target = "image", source = "combo", qualifiedByName = "imageToStringConverterCombo")
    @Mapping(target = "category", expression = "java(combo.category())")
    @Mapping(target = "name", expression = "java(combo.name())")
    @Mapping(target = "description", expression = "java(combo.description())")
    @Mapping(target = "enabled", expression = "java(combo.enabled())")
    @Mapping(target = "id", ignore = true)
    ProductEntity toProductType(Combo combo);

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
}
