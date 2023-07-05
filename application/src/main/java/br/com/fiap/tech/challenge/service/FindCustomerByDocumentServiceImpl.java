package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.valueobject.Document;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByDocumentService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
class FindCustomerByDocumentServiceImpl implements FindCustomerByDocumentService {

    private final CustomerReaderService readerService;

    @Override
    public Optional<Customer> get(String document) {
        return readerService.readByDocument(Document.of(document));
    }

}
