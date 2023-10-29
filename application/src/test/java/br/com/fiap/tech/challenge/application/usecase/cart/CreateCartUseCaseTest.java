package br.com.fiap.tech.challenge.application.usecase.cart;

import br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.CartWriterGateway;
import br.com.fiap.tech.challenge.application.usecase.customer.FindCustomerByUUIDUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

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
        when(findCustomerByUUIDUseCase.get(CustomerTestBuilder.CUSTOMER_UUID)).thenReturn(customerByUUID);


    }

}
