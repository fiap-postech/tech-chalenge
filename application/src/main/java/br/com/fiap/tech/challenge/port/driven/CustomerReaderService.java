package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Customer;
import br.com.fiap.tech.challenge.domain.Document;

import java.util.Optional;

public interface CustomerReaderService {

    Optional<Customer> readByDocument(Document document);
}