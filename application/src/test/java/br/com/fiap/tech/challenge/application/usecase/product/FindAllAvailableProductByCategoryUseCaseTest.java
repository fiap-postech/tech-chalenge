package br.com.fiap.tech.challenge.application.usecase.product;

import br.com.fiap.tech.challenge.application.builder.SandwichTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.application.util.Page;
import br.com.fiap.tech.challenge.application.util.ResponseList;
import br.com.fiap.tech.challenge.enterprise.entity.Product;
import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllAvailableProductByCategoryUseCaseTest {

    @Mock
    private ProductReaderGateway readerService;

    @InjectMocks
    private FindAllAvailableProductByCategoryUseCaseImpl findAllAvailableProductByCategoryUseCase;

    @Test
    void whenFindAllAvailableProduct() {
        var sandwich = new SandwichTestBuilder.Builder().fullFields();
        var expected = buildResponseList(sandwich);
        var page = new Page(1, 1);
        when(readerService.readAllByCategory(ProductCategory.SANDWICH,page)).thenReturn(expected);

        var result = findAllAvailableProductByCategoryUseCase.list(ProductCategory.SANDWICH, page);

        assertEquals(expected, result);
    }

    private ResponseList<Product> buildResponseList(Sandwich sandwich){
        return new ResponseList<>(
                1,
                1,
                1,
                1L,
                List.of(sandwich)
        );
    }

}
