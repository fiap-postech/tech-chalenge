package br.com.fiap.tech.challenge.dto;

import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.mapper.CreateBeverageMapper;
import br.com.fiap.tech.challenge.mapper.CreateDessertMapper;
import br.com.fiap.tech.challenge.mapper.CreateSandwichMapper;
import br.com.fiap.tech.challenge.mapper.CreateSideDishMapper;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

import static br.com.fiap.tech.challenge.enterprise.error.ApplicationError.INVALID_CATEGORY_FOR_PRODUCT;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CreateSingleProductDTO extends CreateProductDTO {
    @Serial
    private static final long serialVersionUID = 1464909268054662495L;

    @Override
    public Product toDomain() {
        return switch (getCategory()) {
            case COMBO -> throw new ApplicationException(INVALID_CATEGORY_FOR_PRODUCT);
            case SIDE_DISH -> CreateSideDishMapper.INSTANCE.toSideDish(this);
            case DESSERT -> CreateDessertMapper.INSTANCE.toDessert(this);
            case BEVERAGE -> CreateBeverageMapper.INSTANCE.toBeverage(this);
            case SANDWICH -> CreateSandwichMapper.INSTANCE.toSandwich(this);
        };
    }
}
