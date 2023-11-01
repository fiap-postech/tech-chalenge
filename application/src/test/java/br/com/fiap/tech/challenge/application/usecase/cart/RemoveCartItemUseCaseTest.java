package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.builder.CartTestBuilder;
import br.com.fiap.tech.challenge.application.builder.SandwichTestBuilder;
import br.com.fiap.tech.challenge.application.dto.RemoveCartItemDTO;
import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoveCartItemUseCaseTest {

    @Mock
    private CartReaderGateway cartReaderGateway;

    @Mock
    private CartWriterGateway writerService;

    @InjectMocks
    private RemoveCartItemUseCaseImpl removeCartItemUseCase;

    @Test
    void whenRemoveCartItemInCart() {
        var cartBuilder = new CartTestBuilder.Builder();
        var cartByUUID = cartBuilder.buildWithCartItem();
        var expected = cartBuilder.buildWithoutCartItem();
        var remoteCartItemDTO = buildRemoveCartItemDTO();
        when(cartReaderGateway.readById(CartTestBuilder.CART_UUID)).thenReturn(cartByUUID);
        when(writerService.write(any(Cart.class))).thenReturn(expected);

        var result = removeCartItemUseCase.remove(CartTestBuilder.CART_UUID, remoteCartItemDTO);

        assertEquals(expected, result);
    }

    private RemoveCartItemDTO buildRemoveCartItemDTO() {
        var remoteCartItemDTO = new RemoveCartItemDTO();
        remoteCartItemDTO.setProductId(SandwichTestBuilder.SANDWICH_UUID.toString());
        return remoteCartItemDTO;
    }
}
