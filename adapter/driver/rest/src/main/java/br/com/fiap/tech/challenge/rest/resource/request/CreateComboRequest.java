package br.com.fiap.tech.challenge.rest.resource.request;

import br.com.fiap.tech.challenge.domain.Combo;
import br.com.fiap.tech.challenge.domain.Product;
import br.com.fiap.tech.challenge.domain.validation.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.io.Serial;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateComboRequest extends Request<Product> {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @UUID
    private String sandwichId;

    @UUID
    private String beverageId;

    @UUID
    private String sideDishId;

    @PositiveOrZero
    private BigDecimal discount;

    @Override
    public Combo toDomain(ModelMapper mapper) {
        return mapper.map(this, Combo.class);
    }
}
