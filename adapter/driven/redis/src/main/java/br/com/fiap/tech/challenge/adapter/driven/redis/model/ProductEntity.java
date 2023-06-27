package br.com.fiap.tech.challenge.adapter.driven.redis.model;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.ProductCategory;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
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

    public Product toDomain(ModelMapper mapper) {
        return switch (getCategory()) {
            case COMBO -> mapper.map(this, SideDish.class); //TODO put right code for combo here
            case SIDE_DISH -> mapper.map(this, SideDish.class);
            case DESSERT -> mapper.map(this, Dessert.class);
            case BEVERAGE -> mapper.map(this, Beverage.class);
            case SANDWICH -> mapper.map(this, Sandwich.class);
        };
    }
}