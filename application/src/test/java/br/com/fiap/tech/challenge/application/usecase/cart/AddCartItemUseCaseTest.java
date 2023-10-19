package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCartItemUseCaseTest {

    @Mock
    private CartReaderGateway cartReaderGateway;

    @Mock
    private CartWriterGateway writerService;

    @Mock
    private FindProductByUUIDUseCase findProductByUUIDUseCase;

    @InjectMocks
    private AddCartItemUseCase addCartItemUseCase;

    @Test
    void whenAddCartItemWithSucess() {
        assertEquals("a", "a");
    }
}
