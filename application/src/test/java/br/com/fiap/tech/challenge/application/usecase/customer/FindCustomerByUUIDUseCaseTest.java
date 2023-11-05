package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder.CUSTOMER_UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindCustomerByUUIDUseCaseTest {

    @Mock
    private CustomerReaderGateway customerReaderGateway;

    @InjectMocks
    private FindCustomerByUUIDUseCaseImpl findCustomerByUUIDUseCase;

    @Test
    void whenFindCustomerByUUID() {
        var expected = new CustomerTestBuilder.Builder().fullFields();
        when(customerReaderGateway.readById(CUSTOMER_UUID)).thenReturn(expected);

        var result = findCustomerByUUIDUseCase.get(CUSTOMER_UUID);

        assertEquals(expected, result);
    }
}
