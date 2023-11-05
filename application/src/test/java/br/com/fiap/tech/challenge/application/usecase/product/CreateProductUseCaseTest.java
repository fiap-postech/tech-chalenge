package br.com.fiap.tech.challenge.application.usecase.product;

import br.com.fiap.tech.challenge.application.builder.*;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.*;
import br.com.fiap.tech.challenge.enterprise.enums.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseTest {

    @Mock
    private ProductWriterGateway writerGateway;

    @Mock
    private ProductReaderGateway readerGateway;

    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCase;

    @Test
    void whenCreateSandwich() {
        var sandwichProductDTO = new CreateSandwichProdutDTOTestBuilder.Builder().fullFields();
        var expected = new SandwichTestBuilder.Builder().fullFields();
        when(writerGateway.write(any(Sandwich.class))).thenReturn(expected);

        var result = createProductUseCase.create(sandwichProductDTO);
        assertEquals(expected, result);
    }

    @Test
    void whenCreateDessert() {
        var dessertProductDTO = new CreateDessertProdutDTOTestBuilder.Builder().fullFields();
        var expected = new DessertTestBuilder.Builder().fullFields();
        when(writerGateway.write(any(Dessert.class))).thenReturn(expected);

        var result = createProductUseCase.create(dessertProductDTO);
        assertEquals(expected, result);
    }

    @Test
    void whenCreateSideDish() {
        var sideDishProductDTO = new CreateSideDishProdutDTOTestBuilder.Builder().fullFields();
        var expected = new SideDishTestBuilder.Builder().fullFields();
        when(writerGateway.write(any(SideDish.class))).thenReturn(expected);

        var result = createProductUseCase.create(sideDishProductDTO);
        assertEquals(expected, result);
    }

    @Test
    void whenCreateBeverage() {
        var beverageProductDTO = new CreateBeverageProdutDTOTestBuilder.Builder().fullFields();
        var expected = new BeverageTestBuilder.Builder().fullFields();
        when(writerGateway.write(any(Beverage.class))).thenReturn(expected);

        var result = createProductUseCase.create(beverageProductDTO);
        assertEquals(expected, result);
    }

    @Test
    void whenCreateCombo() {
        var comboProductDTO = new CreateComboProductDTOTestBuilder.Builder().fullFields();
        var beverage = new BeverageTestBuilder.Builder().fullFields();
        var sandwich = new SandwichTestBuilder.Builder().fullFields();
        var sideDish = new SideDishTestBuilder.Builder().fullFields();
        var expected = new ComboTestBuilder.Builder().fullFields();
        when(writerGateway.write(any(Combo.class))).thenReturn(expected);
        when(readerGateway.readById(BeverageTestBuilder.BEVERAGE_UUID)).thenReturn(beverage);
        when(readerGateway.readById(SandwichTestBuilder.SANDWICH_UUID)).thenReturn(sandwich);
        when(readerGateway.readById(SideDishTestBuilder.SIDEDISH_UUID)).thenReturn(sideDish);

        var result = createProductUseCase.create(comboProductDTO);
        assertEquals(expected, result);
    }

    @Test
    void whenCreateIllegalStateDTO() {
        assertThatThrownBy(() -> createProductUseCase.create(null))
                .isInstanceOf(IllegalStateException.class);

        verify(writerGateway,times(0)).write(any());
        verify(readerGateway,times(0)).readById(any());
    }

    @Test
    void whenCreateIllegalStateSingleDTO() {
        var singleDTO = new CreateSandwichProdutDTOTestBuilder.Builder().fullFields();
        singleDTO.setCategory(ProductCategory.COMBO);

        assertThatThrownBy(() -> createProductUseCase.create(singleDTO))
                .isInstanceOf(IllegalStateException.class);

        verify(writerGateway,times(0)).write(any());
        verify(readerGateway,times(0)).readById(any());
    }
}
