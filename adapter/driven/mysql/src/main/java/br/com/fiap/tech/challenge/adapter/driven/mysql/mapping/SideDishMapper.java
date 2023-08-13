package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.SideDish;
import br.com.fiap.tech.challenge.domain.valueobject.Image;
import br.com.fiap.tech.challenge.domain.valueobject.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Mapper
public interface SideDishMapper {

    SideDishMapper INSTANCE = Mappers.getMapper(SideDishMapper.class);

    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "generateUuid")
    @Mapping(target = "price", source = "price", qualifiedByName = "getPrice")
    @Mapping(target = "image", source = "image", qualifiedByName = "getImage")
    SideDish toSideDish(ProductEntity request);

    @Named("generateUuid")
    static UUID generateUuid(String uuid){
        return UUID.fromString(uuid);
    }

    @Named("getPrice")
    static Price getPrice(BigDecimal source){
        return Price.of(makeMoney(source));
    }

    @Named("getImage")
    static Image getImage(String source){
        return Image.of(source);
    }
}
