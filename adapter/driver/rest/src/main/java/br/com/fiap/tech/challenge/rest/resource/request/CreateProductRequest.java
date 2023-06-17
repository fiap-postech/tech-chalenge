package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.Beverage;
import br.com.fiap.tech.challenge.domain.Dessert;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.ProductType;
import br.com.fiap.tech.challenge.domain.Sandwich;
import br.com.fiap.tech.challenge.domain.SideDish;
import br.com.fiap.tech.challenge.rest.resource.response.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serial;
import java.math.BigDecimal;


@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateProductRequest extends Response {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    private String name;
    private BigDecimal price;
    private BigDecimal cost;
    private BigDecimal profit;
    private ProductType type;

    public Product toDomain(ModelMapper mapper) {
        return switch (getType()) {
            case SIDE_DISH -> mapper.map(this, SideDish.class);
            case DESSERT -> mapper.map(this, Dessert.class);
            case BEVERAGE -> mapper.map(this, Beverage.class);
            case SANDWICH -> mapper.map(this, Sandwich.class);
        };
    }
}
