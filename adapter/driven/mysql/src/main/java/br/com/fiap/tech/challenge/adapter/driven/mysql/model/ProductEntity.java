package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Price;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.ProductCategory;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
public class ProductEntity extends JPAEntity {
    @Serial
    private static final long serialVersionUID = -3103908935115324117L;

    @NotBlank
    private String name;

    @NotBlank
    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    @Column(columnDefinition = "text")
    private String image;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    private boolean enabled;

    public Product toDomain(){
        return switch (category){
            case BEVERAGE -> Beverage.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(Price.of(makeMoney(getPrice())))
                    .image(getImage())
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            case SANDWICH -> Sandwich.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(Price.of(makeMoney(getPrice())))
                    .image(getImage())
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            case DESSERT -> Dessert.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(Price.of(makeMoney(getPrice())))
                    .image(getImage())
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            case SIDE_DISH, COMBO -> SideDish.builder()
                    .name(getName())
                    .description(getDescription())
                    .price(Price.of(makeMoney(getPrice())))
                    .image(getImage())
                    .uuid(UUID.fromString(getUuid()))
                    .build();

            //TODO map combo to domain
        };
    }
}
