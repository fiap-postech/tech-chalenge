package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import br.com.fiap.tech.challenge.rest.common.request.Request;
import br.com.fiap.tech.challenge.rest.mapping.BeverageMapper;
import br.com.fiap.tech.challenge.rest.mapping.ComboMapper;
import br.com.fiap.tech.challenge.rest.mapping.DessertMapper;
import br.com.fiap.tech.challenge.rest.mapping.SandwichMapper;
import br.com.fiap.tech.challenge.rest.mapping.SideDishMapper;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateSingleProductRequest.class, name = "single"),
        @JsonSubTypes.Type(value = CreateComboProductRequest.class, name = "combo")
})
public abstract class CreateProductRequest extends Request<Product> {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    private String image;

    @NotNull
    private ProductCategory category;

    private boolean enabled = true;

    @Override
    public Product toDomain() {
        throw new IllegalStateException("this method is deprecated and will be removed soon");
    }
}
