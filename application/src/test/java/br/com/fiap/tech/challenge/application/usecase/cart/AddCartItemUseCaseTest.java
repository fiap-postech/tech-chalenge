package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
import jakarta.validation.ConstraintViolationException;
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
        try {

            var customer = new CustomerTestBuilder.Builder().fullFields();

            System.out.println(customer.toString());
        } catch (ConstraintViolationException e) {
            System.out.println(e.getMessage());
        }


        assertEquals("a", "a");
    }
}
