package br.com.fiap.tech.challenge.service;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.port.driven.CustomerReaderService;
import br.com.fiap.tech.challenge.port.driver.FindCustomerByDocumentService;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class FindCustomerByDocumentServiceImpl implements FindCustomerByDocumentService {

    private CustomerReaderService readerService;

    @Override
    public Optional<Customer> get(String document) {
        return readerService.readByDocument(document);
    }

}
