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
public class EnableProductUseCaseTest {

    @Mock
    private ProductReaderGateway readerGateway;

    @Mock
    private ProductWriterGateway writerGateway;

    @InjectMocks
    private EnableProductUseCaseImpl enableProductUseCase;

    @Test
    void whenEnableProduct() {
        var sandwichBuilder = new SandwichTestBuilder.Builder();
        var sandwichDisable = sandwichBuilder.buildDisable();
        var expected = sandwichBuilder.fullFields();
        when(readerGateway.readById(SandwichTestBuilder.SANDWICH_UUID)).thenReturn(sandwichDisable);
        when(writerGateway.write(any(Sandwich.class))).thenReturn(expected);

        var result = enableProductUseCase.enable(SandwichTestBuilder.SANDWICH_UUID.toString());

        assertEquals(expected, result);
    }
}
