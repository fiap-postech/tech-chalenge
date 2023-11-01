package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.builder.CartTestBuilder;
import br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder;
import br.com.fiap.tech.challenge.application.dto.CreateCartDTO;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import br.com.fiap.tech.challenge.enterprise.entity.Cart;
import br.com.fiap.tech.challenge.exception.ApplicationException;
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
public class CreateCartUseCaseTest {

    @Mock
    private FindCustomerByUUIDUseCase findCustomerByUUIDUseCase;

    @Mock
    private CartWriterGateway writerService;

    @InjectMocks
    private CreateCartUseCaseImpl createCartUseCase;

    @Test
    void whenCreateCartByCreateCartDTO() {
        var customerByUUID = new CustomerTestBuilder.Builder().fullFields();
        var expected = new CartTestBuilder.Builder().withCustomer().build();
        when(findCustomerByUUIDUseCase.get(CustomerTestBuilder.CUSTOMER_UUID)).thenReturn(customerByUUID);
        when(writerService.write(any(Cart.class))).thenReturn(expected);
        CreateCartDTO createCartDTO = buildCreateCartDTO();

        var result = createCartUseCase.create(createCartDTO);

        assertEquals(expected, result);
    }

    @Test
    void whenCreateCartThrowApplicationException() {
        var customerByUUID = new CustomerTestBuilder.Builder().buildDisable();
        var messageExpected = String.format("Cart customer not available [customerUuid=%s]", CustomerTestBuilder.CUSTOMER_UUID);
        when(findCustomerByUUIDUseCase.get(CustomerTestBuilder.CUSTOMER_UUID)).thenReturn(customerByUUID);
        CreateCartDTO createCartDTO = buildCreateCartDTO();

        assertThatThrownBy(() -> createCartUseCase.create(createCartDTO))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(messageExpected);

        verify(writerService,times(0)).write(any(Cart.class));
    }

    private CreateCartDTO buildCreateCartDTO() {
        var createCartDTO = new CreateCartDTO();
        createCartDTO.setCustomerId(CustomerTestBuilder.CUSTOMER_UUID.toString());
        return createCartDTO;
    }

}
