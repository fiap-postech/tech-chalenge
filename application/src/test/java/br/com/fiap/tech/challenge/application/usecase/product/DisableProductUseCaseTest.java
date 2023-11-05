package br.com.fiap.tech.challenge.application.usecase.product;

import br.com.fiap.tech.challenge.application.builder.SandwichTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.ProductReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.ProductWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Sandwich;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DisableProductUseCaseTest {

    @Mock
    private ProductReaderGateway readerGateway;

    @Mock
    private ProductWriterGateway writerGateway;

    @InjectMocks
    private DisableProductUseCaseImpl disableProductUseCase;

    @Test
    void whenDisableProduct() {
        var sandwichBuilder = new SandwichTestBuilder.Builder();
        var sandwich = sandwichBuilder.fullFields();
        var expected = sandwichBuilder.buildDisable();
        when(readerGateway.readById(SandwichTestBuilder.SANDWICH_UUID)).thenReturn(sandwich);
        when(writerGateway.write(any(Sandwich.class))).thenReturn(expected);

        var result = disableProductUseCase.disable(SandwichTestBuilder.SANDWICH_UUID.toString());

        assertEquals(expected, result);
    }
}
