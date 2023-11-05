package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder;
import br.com.fiap.tech.challenge.application.dto.CreateCustomerDTO;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.exception.ApplicationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerUseCaseTest {

    @Mock
    private CustomerWriterGateway writerService;

    @Mock
    private CustomerReaderGateway readerService;

    @InjectMocks
    private CreateCustomerUseCaseImpl createCustomerUseCase;

    @Test
    void whenCreateCustomer() {
        var expected = new CustomerTestBuilder.Builder().fullFields();
        var createCustomerDTO = buildCreateCustomerDTO();
        when(readerService.readByDocument(any(Document.class))).thenReturn(Optional.empty());
        when(writerService.write(any(Customer.class))).thenReturn(expected);

        var result = createCustomerUseCase.create(createCustomerDTO);

        assertEquals(expected, result);
    }

    @Test
    void whenCreateCustomerThrowApplicationExceptionHasRegistration() {
        var customer = new CustomerTestBuilder.Builder().fullFields();
        var createCustomerDTO = buildCreateCustomerDTO();
        var messageExpected = "Customer already has registration";
        when(readerService.readByDocument(any(Document.class))).thenReturn(Optional.of(customer));

        assertThatThrownBy(() -> createCustomerUseCase.create(createCustomerDTO))
                .isInstanceOf(ApplicationException.class)
                .hasMessageContaining(messageExpected);

        verify(writerService,times(0)).write(any(Customer.class));
    }

    private static CreateCustomerDTO buildCreateCustomerDTO() {
        var createCustomerDTO = new CreateCustomerDTO();
        createCustomerDTO.setDocument(CustomerTestBuilder.DOCUMENT);
        createCustomerDTO.setEmail(CustomerTestBuilder.EMAIL);
        createCustomerDTO.setName(CustomerTestBuilder.NAME);
        return createCustomerDTO;
    }
}
