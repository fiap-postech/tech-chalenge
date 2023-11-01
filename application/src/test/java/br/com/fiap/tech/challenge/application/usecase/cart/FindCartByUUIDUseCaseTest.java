package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.builder.CartTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindCartByUUIDUseCaseTest {

    @Mock
    private CartReaderGateway readerService;

    @InjectMocks
    private FindCartByUUIDUseCaseImpl findCartByUUIDUseCase;

    @Test
    void whenFindCartByUUID() {
        var expected = new CartTestBuilder.Builder().buildWithoutCartItem();
        when(readerService.readById(CartTestBuilder.CART_UUID)).thenReturn(expected);

        var result = findCartByUUIDUseCase.get(CartTestBuilder.CART_UUID);

        assertEquals(expected, result);
    }

}
