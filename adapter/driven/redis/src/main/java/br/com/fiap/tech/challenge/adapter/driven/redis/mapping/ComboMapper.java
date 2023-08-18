package br.com.fiap.tech.challenge.adapter.driven.redis.mapping;

import br.com.fiap.tech.challenge.adapter.driven.redis.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.Combo;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static br.com.fiap.tech.challenge.mapper.common.Mappings.imageToStringConverter;
import static br.com.fiap.tech.challenge.mapper.common.Mappings.priceToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper(uses = {BeverageMapper.class, SideDishMapper.class, SandwichMapper.class})
public interface ComboMapper {

    ComboMapper INSTANCE = Mappers.getMapper(ComboMapper.class);

    @Mapping(target = "price", source = "price", qualifiedByName = "getComboPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getComboImage")
    Combo toCombo(ProductEntity source);

    @Mapping(target = "price", source = "combo", qualifiedByName = "priceToBigDecimalCombo")
    @Mapping(target = "image", source = "combo", qualifiedByName = "imageToStringConverterCombo")
    @Mapping(target = "category", expression = "java(combo.category())")
    ProductEntity toProductType(Combo combo);

    @Named("getComboPrice")
    static Price map(BigDecimal source){
        return Price.of(makeMoney(source));
    }

    @Named("priceToBigDecimalCombo")
    static BigDecimal priceToBigDecimal(Combo source){
        return priceToBigDecimalConverter(source.price());
    }

    @Named("getComboImage")
    static Image map(String source){
        return Image.of(source);
    }

    @Named("imageToStringConverterCombo")
    static String imageConverter(Combo source){
        return imageToStringConverter(source.image());
    }
}
