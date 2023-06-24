package br.com.fiap.tech.challenge.port.driven;

import br.com.fiap.tech.challenge.domain.Customer;

import java.util.Optional;

public interface CustomerReaderService {

    Optional<Customer> readByDocument(String document);
}