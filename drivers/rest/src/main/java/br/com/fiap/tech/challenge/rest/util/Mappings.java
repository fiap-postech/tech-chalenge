package br.com.fiap.tech.challenge.rest.util;

import br.com.fiap.tech.challenge.enterprise.entity.Beverage;
import br.com.fiap.tech.challenge.enterprise.entity.Combo;
import br.com.fiap.tech.challenge.enterprise.entity.Dessert;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.enterprise.entity.SideDish;
import br.com.fiap.tech.challenge.port.driver.FindProductByUUIDService;
import br.com.fiap.tech.challenge.rest.mapping.BeverageMapper;
import br.com.fiap.tech.challenge.rest.mapping.ComboMapper;
import br.com.fiap.tech.challenge.rest.mapping.DessertMapper;
import br.com.fiap.tech.challenge.rest.mapping.SandwichMapper;
import br.com.fiap.tech.challenge.rest.mapping.SideDishMapper;
import br.com.fiap.tech.challenge.rest.resource.response.ProductResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static java.util.Objects.isNull;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Mappings {

    private static FindProductByUUIDService findProductByUUIDService;


    public static ProductResponse toProductResponse(Product product) {
        if (isNull(product)) return null;

        return switch (product.category()) {
            case BEVERAGE -> BeverageMapper.INSTANCE.toProductEntity((Beverage) product);
            case SANDWICH -> SandwichMapper.INSTANCE.toProductEntity((Sandwich) product);
            case DESSERT -> DessertMapper.INSTANCE.toProductEntity((Dessert) product);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toProductEntity((SideDish) product);
            case COMBO -> ComboMapper.INSTANCE.toProductType((Combo) product);
        };
    }


    public static Product getProduct(String productId) {
        return isNull(productId) ? null : findProductByUUIDService.get(UUID.fromString(productId));
    }


    public static Mappings init(FindProductByUUIDService service) {
        findProductByUUIDService = service;
        return new Mappings();
    }
}
