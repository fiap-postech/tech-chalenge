package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.builder.CartTestBuilder;
import br.com.fiap.tech.challenge.application.builder.SandwichTestBuilder;
import br.com.fiap.tech.challenge.application.dto.UpdateCartItemDTO;
import br.com.fiap.tech.challenge.application.gateway.CartReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.product.FindProductByUUIDUseCase;
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
public class UpdateCartItemUseCaseTest {

    @Mock
    private CartReaderGateway cartReaderGateway;

    @Mock
    private CartWriterGateway writerService;

    @Mock
    private FindProductByUUIDUseCase findProductByUUIDUseCase;

    @InjectMocks
    private UpdateCartItemUseCaseImpl upgradeCustomerUseCase;

    @Test
    void whenUpdateCartItemInCart() {
        var cartBuilder = new CartTestBuilder.Builder();
        var cartByUUID = cartBuilder.buildWithCartItem();
        var sandwichByUUID = new SandwichTestBuilder.Builder().fullFields();
        var updateCartItemDTO = buildUpdateCartItemDTO();
        var expected = cartBuilder.buildWithCartItemTwoQuantity();

        when(cartReaderGateway.readById(CartTestBuilder.CART_UUID)).thenReturn(cartByUUID);
        when(findProductByUUIDUseCase.get(SandwichTestBuilder.SANDWICH_UUID)).thenReturn(sandwichByUUID);
        when(writerService.write(any(Cart.class))).thenReturn(expected);

        var result = upgradeCustomerUseCase.update(CartTestBuilder.CART_UUID, updateCartItemDTO);

        assertEquals(expected, result);
    }

    private UpdateCartItemDTO buildUpdateCartItemDTO() {
        var updateCartItemDTO = new UpdateCartItemDTO();
        updateCartItemDTO.setProductId(SandwichTestBuilder.SANDWICH_UUID.toString());
        updateCartItemDTO.setQuantity(2);
        return updateCartItemDTO;
    }
}
