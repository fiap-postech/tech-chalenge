package br.com.fiap.tech.challenge.adapter.mapping;

import br.com.fiap.tech.challenge.enterprise.valueobject.Image;
import br.com.fiap.tech.challenge.enterprise.valueobject.Price;
import br.com.fiap.tech.challenge.enterprise.valueobject.Quantity;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

public interface CommonMapper {

    @Named("generateUuid")
    static UUID generateUuid(String uuid) {
        return UUID.fromString(uuid);
    }

    @Named("getPrice")
    static Price map(BigDecimal source) {
        return Price.of(makeMoney(source));
    }

    @Named("getImage")
    static Image map(String source) {
        return Image.of(source);
    }

    @Named("getQuantityVO")
    static Quantity mapQuantity(int quantity){
        return Quantity.of(quantity);
    }
}
