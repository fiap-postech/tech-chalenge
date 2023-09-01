package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.Combo;
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

@Mapper(uses = {BeverageMapper.class, SideDishMapper.class, SandwichMapper.class})
public interface ComboMapper {

    ComboMapper INSTANCE = Mappers.getMapper(ComboMapper.class);

    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "generateUuidCombo")
    @Mapping(target = "price", source = "price", qualifiedByName = "getComboPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getComboImage")
    Combo toCombo(ComboEntity source);

    @Mapping(target = "uuid", expression = "java(combo.uuid().toString())")
    @Mapping(target = "price", source = "combo", qualifiedByName = "priceToBigDecimalCombo")
    @Mapping(target = "image", source = "combo", qualifiedByName = "imageToStringConverterCombo")
    @Mapping(target = "category", expression = "java(combo.category())")
    @Mapping(target = "name", expression = "java(combo.name())")
    @Mapping(target = "description", expression = "java(combo.description())")
    @Mapping(target = "enabled", expression = "java(combo.enabled())")
    @Mapping(target = "beverage", source = "combo", qualifiedByName = "toBeverageEntity")
    @Mapping(target = "sideDish", source = "combo", qualifiedByName = "toSideDishEntity")
    @Mapping(target = "sandwich", source = "combo", qualifiedByName = "toSandwichEntity")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ComboEntity toProductType(Combo combo);

    @Named("generateUuidCombo")
    static UUID generateUuidCombo(String uuid) {
        return UUID.fromString(uuid);
    }

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

    @Named("toBeverageEntity")
    static ProductEntity mapBeverageToEntity(Combo combo) {
        return BeverageMapper.INSTANCE.toProductEntity(combo.beverage());
    }

    @Named("toSideDishEntity")
    static ProductEntity mapSideDishToEntity(Combo combo) {
        return SideDishMapper.INSTANCE.toProductEntity(combo.sideDish());
    }

    @Named("toSandwichEntity")
    static ProductEntity mapSandwichToEntity(Combo combo) {
        return SandwichMapper.INSTANCE.toProductEntity(combo.sandwich());
    }
}
