package br.com.fiap.tech.challenge.application.usecase.customer;

import br.com.fiap.tech.challenge.application.builder.CustomerTestBuilder;
import br.com.fiap.tech.challenge.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.enterprise.valueobject.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindCustomerByDocumentUseCaseTest {

    @Mock
    private CustomerReaderGateway readerService;

    @InjectMocks
    private FindCustomerByDocumentUseCaseImpl findCustomerByDocumentUseCase;

    @Test
    void whenFindCustomerByDocument() {
        var customerByDocument = new CustomerTestBuilder.Builder().fullFields();
        var expected = Optional.of(customerByDocument);
        when(readerService.readByDocument(any(Document.class))).thenReturn(expected);

        var result = findCustomerByDocumentUseCase.get(CustomerTestBuilder.DOCUMENT);

        assertEquals(expected, result);
    }

}
