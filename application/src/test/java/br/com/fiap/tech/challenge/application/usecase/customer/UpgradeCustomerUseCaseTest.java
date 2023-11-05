package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpgradeCustomerUseCaseTest {

    @Mock
    private CustomerWriterGateway writerGateway;

    @Mock
    private CustomerReaderGateway readerGateway;

    @InjectMocks
    private UpgradeCustomerUseCaseImpl upgradeCustomerUseCase;


    @Test
    void whenDisableCustomerByDocument() {
        var customerBuilder = new CustomerTestBuilder.Builder();
        var customerByDocument = customerBuilder.fullFields();
        var expected = customerBuilder.buildDisable();

        when(readerGateway.readByDocument(any(Document.class))).thenReturn(Optional.of(customerByDocument));
        when(writerGateway.write(any(Customer.class))).thenReturn(expected);

        var result = upgradeCustomerUseCase.disable(CustomerTestBuilder.DOCUMENT).orElseThrow();

        assertEquals(expected, result);
    }

    @Test
    void whenDisableCustomerByDocumentEmpty() {
        when(readerGateway.readByDocument(any(Document.class))).thenReturn(Optional.empty());

        var result = upgradeCustomerUseCase.disable(CustomerTestBuilder.DOCUMENT);

        verify(writerGateway,times(0)).write(any(Customer.class));
        assertTrue(result.isEmpty());
    }

}
