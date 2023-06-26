package br.com.fiap.tech.challenge.adapter.driven.mysql.model;

import br.com.fiap.tech.challenge.domain.*;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

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

    public Product toDomain(ModelMapper mapper){
        return switch (category){
            case BEVERAGE -> mapper.map(this, Beverage.class);
            case SANDWICH -> mapper.map(this, Sandwich.class);
            case DESSERT -> mapper.map(this, Dessert.class);
            case SIDE_DISH -> mapper.map(this, SideDish.class);
            case COMBO -> mapper.map(this, Combo.class);
        };
    }
}
