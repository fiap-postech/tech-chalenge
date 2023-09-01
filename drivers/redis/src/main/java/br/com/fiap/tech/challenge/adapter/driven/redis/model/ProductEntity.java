package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.BeverageMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.ComboMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.DessertMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.SandwichMapper;
import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.SideDishMapper;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ProductEntity {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private ProductCategory category;
    private Boolean enabled;

    public Product toDomain() {
        return switch (getCategory()) {
            case SIDE_DISH -> SideDishMapper.INSTANCE.toSideDish(this);
            case DESSERT -> DessertMapper.INSTANCE.toDessert(this);
            case BEVERAGE -> BeverageMapper.INSTANCE.toBeverage(this);
            case SANDWICH -> SandwichMapper.INSTANCE.toSandwich(this);
            case COMBO -> ComboMapper.INSTANCE.toCombo(this);
        };
    }
}