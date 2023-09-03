package br.com.fiap.tech.challenge.dto;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.validation.UUID;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.mapper.CreateComboMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

import static br.com.fiap.tech.challenge.enterprise.enums.ProductCategory.COMBO;
import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.INVALID_CATEGORY_FOR_PRODUCT;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateComboProductDTO extends CreateProductDTO {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @UUID
    private String sandwichId;

    @UUID
    private String beverageId;

    @UUID
    private String sideDishId;

    @Override
    public Product toDomain() {
        if (getCategory() == COMBO) {
            return CreateComboMapper.INSTANCE.toCombo(this);
        }

        throw new ApplicationException(INVALID_CATEGORY_FOR_PRODUCT);
    }
}
