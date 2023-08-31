package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.*;
import br.com.fiap.tech.challenge.domain.entity.Product;
import br.com.fiap.tech.challenge.domain.enums.ProductCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
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

    public Product toDomain() {
        return switch (category) {
            case BEVERAGE -> BeverageMapper.INSTANCE.toBeverage(this);
            case SANDWICH -> SandwichMapper.INSTANCE.toSandwich(this);
            case DESSERT -> DessertMapper.INSTANCE.toDessert(this);
            case SIDE_DISH -> SideDishMapper.INSTANCE.toSideDish(this);
            case COMBO -> ComboMapper.INSTANCE.toCombo((ComboEntity) this);
        };
    }
}
