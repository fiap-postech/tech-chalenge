package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ComboEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.enterprise.entity.Combo;
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

@Mapper(uses = {BeverageMapper.class, SideDishMapper.class, SandwichMapper.class})
public interface ComboMapper {

    ComboMapper INSTANCE = Mappers.getMapper(ComboMapper.class);

    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "generateUuidCombo")
    @Mapping(target = "price", source = "price", qualifiedByName = "getComboPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getComboImage")
    Combo toCombo(ComboEntity source);


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
