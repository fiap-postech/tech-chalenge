package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.Document;

import java.util.Optional;
import java.util.UUID;

public interface CustomerReaderService {

    Customer readById(UUID id);
    Optional<Customer> readByDocument(Document document);
}