package br.com.fiap.tech.challenge.rest.util;

import br.com.fiap.tech.challenge.domain.entity.*;
import br.com.fiap.tech.challenge.rest.mapping.*;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {
    public static ProductResponse toProductResponse(Product product) {
        if(isNull(product)) return null;

        return switch (product.category()){
            case BEVERAGE -> BeverageMapper.INSTANCE.toProductEntity((Beverage) product);
            case SANDWICH -> SandwichMapper.INSTANCE.toProductEntity((Sandwich) product);
            case DESSERT -> DessertMapper.INSTANCE.toProductEntity((Dessert) product);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toProductEntity((SideDish) product);
            case COMBO -> ComboMapper.INSTANCE.toProductType((Combo) product);
        };
    }
}
