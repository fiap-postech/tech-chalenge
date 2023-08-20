package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.adapter.driven.redis.mapping.*;
import br.com.fiap.tech.challenge.domain.entity.Beverage;
import br.com.fiap.tech.challenge.domain.entity.Combo;
import br.com.fiap.tech.challenge.domain.entity.Dessert;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.domain.enums.ProductCategory;
import br.com.fiap.tech.challenge.domain.entity.Sandwich;
import br.com.fiap.tech.challenge.domain.entity.SideDish;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

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