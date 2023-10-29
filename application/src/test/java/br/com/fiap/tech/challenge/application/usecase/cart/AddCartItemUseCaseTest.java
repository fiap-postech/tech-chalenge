package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.builder.CartTestBuilder;
import br.com.fiap.tech.challenge.application.builder.SandwichTestBuilder;
import br.com.fiap.tech.challenge.application.dto.AddCartItemDTO;
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
class AddCartItemUseCaseTest {

    @Mock
    private CartReaderGateway cartReaderGateway;

    @Mock
    private CartWriterGateway writerService;

    @Mock
    private FindProductByUUIDUseCase findProductByUUIDUseCase;

    @InjectMocks
    private AddCartItemUseCaseImpl addCartItemUseCase;

    @Test
    void whenAddCartItemWithSucess() {

        var cartReadyById = new CartTestBuilder.Builder().buildWithoutCartItem();
        var productByUUID = new SandwichTestBuilder.Builder().fullFields();
        var inputDTO = getAddCartItemDTO();
        var expected = new CartTestBuilder.Builder().buildWithCartItem();

        when(cartReaderGateway.readById(CartTestBuilder.CART_UUID)).thenReturn(cartReadyById);
        when(findProductByUUIDUseCase.get(SandwichTestBuilder.SANDWICH_UUID)).thenReturn(productByUUID);
        when(writerService.write(any(Cart.class))).thenReturn(expected);

        var result = addCartItemUseCase.add(CartTestBuilder.CART_UUID, inputDTO);

        assertEquals(expected, result);
    }

    private AddCartItemDTO getAddCartItemDTO() {
        var inputDTO = new AddCartItemDTO();
        inputDTO.setProductId(SandwichTestBuilder.SANDWICH_UUID.toString());
        inputDTO.setQuantity(1);
        return inputDTO;
    }
}
