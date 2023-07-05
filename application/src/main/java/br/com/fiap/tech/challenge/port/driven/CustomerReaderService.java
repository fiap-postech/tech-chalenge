package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.entity.Customer;
import br.com.fiap.tech.challenge.domain.valueobject.Document;

import java.util.Optional;
import java.util.UUID;

public interface CustomerReaderService {

    Customer readById(UUID id);
    Optional<Customer> readByDocument(Document document);
}