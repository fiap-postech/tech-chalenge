package br.com.fiap.tech.challenge.port.driver;

import br.com.fiap.tech.challenge.domain.entity.Customer;

import java.util.Optional;

public interface FindCustomerByDocumentService {

    Optional<Customer> get(String document);

}
