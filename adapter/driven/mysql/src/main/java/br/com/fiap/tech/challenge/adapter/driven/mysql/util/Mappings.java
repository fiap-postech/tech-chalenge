package br.com.fiap.tech.challenge.adapter.driven.mysql.util;

import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.*;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.domain.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    public static ProductEntity toProductEntity(Product product) {
        return switch (product.category()) {
            case SIDE_DISH -> SideDishMapper.INSTANCE.toProductEntity((SideDish) product);
            case DESSERT -> DessertMapper.INSTANCE.toProductEntity((Dessert) product);
            case BEVERAGE -> BeverageMapper.INSTANCE.toProductEntity((Beverage) product);
            case SANDWICH -> SandwichMapper.INSTANCE.toProductEntity((Sandwich) product);
            case COMBO -> ComboMapper.INSTANCE.toProductType((Combo) product);
        };
    }
}
